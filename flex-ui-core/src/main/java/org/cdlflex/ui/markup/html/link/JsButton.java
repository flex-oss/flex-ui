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
package org.cdlflex.ui.markup.html.link;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.markup.css.Buttons.Size;
import org.cdlflex.ui.markup.css.Buttons.Type;
import org.rauschig.wicketjs.markup.html.JsLink;

/**
 * A JsLink that provides accessors to Bootstrap's Buttons API and contains a ButtonBehavior.
 */
public abstract class JsButton extends JsLink {

    private static final long serialVersionUID = 1L;

    private static final Size DEFAULT_SIZE = Size.MEDIUM;
    private static final Type DEFAULT_TYPE = Type.DEFAULT;

    private IModel<Size> size;
    private IModel<Type> type;

    public JsButton(String id) {
        this(id, DEFAULT_TYPE);
    }

    public JsButton(String id, IModel<?> model) {
        this(id, DEFAULT_TYPE, DEFAULT_SIZE, model);
    }

    public JsButton(String id, Type type) {
        this(id, type, DEFAULT_SIZE, null);
    }

    public JsButton(String id, Size size) {
        this(id, DEFAULT_TYPE, size, null);
    }

    public JsButton(String id, Type type, Size size) {
        this(id, type, size, null);
    }

    public JsButton(String id, Type type, Size size, IModel<?> model) {
        this(id, Model.of(type), Model.of(size), model);
    }

    public JsButton(String id, IModel<Type> type, IModel<Size> size, IModel<?> model) {
        super(id, model);
        this.type = type;
        this.size = size;

        add(new ButtonBehavior(type, size));
    }

    @Override
    public JsButton setBody(IModel<?> bodyModel) {
        return (JsButton) super.setBody(bodyModel);
    }

    /**
     * Sets the size of this button.
     *
     * @param option the button size
     * @return this for chaining
     */
    public JsButton size(Size option) {
        getSize().setObject(option);
        return this;
    }

    /**
     * Sets the type of this button.
     *
     * @param option the button type
     * @return this for chaining
     */
    public JsButton type(Type option) {
        getType().setObject(option);
        return this;
    }

    public IModel<Size> getSize() {
        return size;
    }

    public void setSize(IModel<Size> size) {
        this.size = size;
    }

    public IModel<Type> getType() {
        return type;
    }

    public void setType(IModel<Type> type) {
        this.type = type;
    }

}
