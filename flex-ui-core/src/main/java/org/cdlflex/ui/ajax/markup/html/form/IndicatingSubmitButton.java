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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.ajax.AjaxCallListener;
import org.rauschig.wicketjs.ajax.DelegatingAjaxCallListener;
import org.rauschig.wicketjs.ajax.IAjaxCallAware;
import org.rauschig.wicketjs.ajax.JsAjaxCallListener;

/**
 * An AjaxSubmitLink that uses bootstrap's <code>button</code> JavaScript to load a default loading text.
 *
 * @see org.cdlflex.ui.ajax.IndicatingButton
 */
public abstract class IndicatingSubmitButton extends AjaxSubmitLink implements IAjaxCallAware {
    private static final long serialVersionUID = 1L;

    private IModel<String> loadingDisplayModel;

    public IndicatingSubmitButton(String id) {
        super(id);
    }

    public IndicatingSubmitButton(String id, Form<?> form) {
        super(id, form);
    }

    public IndicatingSubmitButton(String id, Form<?> form, IModel<String> loadingDisplayModel) {
        super(id, form);
        this.loadingDisplayModel = loadingDisplayModel;
    }

    /**
     * Sets the model used for showing the loading text.
     *
     * @param model a string model
     * @return this for chaining
     */
    public IndicatingSubmitButton setLoadingDisplayModel(IModel<String> model) {
        this.loadingDisplayModel = model;
        return this;
    }

    /**
     * Returns the model used for showing the loading text.
     *
     * @return a string model
     */
    public IModel<String> getLoadingDisplayModel() {
        return loadingDisplayModel;
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
        return null;
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

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);

        attributes.getAjaxCallListeners().add(new AjaxCallListener(new JsAjaxCallListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public IJavaScript getBeforeHandler(Component component) {
                return $(component).call("button", "loading");
            }

            @Override
            public IJavaScript getCompleteHandler(Component component) {
                return $(component).call("button", "reset");
            }
        }));
        attributes.getAjaxCallListeners().add(new AjaxCallListener(new DelegatingAjaxCallListener(this)));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        String loadingText = getLoadingText();

        if (loadingText != null) {
            tag.put("data-loading-text", loadingText);
        }
    }

    /**
     * Loads the model object from the loadingDisplayModel.
     *
     * @return a string to show as loading text
     */
    private String getLoadingText() {
        IModel<String> model = getLoadingDisplayModel();
        if (model == null) {
            return null;
        }
        return model.getObject();
    }

}
