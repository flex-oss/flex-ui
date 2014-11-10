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
package org.cdlflex.ui.markup.html.dialog;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.rauschig.wicketjs.markup.html.AbstractJsLink;

/**
 * This link opens a given Dialog.
 */
public class DialogOpenLink extends AbstractJsLink {
    private static final long serialVersionUID = 1L;

    private Component dialog;

    public DialogOpenLink(String id) {
        super(id);
    }

    public DialogOpenLink(String id, IModel<?> model) {
        super(id, model);
    }

    public DialogOpenLink(String id, Component dialog) {
        this(id, null, dialog);
    }

    public DialogOpenLink(String id, IModel<?> model, Component dialog) {
        super(id, model);
        this.dialog = dialog;
    }

    /**
     * Sets the dialog this link opens.
     * 
     * @param component the dialog component
     * @return this for chaining
     */
    public DialogOpenLink setDialog(Component component) {
        this.dialog = component;
        return this;
    }

    public Component getDialog() {
        return dialog;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        if (isLinkEnabled()) {
            Component component = getDialog();

            tag.put("data-toggle", "modal");
            if (component != null) {
                tag.put("data-target", "#" + component.getMarkupId());
            }
        }
    }
}
