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
package org.cdlflex.ui.markup.html.panel;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.markup.css.Alerts.Level;
import org.cdlflex.ui.markup.css.ICssClassNameProvider;
import org.cdlflex.ui.markup.html.alert.Alert;

/**
 * A Panel that displays an {@link Alert}.
 */
public class AlertPanel extends Panel implements ICssClassNameProvider {

    private static final long serialVersionUID = 1L;

    private Alert alert;

    public AlertPanel(String id) {
        this(id, true);
    }

    public AlertPanel(String id, IModel<?> model) {
        this(id, model, Level.INFO);
    }

    public AlertPanel(String id, Level level) {
        this(id, level, true);
    }

    public AlertPanel(String id, IModel<?> model, Level level) {
        this(id, model, level, true);
    }

    public AlertPanel(String id, boolean dismissible) {
        this(id, Level.INFO, dismissible);
    }

    public AlertPanel(String id, IModel<?> model, boolean dismissible) {
        this(id, model, Level.INFO, dismissible);
    }

    public AlertPanel(String id, Level level, boolean dismissible) {
        this(id, null, level, dismissible);
    }

    public AlertPanel(String id, IModel<?> model, Level level, boolean dismissible) {
        super(id, model);
        add(alert = new Alert("alert", model, level, dismissible));
    }

    @Override
    public String getCssClassName() {
        return alert.getCssClassName();
    }

    public Level getLevel() {
        return alert.getLevel();
    }

    public AlertPanel setLevel(Level level) {
        alert.setLevel(level);
        return this;
    }

    public boolean isDismissible() {
        return alert.isDismissible();
    }

    public AlertPanel setDismissible(boolean dismissible) {
        alert.setDismissible(dismissible);
        return this;
    }

    public boolean isFade() {
        return alert.isFade();
    }

    public AlertPanel setFade(boolean fade) {
        alert.setFade(fade);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setRenderBodyOnly(true);
        alert.add(newMessageComponent("message"));
    }

    /**
     * Factory method for creating the component displaying the body of the alert. Defaults to a label that displays the
     * default model of this panel.
     * 
     * @param id the component id
     * @return a new component
     */
    protected Component newMessageComponent(String id) {
        return new Label(id, getDefaultModel());
    }

}
