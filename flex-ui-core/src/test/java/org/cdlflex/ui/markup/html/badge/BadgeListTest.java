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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.DummyHomePage;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.cdlflex.ui.AbstractWicketPageTest;
import org.junit.Before;
import org.junit.Test;

public class BadgeListTest extends AbstractWicketPageTest {

    BadgeList badgeList;

    TagTester item0;
    TagTester item1;
    TagTester item2;
    TagTester item3;

    @Before
    public void setUp() throws Exception {
        List<BadgeListItem> items = new ArrayList<>();

        // only body model
        items.add(new BadgeListItem(Model.of(42)));

        // body and label model
        items.add(new BadgeListItem(Model.of(42), Model.of("Answer")));

        // body model and link
        items.add(new BadgeListItem(Model.of(42)) {
            private static final long serialVersionUID = 1L;

            @Override
            public AbstractLink createLink(String id) {
                return new BookmarkablePageLink<Void>(id, DummyHomePage.class);
            }
        });
        // body and label model and link
        items.add(new BadgeListItem(Model.of(42), Model.of("Answer")) {
            private static final long serialVersionUID = 1L;

            @Override
            public AbstractLink createLink(String id) {
                return new BookmarkablePageLink<Void>(id, DummyHomePage.class);
            }
        });

        badgeList = new BadgeList("badge-list", items) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onBeforeRender() {
                // hack for allowing access in TagTester
                super.onBeforeRender();
                visitChildren(new IVisitor<Component, Void>() {
                    int cnt = 0;

                    @Override
                    public void component(Component component, IVisit<Void> visit) {
                        component.setOutputMarkupId(true);
                        if (component instanceof ListItem) {
                            component.setMarkupId("item-" + (cnt++));
                        }
                    }
                });
            }
        };

        String markup = ComponentRenderer.renderComponent(badgeList).toString();
        TagTester tag = TagTester.createTagByAttribute(markup, "wicket:id", "badge-list");

        item0 = tag.getChild("id", "item-0");
        item1 = tag.getChild("id", "item-1");
        item2 = tag.getChild("id", "item-2");
        item3 = tag.getChild("id", "item-3");
    }

    @Test
    public void items_notNull() throws Exception {
        assertNotNull(item0);
        assertNotNull(item1);
        assertNotNull(item2);
        assertNotNull(item3);
    }

    @Test
    public void badgeItem_onlyBodyModel_rendersCorrectly() throws Exception {
        assertNull(item0.getChild("wicket:id", "link"));

        TagTester label = item0.getChild("wicket:id", "label");
        assertNull(label);

        TagTester badge = item0.getChild("wicket:id", "badge");
        assertNotNull(badge);
        assertEquals("badge", badge.getAttribute("class"));
        assertEquals("42", badge.getValue());
    }

    @Test
    public void badgeItem_bodyAndLabelModel_rendersCorrectly() throws Exception {
        assertNull(item1.getChild("wicket:id", "link"));

        TagTester label = item1.getChild("wicket:id", "label");
        assertNotNull(label);
        assertEquals("Answer", label.getValue());

        TagTester badge = item1.getChild("wicket:id", "badge");
        assertNotNull(badge);
        assertEquals("badge", badge.getAttribute("class"));
        assertEquals("42", badge.getValue());
    }

    @Test
    public void badgeItem_bodyModelAndLink_rendersCorrectly() throws Exception {
        TagTester link = item2.getChild("wicket:id", "link");
        assertNotNull(link);
        assertNotNull(link.getAttribute("href"));

        TagTester label = link.getChild("wicket:id", "label");
        assertNull(label);

        TagTester badge = link.getChild("wicket:id", "badge");
        assertNotNull(badge);
        assertEquals("badge", badge.getAttribute("class"));
        assertEquals("42", badge.getValue());
    }

    @Test
    public void badgeItem_bodyAndLabelModelAndLink_rendersCorrectly() throws Exception {
        TagTester link = item3.getChild("wicket:id", "link");
        assertNotNull(link);
        assertNotNull(link.getAttribute("href"));

        TagTester label = link.getChild("wicket:id", "label");
        assertNotNull(label);
        assertEquals("Answer", label.getValue());

        TagTester badge = link.getChild("wicket:id", "badge");
        assertNotNull(badge);
        assertEquals("badge", badge.getAttribute("class"));
        assertEquals("42", badge.getValue());
    }

    @Test
    public void setModels_rendersUpdatedModels() throws Exception {
        BadgeListItem item = new BadgeListItem();
        badgeList.add(item);

        item.setBadgeModel(Model.of(43));
        item.setLabelModel(Model.of("Label"));

        String markup = ComponentRenderer.renderComponent(badgeList).toString();
        TagTester tag = TagTester.createTagByAttribute(markup, "wicket:id", "badge-list");

        TagTester item4 = tag.getChild("id", "item-4");
        assertNull(item4.getChild("wicket:id", "link"));

        TagTester label = item4.getChild("wicket:id", "label");
        assertNotNull(label);
        assertEquals("Label", label.getValue());

        TagTester badge = item4.getChild("wicket:id", "badge");
        assertNotNull(badge);
        assertEquals("badge", badge.getAttribute("class"));
        assertEquals("43", badge.getValue());
    }

}
