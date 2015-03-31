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
package org.cdlflex.ui.markup.html.nav;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.css.Buttons.Type;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.cdlflex.ui.markup.html.button.DropDownButton;

/**
 * Wraps a DropDownButton to be used within the Navbar.
 */
public abstract class NavbarDropDown extends AbstractNavbarComponent<DropDownButton> {

    private static final long serialVersionUID = 1L;

    private IModel<?> displayModel;
    private IModel<IconType> iconType;

    public NavbarDropDown(IModel<?> displayModel) {
        this(displayModel, null);
    }

    protected NavbarDropDown(IModel<?> displayModel, IModel<IconType> iconType) {
        super();
        this.displayModel = displayModel;
        this.iconType = iconType;
    }

    public NavbarDropDown(Position position, IModel<?> displayModel) {
        this(position, displayModel, null);
    }

    protected NavbarDropDown(Position position, IModel<?> displayModel, IModel<IconType> iconType) {
        super(position);
        this.displayModel = displayModel;
        this.iconType = iconType;
    }

    @Override
    public DropDownButton create(String id) {
        return new DropDownButton(id, displayModel, iconType) {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                return NavbarDropDown.this.newSubMenuButtons(buttonMarkupId);
            }

        }.setType(Type.NONE);
    }

    @Override
    public void onAfterPopulateItem(ListItem<INavbarComponent<? extends Component>> item) {
        item.add(new CssClassNameAppender("dropdown"));
    }

    public IModel<IconType> getIconType() {
        return iconType;
    }

    public NavbarDropDown setIconType(IModel<IconType> iconType) {
        this.iconType = iconType;
        return this;
    }

    public NavbarDropDown setIconType(IconType iconType) {
        if (this.iconType == null) {
            setIconType(Model.of(iconType));
        } else {
            this.iconType.setObject(iconType);
        }
        return this;
    }

    /**
     * Propagated call from {@link org.cdlflex.ui.markup.html.button.DropDownButton#newSubMenuButtons(String)}.
     * 
     * @param id the markup id used for creating links
     * @return a list of links to be rendered in the dropdown
     */
    protected abstract List<AbstractLink> newSubMenuButtons(String id);
}
