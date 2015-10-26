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

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.cdlflex.ui.markup.html.progress.ProgressBar;

/**
 * A AjaxLazyPollingPanel that uses a ProgressBar as a loading component.
 * 
 * @param <T> The operation result type
 */
public abstract class AjaxLazyProgressPanel<T> extends AjaxLazyPollingPanel<T> {

    private static final long serialVersionUID = 1L;

    private ProgressBar progressBar;

    public AjaxLazyProgressPanel(String id, Duration updateInterval) {
        super(id, updateInterval);
    }

    public AjaxLazyProgressPanel(String id, IModel<?> model, Duration updateInterval) {
        super(id, model, updateInterval);
    }

    @Override
    protected final Component getLoadingComponent(String id) {
        this.progressBar = newProgressBar(id);
        return progressBar;
    }

    /**
     * Factory hook for creating the progress bar to be displayed.
     * 
     * @param id the markup id
     * @return a new progress bar
     */
    protected ProgressBar newProgressBar(String id) {
        return new ProgressBar(id);
    }

    /**
     * Returns the ProgressBar that was created using {@link #newProgressBar(String)}.
     * 
     * @return the ProgressBar
     */
    public ProgressBar getProgressBar() {
        return progressBar;
    }

    /**
     * Sets the value of the progress bar, should be between 0 and 100.
     *
     * @param value the value
     * @return this for chaining
     */
    public AjaxLazyProgressPanel setProgress(Double value) {
        getProgressBar().setValue(value);
        return this;
    }

    /**
     * Sets the text of the progress bar.
     *
     * @param value the text
     * @return this for chaining
     */
    public AjaxLazyProgressPanel setProgressText(String value) {
        getProgressBar().setText(value);
        return this;
    }

}
