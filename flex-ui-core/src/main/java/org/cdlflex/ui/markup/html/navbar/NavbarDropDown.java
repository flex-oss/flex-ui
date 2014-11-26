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
package org.cdlflex.ui.markup.html.navbar;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.css.Buttons.Size;
import org.cdlflex.ui.markup.css.Buttons.Type;
import org.cdlflex.ui.markup.html.button.DropDownButton;

/**
 * Wraps a DropDownButton to be used within the Navbar.
 */
public abstract class NavbarDropDown extends AbstractNavbarComponent<DropDownButton> {

    private IModel<?> displayModel;

    protected NavbarDropDown(IModel<?> displayModel) {
        this.displayModel = displayModel;
    }

    public NavbarDropDown(Position position, IModel<?> displayModel) {
        super(position);
        this.displayModel = displayModel;
    }

    @Override
    public DropDownButton create(String id) {
        return new DropDownButton(id, displayModel) {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                return NavbarDropDown.this.newSubMenuButtons(buttonMarkupId);
            }

            @Override
            protected void addButtonBehavior(IModel<Type> type, IModel<Size> size) {
                // do not add button behavior to dropdown button in navbar
            }
        };
    }

    @Override
    public void onAfterPopulateItem(ListItem<INavbarComponent<? extends Component>> item) {
        item.add(new CssClassNameAppender("dropdown"));
    }

    /**
     * Propagated call from {@link org.cdlflex.ui.markup.html.button.DropDownButton#newSubMenuButtons(String)}.
     * 
     * @param id the markup id used for creating links
     * @return a list of links to be rendered in the dropdown
     */
    protected abstract List<AbstractLink> newSubMenuButtons(String id);
}
