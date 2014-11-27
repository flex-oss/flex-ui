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

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.cdlflex.ui.markup.html.link.BookmarkablePageButton;

/**
 * A NavbarLink that generates a {@code org.apache.wicket.markup.html.link.BookmarkablePageLink} from the given
 * parameters.
 */
public class NavbarBookmarkablePageLink extends NavbarLink<AbstractLink> {

    private Class<? extends Page> pageClass;
    private IModel<?> displayModel;
    private PageParameters parameters;
    private IconType iconType;

    public NavbarBookmarkablePageLink(Class<? extends Page> pageClass, IModel<?> displayModel) {
        super();
        this.pageClass = pageClass;
        this.displayModel = displayModel;
    }

    public NavbarBookmarkablePageLink(Position position, Class<? extends Page> pageClass, IModel<?> displayModel) {
        super(position);
        this.pageClass = pageClass;
        this.displayModel = displayModel;
    }

    @Override
    public AbstractLink create(String id) {
        BookmarkablePageButton<?> button = new BookmarkablePageButton<>(id, getPageClass(), getParameters());
        button.setType(Buttons.Type.NONE).setIconType(getIconType()).setBody(getDisplayModel());
        return button.setBody(getDisplayModel());
    }

    public Class<? extends Page> getPageClass() {
        return pageClass;
    }

    public NavbarBookmarkablePageLink setPageClass(Class<? extends Page> pageClass) {
        this.pageClass = pageClass;
        return this;
    }

    public IModel<?> getDisplayModel() {
        return displayModel;
    }

    public NavbarBookmarkablePageLink setDisplayModel(IModel<?> displayModel) {
        this.displayModel = displayModel;
        return this;
    }

    public PageParameters getParameters() {
        return parameters;
    }

    public NavbarBookmarkablePageLink setParameters(PageParameters parameters) {
        this.parameters = parameters;
        return this;
    }

    public IconType getIconType() {
        return iconType;
    }

    public NavbarBookmarkablePageLink setIconType(IconType iconType) {
        this.iconType = iconType;
        return this;
    }
}
