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

import java.util.List;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.cdlflex.ui.behavior.CssClassNameAppender;

/**
 * A {@link GenericPanel} that holds a ListView of {@link BadgeListItem}. By default, it wraps the link created by
 * {@link BadgeListItem#createLink(String)} around the Label and {@link org.cdlflex.ui.markup.html.badge.Badge} created
 * by the other factory methods in {@link BadgeListItem}.
 * <p/>
 * By default, the {@link BadgeListItem#createLink(String)} creates a {@code org.cdlflex.ui.markup.html.link.DummyLink}
 * to disable the Link. You can control the link creation by overwriting that method of a specific item.
 * 
 * <pre>
 *     &lt;li wicket:id="badges"&gt;
 *       &lt;a wicket:id="link" class="badge-item-link"&gt;
 *         &lt;span wicket:id="label" class="badge-item-label"&gt;&lt;/span&gt; &lt;span wicket:id="badge"&gt;&lt;/span&gt;
 *       &lt;/a&gt;
 *     &lt;/li&gt;
 * </pre>
 */
public class BadgeList extends GenericPanel<List<? extends BadgeListItem>> {
    private static final long serialVersionUID = 1L;

    public BadgeList(String id) {
        super(id);
    }

    @SuppressWarnings("unchecked")
    public BadgeList(String id, List<? extends BadgeListItem> list) {
        super(id, (IModel<List<? extends BadgeListItem>>) new ListModel(list));
    }

    public BadgeList(String id, IModel<List<? extends BadgeListItem>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(newBadgeListView("badges", getModel()));
        add(new CssClassNameAppender("badge-list"));
    }

    @SuppressWarnings("unchecked")
    public BadgeList add(BadgeListItem item) {
        List<BadgeListItem> list = (List<BadgeListItem>) getModelObject();
        list.add(item);

        return this;
    }

    /**
     * Factory method for the ListView that uses the given BadgeList instance model (a list of {@link BadgeListItem} to
     * render a list of bootstrap badges.
     * 
     * @param id the component id
     * @param model The list model if BadgeListItem to render
     * @return a new ListView
     */
    protected ListView<BadgeListItem> newBadgeListView(String id, IModel<List<? extends BadgeListItem>> model) {
        return new ListView<BadgeListItem>(id, model) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BadgeListItem> item) {
                BadgeList.this.populateItem(item);
            }
        };
    }

    /**
     * Called by the ListView created by default via {@link #newBadgeListView(String, IModel)}. It populates the item
     * with the link returned by {@link BadgeListItem#createLink(String)}, to which subsequently the Badge and Label is
     * added.
     * 
     * @param item the specific list item.
     */
    protected void populateItem(ListItem<BadgeListItem> item) {
        BadgeListItem model = item.getModelObject();

        AbstractLink link = item.getModelObject().createLink("link");
        link.add(model.createBadge("badge"));
        link.add(model.createLabel("label"));

        item.add(link);
    }
}
