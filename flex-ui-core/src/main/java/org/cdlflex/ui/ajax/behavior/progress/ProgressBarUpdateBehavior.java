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
package org.cdlflex.ui.ajax.behavior.progress;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.time.Duration;
import org.cdlflex.ui.markup.html.progress.ProgressBar;

/**
 * This AbstractAjaxTimerBehavior updates a ProgressBar until the value of 100 is reached.
 */
public class ProgressBarUpdateBehavior extends AbstractAjaxTimerBehavior {

    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     *
     * @param updateInterval Duration between AJAX callbacks
     */
    public ProgressBarUpdateBehavior(Duration updateInterval) {
        super(updateInterval);
    }

    /**
     * Returns the casted value of {@link #getComponent()}.
     * 
     * @return the ProgressBar component
     */
    protected final ProgressBar getProgressBar() {
        return (ProgressBar) getComponent();
    }

    @Override
    protected void onBind() {
        super.onBind();

        Component component = getComponent();
        if (!(component instanceof ProgressBar)) {
            throw new IllegalStateException("Tried to attach ProgressBarUpdateBehavior to " + component);
        }
    }

    @Override
    protected final void onTimer(AjaxRequestTarget target) {
        target.add(getComponent());

        if (getProgressBar().getValue() >= 100) {
            stop(target);
            onFinished(target);
        } else {
            onAfterTimer(target);
        }
    }

    /**
     * Called after the progress bar's value hits 100 and stop was called.
     * 
     * @param target The request target
     */
    protected void onFinished(AjaxRequestTarget target) {
        // hook
    }

    /**
     * Called after each timer tick.
     * 
     * @param target The request target
     */
    protected void onAfterTimer(AjaxRequestTarget target) {
        // hook
    }
}
