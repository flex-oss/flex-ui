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
package org.cdlflex.ui.markup.html.badge;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;
import org.cdlflex.ui.markup.html.link.DummyLink;

/**
 * List items for the ListView used in {@code org.cdlflex.ui.markup.html.badge.BadgeList}. It also acts as a factory for
 * creating the various components required by rendering an item in a BadgeList.
 */
public class BadgeListItem implements IClusterable {

    private static final long serialVersionUID = 1L;

    private IModel<?> badgeModel;
    private IModel<?> labelModel;

    public BadgeListItem() {
        this(null);
    }

    /**
     * Construct.
     * 
     * @param badgeModel the display model shown inside the badge
     */
    public BadgeListItem(IModel<?> badgeModel) {
        this(badgeModel, null);
    }

    /**
     * Construct.
     * 
     * @param badgeModel the display model shown inside the badge
     * @param labelModel the display model shown as a label beside the badge
     */
    public BadgeListItem(IModel<?> badgeModel, IModel<?> labelModel) {
        this.badgeModel = badgeModel;
        this.labelModel = labelModel;
    }

    /**
     * Creates the Link which wraps the Badge and the optional Label. By default, this returns a {@link DummyLink} that
     * only renders the body (and no link). If you want to navigate somewhere when the Badge item is clicked, overwrite
     * this method and return an appropriate AbstractLink component.
     * 
     * @param id The component id used for the AbstractLink
     * @return A new AbstractLink
     */
    public AbstractLink createLink(String id) {
        return new DummyLink(id);
    }

    /**
     * Creates the BadgeComponent. By default, it uses the return value of {@link #getBadgeModel()} as constructor
     * parameter for the Badge, which should not be null.
     * 
     * @param id The component id
     * @return A new Badge
     */
    public Badge createBadge(String id) {
        return new Badge(id, getBadgeModel());
    }

    /**
     * Creates the (optional) Label rendered beside the Badge. If the return value of {@link #getLabelModel()} is null
     * or empty, the Label visibility is set to false.
     * 
     * @param id The component id
     * @return A new Label
     */
    public Label createLabel(String id) {
        IModel<?> model = getLabelModel();
        Label label = new Label(id, model);

        if (model == null) {
            label.setVisible(false);
        }

        return label;
    }

    public IModel<?> getBadgeModel() {
        return badgeModel;
    }

    public void setBadgeModel(IModel<?> badgeModel) {
        this.badgeModel = badgeModel;
    }

    public IModel<?> getLabelModel() {
        return labelModel;
    }

    public void setLabelModel(IModel<?> labelModel) {
        this.labelModel = labelModel;
    }

}
