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
package org.cdlflex.ui.markup.html.alert;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.css.Alerts;
import org.cdlflex.ui.markup.css.Alerts.Level;
import org.cdlflex.ui.markup.css.ICssClassNameProvider;
import org.cdlflex.ui.util.Strings;

/**
 * A Border container that wraps markup around a bootstrap alert component. It adds all necessary DOM attributes and an
 * optional dismiss button that allows the alert to be closed.
 */
public class Alert extends Border implements ICssClassNameProvider {

    private static final long serialVersionUID = 1L;

    private Level level;
    private boolean dismissible;
    private boolean fade;

    public Alert(String id) {
        this(id, true);
    }

    public Alert(String id, IModel<?> model) {
        this(id, model, Level.INFO);
    }

    public Alert(String id, Level level) {
        this(id, level, true);
    }

    public Alert(String id, IModel<?> model, Level level) {
        this(id, model, level, true);
    }

    public Alert(String id, boolean dismissible) {
        this(id, Level.INFO, dismissible);
    }

    public Alert(String id, IModel<?> model, boolean dismissible) {
        this(id, model, Level.INFO, dismissible);
    }

    public Alert(String id, Level level, boolean dismissible) {
        this(id, null, level, dismissible);
    }

    public Alert(String id, IModel<?> model, Level level, boolean dismissible) {
        super(id, model);
        this.level = level;
        this.dismissible = dismissible;
    }

    public Level getLevel() {
        return level;
    }

    public Alert setLevel(Level level) {
        this.level = level;
        return this;
    }

    public boolean isDismissible() {
        return dismissible;
    }

    public Alert setDismissible(boolean dismissible) {
        this.dismissible = dismissible;
        return this;
    }

    public boolean isFade() {
        return fade;
    }

    public Alert setFade(boolean fade) {
        this.fade = fade;
        return this;
    }

    @Override
    public String getCssClassName() {
        return Strings.join(" ", Alerts.CSS_CLASS, getLevel().getCssClassName(), isDismissible()
            ? "alert-dismissible" : "", isFade() ? "fade in" : "");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new CssClassNameAppender(this));
        addToBorder(newDismissButtonComponent("dismiss-button"));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.put("role", "alert");
    }

    /**
     * Factory method for creating the component that may dismiss the alert panel.
     *
     * @param id the component id
     * @return a new component
     */
    protected Component newDismissButtonComponent(String id) {
        return new WebMarkupContainer(id) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isVisible() {
                return isDismissible();
            }
        };
    }

}
