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
package org.cdlflex.ui.model;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.css.Buttons.Size;
import org.cdlflex.ui.markup.css.Buttons.Type;
import org.cdlflex.ui.util.Strings;

/**
 * Concatenates Type and Size css class names and any others required for rendering bootstrap buttons by Type and Size.
 * Using {@link Type#NONE} will cause no css classes to be returned.
 */
public class ButtonCssClassNameModel extends AbstractReadOnlyModel<String> {

    private static final long serialVersionUID = 1L;

    private IModel<Type> buttonType;
    private IModel<Size> buttonSize;

    public ButtonCssClassNameModel(IModel<Type> buttonType, IModel<Size> buttonSize) {
        this.buttonType = buttonType;
        this.buttonSize = buttonSize;
    }

    @Override
    public String getObject() {
        if (buttonType.getObject() == Type.NONE) {
            return "";
        }

        String size = buttonSize.getObject().getCssClassName();
        String type = buttonType.getObject().getCssClassName();

        return Strings.join(" ", Buttons.CSS_CLASS, size, type);
    }

    public IModel<Type> getButtonType() {
        return buttonType;
    }

    public void setButtonType(IModel<Type> buttonType) {
        this.buttonType = buttonType;
    }

    public IModel<Size> getButtonSize() {
        return buttonSize;
    }

    public void setButtonSize(IModel<Size> buttonSize) {
        this.buttonSize = buttonSize;
    }
}
