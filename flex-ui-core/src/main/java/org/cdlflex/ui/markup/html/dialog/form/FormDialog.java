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
package org.cdlflex.ui.markup.html.dialog.form;

import static org.rauschig.wicketjs.jquery.JQuery.$;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.html.dialog.Dialog;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.markup.html.JsLink;

/**
 * A Dialog that is expected to hold a form.
 * <p/>
 * It autonomously adds two buttons to the dialog, a close and submit button, for which the display models can be
 * controlled. The form returned by {@code #newForm(IModel)} is added by the Dialog, but it is expected that the
 * extended dialog template provides the markup and wicket-id for the form itself. (This is necessary because of markup
 * boundaries of dialog and form. The form is inside of the modal-body but the buttons are required to be in the
 * modal-footer.)
 * 
 * @param <T> The dialog and form model object type
 */
public abstract class FormDialog<T> extends Dialog<T> {

    private static final long serialVersionUID = 1L;

    private Form<T> form;

    public FormDialog(String markupId) {
        this(markupId, null, null);
    }

    public FormDialog(String id, IModel<T> model) {
        this(id, null, model);
    }

    public FormDialog(String id, IModel<?> header, IModel<T> model) {
        super(id, header, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        form = newForm(getModel());
        add(form);

        addCloseButton(getCloseButtonModel());
        addButton(newSubmitButton(BUTTON_MARKUP_ID, getModel(), getForm()));
    }

    public Form<T> getForm() {
        return form;
    }

    /**
     * Creates a new submit button, can be overridden by subclasses to provide their own submit button. By default, this
     * is a JsButton that triggers the submit event on the provided form.
     * 
     * @param id the button component id
     * @param model the model object
     * @param formComponent the form
     * @return a new submit button
     */
    protected AbstractLink newSubmitButton(String id, IModel<T> model, final Form<T> formComponent) {
        return new JsLink(id) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new ButtonBehavior(Buttons.Type.PRIMARY));
            }

            @Override
            public IJavaScript onClick() {
                return $(formComponent).call("submit");
            }
        }.setBody(getSubmitButtonModel());
    }

    /**
     * Returns the display model of the submit button.
     * 
     * @return a display model
     */
    protected IModel<String> getSubmitButtonModel() {
        return Model.of("Submit");
    }

    /**
     * Returns the display model of the close button.
     * 
     * @return a display model
     */
    protected IModel<String> getCloseButtonModel() {
        return Model.of("Cancel");
    }

    /**
     * Creates the form that this dialog holds. It is added by the Dialog, but it is expected that the extended dialog
     * template provides the markup and wicket-id for the form.
     * 
     * @param model the model object
     * @return a new form instance
     */
    protected abstract Form<T> newForm(IModel<T> model);
}
