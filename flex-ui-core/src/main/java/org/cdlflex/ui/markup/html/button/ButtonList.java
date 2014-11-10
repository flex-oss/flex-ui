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
package org.cdlflex.ui.markup.html.button;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;

/**
 * A {@link ListView} of {@link AbstractLink}. Adapted from de.agilecoders wicket-bootstrap
 */
public class ButtonList extends ListView<AbstractLink> {

    private static final long serialVersionUID = 1L;

    public static final String BUTTON_MARKUP_ID = "button";

    /**
     * Construct.
     *
     * @param markupId the component' id
     * @param list list of all buttons inside this button list
     */
    public ButtonList(final String markupId, final List<? extends AbstractLink> list) {
        super(markupId, list);

        setOutputMarkupId(true);
    }

    /**
     * Construct.
     *
     * @param id the component' id
     * @param model list model of all buttons inside this button list
     */
    public ButtonList(final String id, final IModel<List<? extends AbstractLink>> model) {
        super(id, model);

        setOutputMarkupId(true);
    }

    /**
     * checks whether there is a button that is active or not.
     *
     * @param activeButton the current active button
     * @return true, if at least one button of button list is active
     */
    public final boolean hasActiveButton(final Component activeButton) {
        final Class<? extends Page> pageClass = activeButton.getPage().getPageClass();

        for (final AbstractLink link : getList()) {
            if (link instanceof IActivatable) {
                if (((IActivatable) link).isActive(activeButton)) {
                    return true;
                }
            } else if (link instanceof BookmarkablePageLink) {
                if (((BookmarkablePageLink<?>) link).getPageClass().equals(pageClass)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    protected void populateItem(ListItem<AbstractLink> item) {
        final AbstractLink link = item.getModelObject();

        Args.isTrue(getButtonMarkupId().equals(link.getId()),
                "component id is invalid, please use ButtonList.getButtonMarkupId()");

        item.add(link);
    }

    /**
     * Returns the markup id used for button list elements.
     *
     * @return the markup id that is used for buttons in the list
     */
    public static String getButtonMarkupId() {
        return BUTTON_MARKUP_ID;
    }

}
