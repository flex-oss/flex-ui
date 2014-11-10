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

import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.behavior.dialog.DialogCloseBehavior;
import org.cdlflex.ui.markup.css.Buttons;
import org.rauschig.wicketjs.markup.html.AbstractJsLink;

/**
 * A Link intended to close a bootstrap Modal. Will only work properly in context of a Dialog.
 */
public class DialogCloseLink extends AbstractJsLink {
    private static final long serialVersionUID = 1L;

    private ButtonBehavior buttonBehavior;

    public DialogCloseLink(String id) {
        this(id, null);
    }

    public DialogCloseLink(String id, IModel<?> model) {
        super(id, model);

        buttonBehavior = new ButtonBehavior(Buttons.Type.DEFAULT);
        add(buttonBehavior);

        add(new DialogCloseBehavior());
    }

    /**
     * Sets the button type.
     * 
     * @param type The button type to use
     * @return this for chaining
     */
    public DialogCloseLink setType(final Buttons.Type type) {
        buttonBehavior.setType(type);
        return this;
    }

    /**
     * Sets the button size.
     * 
     * @param size The button size
     * @return this for chaining
     */
    public DialogCloseLink setSize(final Buttons.Size size) {
        buttonBehavior.setSize(size);
        return this;
    }

}
