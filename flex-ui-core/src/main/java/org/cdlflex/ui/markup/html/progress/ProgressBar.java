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
package org.cdlflex.ui.markup.html.progress;

import java.util.Objects;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.model.ReadOnlyModel;

/**
 * Wicket component for bootstrap's <a href="http://getbootstrap.com/components/#progress">progress bar</a>.
 */
public class ProgressBar extends Panel {

    private IModel<Double> valueModel;
    private IModel<String> textModel;

    private boolean striped;
    private boolean animated;

    public ProgressBar(String id) {
        this(id, new Model<>(), new Model<>());
    }

    public ProgressBar(String id, IModel<Double> valueModel, IModel<String> textModel) {
        this(id, null, valueModel, textModel);
    }

    public ProgressBar(String id, IModel<?> model, IModel<Double> valueModel, IModel<String> textModel) {
        super(id, model);
        this.valueModel = Objects.requireNonNull(valueModel);
        this.textModel = Objects.requireNonNull(textModel);

        init();
    }

    private void init() {
        add(new CssClassNameAppender("progress"));

        WebMarkupContainer bar = new WebMarkupContainer("progress-bar");
        add(bar);

        bar.add(new CssClassNameAppender(new ReadOnlyModel<>(() -> isStriped() ? "progress-bar-striped" : null)));
        bar.add(new CssClassNameAppender(new ReadOnlyModel<>(() -> isAnimated() ? "active" : null)));
        bar.add(new AttributeAppender("aria-valuenow", new ReadOnlyModel<>(this::getValue)));
        bar.add(new AttributeAppender("style",
                new ReadOnlyModel<>(() -> String.format("width: %.1f%%;", getValue()))));

        Label text = new Label("text", new ReadOnlyModel<>(this::getText));
        bar.add(text);
    }

    public Double getValue() {
        return getValueModel().getObject();
    }

    public String getText() {
        return getTextModel().getObject();
    }

    /**
     * Sets the value of the label inside the progress bar.
     * 
     * @param text the text inside the bar
     * @return this for chaining
     */
    public ProgressBar setText(String text) {
        getTextModel().setObject(text);
        return this;
    }

    /**
     * Sets the current value of the progress bar. Should be a double value between 0 and 100.
     *
     * @param value the value
     * @return this for chaining
     */
    public ProgressBar setValue(Double value) {
        getValueModel().setObject(value);
        return this;
    }

    public IModel<Double> getValueModel() {
        return valueModel;
    }

    public IModel<String> getTextModel() {
        return textModel;
    }

    public boolean isStriped() {
        return striped;
    }

    /**
     * Defines whether the bar should show the gradient effect.
     * 
     * @param striped if the bar should be striped
     * @return this for chaining
     */
    public ProgressBar setStriped(boolean striped) {
        this.striped = striped;
        return this;
    }

    public boolean isAnimated() {
        return animated;
    }

    /**
     * Defines whether the stripes should be animated right to left. The animation is only visible if
     * {@link #setStriped(boolean)} is set to true.
     * 
     * @param animated if the bar should be animated
     * @return this for chaining
     */
    public ProgressBar setAnimated(boolean animated) {
        this.animated = animated;
        return this;
    }

}
