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

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.html.nav.Navbar;
import org.cdlflex.ui.markup.html.nav.NavbarBookmarkablePageLink;
import org.cdlflex.ui.markup.html.nav.NavbarDropDown;
import org.cdlflex.ui.markup.html.nav.NavbarLink;
import org.cdlflex.ui.markup.html.nav.Position;
import org.cdlflex.ui.pages.ExamplePage;
import org.cdlflex.ui.pages.HomePage;

public class NavbarPage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    private boolean stripTags;

    public NavbarPage() {
        stripTags = Application.get().getMarkupSettings().getStripWicketTags();

        Navbar navbar;
        add(navbar = new Navbar("navbar") {
            private static final long serialVersionUID = 1L;

            @Override
            protected Component newBrandLink(String id) {
                return new BookmarkablePageLink<HomePage>(id, HomePage.class).setBody(Model.of("Brand model"));
            }
        });

        navbar.add(new NavbarLink<AbstractLink>() {
            @Override
            public AbstractLink create(String id) {
                return new BookmarkablePageLink<>(id, NavbarPage.class).setBody(Model.of("Navbars"));
            }
        });

        navbar.add(new NavbarBookmarkablePageLink(ButtonsPage.class, Model.of("Buttons")));

        navbar.add(new NavbarDropDown(Model.of("Dropdown")) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(String id) {
                return Arrays.asList(new BookmarkablePageLink<>(id, NavbarPage.class).setBody(Model.of("Navbars")),
                        new BookmarkablePageLink<>(id, ButtonsPage.class).setBody(Model.of("Buttons")));
            }
        }.setIconType(GlyphIconType.ASTERISK));

        navbar.add(new NavbarDropDown(Position.RIGHT, Model.of("Dropdown")) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(String id) {
                return Arrays.asList(new BookmarkablePageLink<>(id, NavbarPage.class).setBody(Model.of("Navbars")),
                        new BookmarkablePageLink<>(id, ButtonsPage.class).setBody(Model.of("Buttons")));
            }
        });
        navbar.add(new NavbarBookmarkablePageLink(Position.RIGHT, AlertsPage.class, Model.of("Alerts"))
                .setIconType(GlyphIconType.BELL));
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        Application.get().getMarkupSettings().setStripWicketTags(true);
    }

    @Override
    protected void onAfterRender() {
        super.onAfterRender();
        Application.get().getMarkupSettings().setStripWicketTags(stripTags);
    }
}
