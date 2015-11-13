/**
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.cdlflex.ui.ajax.markup.html.panel;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Panel that executes an operation in a separate thread and lets the client poll in a specified update interval
 * whether the operation was complete. It provides hooks to react to this execution lifecycle.
 * 
 * @param <T> The operation result type
 */
public abstract class AjaxLazyPollingPanel<T> extends Panel {

    private static final Logger LOG = LoggerFactory.getLogger(AjaxLazyPollingPanel.class);

    private static final long serialVersionUID = 1L;

    /**
     * The component id which will be used to load the lazily loaded component.
     */
    public static final String LAZY_LOAD_COMPONENT_ID = "content";

    /**
     * The state of the execution.
     *
     * <ul>
     * <li>0: created</li>
     * <li>1: loading</li>
     * <li>2: completed</li>
     * <li>3: error</li>
     * </ul>
     */
    private volatile int state;

    public AjaxLazyPollingPanel(String id, Duration updateInterval) {
        this(id, null, updateInterval);
    }

    public AjaxLazyPollingPanel(String id, IModel<?> model, Duration updateInterval) {
        super(id, model);

        setOutputMarkupId(true);

        add(new AbstractAjaxTimerBehavior(updateInterval) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onTimer(AjaxRequestTarget target) {
                if (isCompleted()) {
                    stop(target);
                    AjaxLazyPollingPanel.this.replace(getLazyLoadComponent(LAZY_LOAD_COMPONENT_ID));
                    target.add(AjaxLazyPollingPanel.this);
                    AjaxLazyPollingPanel.this.onTimerStop(this, target);
                } else if (isFailed()) {
                    stop(target);
                    AjaxLazyPollingPanel.this.onTimerFailed(this, target);
                } else {
                    target.add(AjaxLazyPollingPanel.this);
                    AjaxLazyPollingPanel.this.onTimerTick(this, target);
                }
            }
        });
    }

    /**
     * Returns true if the execution process was started or was completed.
     *
     * @return started flag
     */
    public boolean isStarted() {
        return state != 0;
    }

    /**
     * Returns true if the execution is in progress.
     *
     * @return loading flag
     */
    public boolean isLoading() {
        return state == 1;
    }

    /**
     * Returns true if the execution was completed.
     * 
     * @return completed flag
     */
    public boolean isCompleted() {
        return state == 2;
    }

    /**
     * Returns true if the execution has failed because of an exception;
     *
     * @return failed flag
     */
    public boolean isFailed() {
        return state == 3;
    }

    @Override
    protected void onBeforeRender() {
        if (!isStarted()) {
            add(getLoadingComponent(LAZY_LOAD_COMPONENT_ID));
            setLoading();

            // TODO use listenable futures?
            // TODO add the future to the session?
            execute(() -> {
                // TODO ideally we don't execute our own callable, but the provided one and use some trigger mechanisms
                try {
                    T result = getOperation().call();
                    onAfterComplete(result);
                    setComplete();
                    return result;
                } catch (Exception e) {
                    try {
                        onException(e);
                    } catch (Exception inner) {
                        LOG.error("Exception caught while handling exception", inner);
                    } finally {
                        setFailed();
                    }
                    throw e;
                }
            });
        }

        super.onBeforeRender();
    }

    /**
     * Factory hook for creating the operation to be executed.
     *
     * @return a callable
     */
    protected abstract Callable<T> getOperation();

    /**
     * Execute an arbitrary callable. This method should take care of attaching any necessary ThreadContext to the
     * Callable and then execute it in a Thread.
     * 
     * @param callable the Callable to execute
     * @param <V> The callable result type
     * @return a Future
     */
    protected abstract <V> Future<V> execute(Callable<V> callable);

    /**
     * Returns the component displayed while loading.
     *
     * @param id the markup id for the component
     * @return a new component
     */
    protected Component getLoadingComponent(String id) {
        return new Label(id, "Loading...");
    }

    /**
     * Returns the component displayed after completion.
     *
     * @param id the markup id for the component
     * @return a new component
     */
    protected abstract Component getLazyLoadComponent(String id);

    /**
     * Hook executed after the operation has completed with the result object of the operation. You can not access the
     * RequestCycle from within this function, which means you can't manipulate any wicket components that should be
     * rendered afterwards.
     *
     * @param result the result object of the operation {@link #getOperation()}
     */
    protected void onAfterComplete(T result) {
        // hook
    }

    /**
     * Hook executed if the operation failed with an exception. You can not access the RequestCycle from within this
     * function, which means you can't manipulate any wicket components that should be rendered afterwards.
     *
     * @param e the caught exception
     */
    protected void onException(Exception e) {
        LOG.error("An exception occurred while executing operation", e);
    }

    /**
     * Hook executed after each ajax polling timer tick.
     *
     * @param timer the timer being executed
     * @param target the ajax request target
     */
    protected void onTimerTick(AbstractAjaxTimerBehavior timer, AjaxRequestTarget target) {
        // hook
    }

    /**
     * Hook executed after an ajax polling request was made, but the execution of the operation was already done and the
     * content component has been replaced.
     *
     * @param timer the timer being executed
     * @param target the ajax request target
     */
    protected void onTimerStop(AbstractAjaxTimerBehavior timer, AjaxRequestTarget target) {
        // hook
    }

    /**
     * Hook executed after an ajax polling request was made, but the execution of the operation has failed.
     *
     * @param timer the timer being executed
     * @param target the ajax request target
     */
    protected void onTimerFailed(AbstractAjaxTimerBehavior timer, AjaxRequestTarget target) {
        // hook
    }

    private void setLoading() {
        setState(1);
    }

    private void setComplete() {
        setState(2);
    }

    private void setFailed() {
        setState(3);
    }

    private void setState(int state) {
        this.state = state;
    }
}
