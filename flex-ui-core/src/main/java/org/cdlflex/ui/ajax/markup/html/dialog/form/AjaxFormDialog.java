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
package org.cdlflex.ui.ajax.markup.html.dialog.form;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.html.dialog.form.FormDialog;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.ajax.AjaxCallListener;
import org.rauschig.wicketjs.ajax.DelegatingAjaxCallListener;
import org.rauschig.wicketjs.ajax.IAjaxCallAware;
import org.rauschig.wicketjs.jquery.JQuery;

/**
 * A customized FormDialog that uses an AjaxSubmitLink to submit the button. It is IAjaxCallAware, and is a delegate for
 * the AjaxCallListener of the submit button.
 * 
 * @param <T> The dialog and form model object type
 */
public abstract class AjaxFormDialog<T> extends FormDialog<T> implements IAjaxCallAware {

    private static final long serialVersionUID = 1L;

    public AjaxFormDialog(String markupId) {
        super(markupId);
    }

    public AjaxFormDialog(String id, IModel<T> model) {
        super(id, model);
    }

    public AjaxFormDialog(String id, IModel<String> header, IModel<T> model) {
        super(id, header, model);
    }

    @Override
    protected AbstractLink newSubmitButton(String id, IModel<T> model, Form<T> formComponent) {
        return new AjaxSubmitLink(id, formComponent) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new ButtonBehavior(Buttons.Type.PRIMARY));
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);

                AjaxFormDialog dialog = AjaxFormDialog.this;
                dialog.updateAjaxAttributes(this, attributes);
                attributes.getAjaxCallListeners().add(new AjaxCallListener(new DelegatingAjaxCallListener(dialog)));
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                AjaxFormDialog.this.onSubmit(target, (Form<T>) form);
            }
        }.setBody(getSubmitButtonModel());
    }

    /**
     * Update the AjaxRequestAttributes of the AjaxSubmitLink.
     * 
     * @param submitLink the submit button
     * @param attributes ajax request attributes
     */
    protected void updateAjaxAttributes(AjaxSubmitLink submitLink, AjaxRequestAttributes attributes) {
        // hook
    }

    @Override
    public IJavaScript onTrigger() {
        return null;
    }

    @Override
    public IJavaScript onBefore() {
        return null;
    }

    @Override
    public IJavaScript onSuccess() {
        // hide the dialog if the ajax call was successful
        return JQuery.$(this).call("modal", "hide");
    }

    @Override
    public IJavaScript onFail() {
        return null;
    }

    @Override
    public IJavaScript precondition() {
        return null;
    }

    @Override
    public IJavaScript onAfter() {
        return null;
    }

    @Override
    public IJavaScript onComplete() {
        return null;
    }

    /**
     * Called when the submit link is clicked.
     * 
     * @param target ajax request target
     * @param form form being submitted
     */
    protected abstract void onSubmit(AjaxRequestTarget target, Form<T> form);

}
