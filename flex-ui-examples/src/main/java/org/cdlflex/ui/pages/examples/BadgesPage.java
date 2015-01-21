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
package org.cdlflex.ui.pages.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.html.badge.Badge;
import org.cdlflex.ui.markup.html.badge.BadgeList;
import org.cdlflex.ui.markup.html.badge.BadgeListItem;

/**
 * Shows examples for static Badges and dynamic BadgeLists.
 */
public class BadgesPage extends StripTagExamplePage {
    private static final long serialVersionUID = 1L;

    public BadgesPage() {
        add(new Badge("inbox", Model.of(42)));
        add(new Badge("messages", Model.of(4)));

        add(newBadgeList("badge-list"));
        add(newBadgeListPills("badge-list-pills"));
    }

    protected BadgeList newBadgeList(String id) {
        return new BadgeList(id, Arrays.asList(new BadgeListItem(Model.of(42), Model.of("Answer")),
                new BadgeListItem(Model.of("Some string")), new BadgeListItem(Model.of(9), Model.of("Alerts")) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public AbstractLink createLink(String id) {
                        return new BookmarkablePageLink<Void>(id, AlertsPage.class);
                    }
                }, new BadgeListItem(Model.of(8), Model.of("Alerts")) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public AbstractLink createLink(String id) {
                        return new AjaxLink<Void>(id) {
                            private static final long serialVersionUID = 1L;

                            @Override
                            protected void onInitialize() {
                                super.onInitialize();
                                add(new ButtonBehavior(Buttons.Type.PRIMARY, Buttons.Size.MEDIUM));
                            }

                            @Override
                            public void onClick(AjaxRequestTarget target) {
                                setResponsePage(AlertsPage.class);
                            }
                        };
                    }
                }));
    }

    protected BadgeList newBadgeListPills(String id) {
        List<BadgeListItem> list = new ArrayList<>();

        list.add(new BadgeListItem(Model.of(42), Model.of("Home")) {
            private static final long serialVersionUID = 1L;

            @Override
            public AbstractLink createLink(String id) {
                return new BookmarkablePageLink<>(id, ExamplesHomePage.class);
            }
        });
        list.add(new BadgeListItem(Model.of(9), Model.of("Alerts")) {
            private static final long serialVersionUID = 1L;

            @Override
            public AbstractLink createLink(String id) {
                return new BookmarkablePageLink<>(id, AlertsPage.class);
            }
        });

        return new BadgeList(id, list) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<BadgeListItem> item) {
                super.populateItem(item);
                item.add(new AttributeAppender("role", "presentation"));
            }
        };
    }
}
