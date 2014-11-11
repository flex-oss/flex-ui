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
package org.cdlflex.ui.ajax.markup.html.form;

import static org.rauschig.wicketjs.jquery.JQuery.$;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.markup.html.form.ButtonRadioChoice;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsIdentifier;
import org.rauschig.wicketjs.JsStatements;
import org.rauschig.wicketjs.ajax.AjaxCallListener;
import org.rauschig.wicketjs.ajax.JsAjaxCallListener;

/**
 * ButtonRadioChoice with ajax onChange behavior listeners.
 * <p/>
 * Notice: This isn't very well implemented, because it works around a discrepancy between bootstrap's 'data-toggle' for
 * radio buttons (inherited from ButtonRadioChoice) and Wickets AjaxFormChoiceComponentUpdatingBehavior. Bootstrap
 * somehow causes the event bubbling to stop, which means that the wicket ajax event is never called. The front-end
 * functionality (setting label classes) is thus solved via wicket-js and jquery.
 * 
 * @param <T> Model object type
 */
public abstract class AjaxButtonRadioChoice<T> extends ButtonRadioChoice<T> {

    private static final long serialVersionUID = 1L;

    public AjaxButtonRadioChoice(String id) {
        super(id);
    }

    public AjaxButtonRadioChoice(String id, List<? extends T> choices) {
        super(id, choices);
    }

    public AjaxButtonRadioChoice(String id, List<? extends T> choices, IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public AjaxButtonRadioChoice(String id, IModel<T> model, List<? extends T> choices) {
        super(id, model, choices);
    }

    public AjaxButtonRadioChoice(String id, IModel<T> model, List<? extends T> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    public AjaxButtonRadioChoice(String id, IModel<? extends List<? extends T>> choices) {
        super(id, choices);
    }

    public AjaxButtonRadioChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices) {
        super(id, model, choices);
    }

    public AjaxButtonRadioChoice(String id, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public AjaxButtonRadioChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.remove("data-toggle");
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(newUpdatingBehavior());
    }

    /**
     * Creates the Behavior that should call {@link #onSelectionChange}.
     * 
     * @return a new behavior instance
     */
    protected Behavior newUpdatingBehavior() {
        return new AjaxFormChoiceComponentUpdatingBehavior() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                AjaxButtonRadioChoice.this.onSelectionChange(getModelObject(), target);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);

                attributes.getAjaxCallListeners().add(new AjaxCallListener(new JsAjaxCallListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public IJavaScript getBeforeHandler(Component component) {
                        JsIdentifier currentTarget = new JsIdentifier("attrs.event.currentTarget");
                        JsIdentifier target = new JsIdentifier("attrs.event.target");

                        return new JsStatements($("label", currentTarget).removeClass("active"), $(target).parent()
                                .addClass("active"));
                    }
                }));
            }
        };
    }

    /**
     * Called when the selection changes.
     *
     * @param selection the new selection
     * 
     * @param target the ajax request target
     */
    protected abstract void onSelectionChange(T selection, AjaxRequestTarget target);
}
