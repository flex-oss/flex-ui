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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.DummyHomePage;
import org.apache.wicket.util.tester.DummyPanelPage;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.junit.Test;

public class NavbarDropDownTest extends AbstractWicketTest {

    @Test
    public void rendersDropDownCorrectly() throws Exception {
        List<INavbarComponent<? extends Component>> components = new ArrayList<>();

        components.add(new NavbarDropDown(Model.of("DropDown"), new Model<IconType>(GlyphIconType.COG)) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(String id) {
                return Arrays.asList(
                        new BookmarkablePageLink<Void>(id, DummyHomePage.class).setBody(Model.of("Home page")),
                        new BookmarkablePageLink<Void>(id, DummyPanelPage.class).setBody(Model.of("Panel page")));
            }
        });

        Navbar navbar = new Navbar("navbar", Model.of(), components);

        TagTester container = renderAndCreateTag(navbar);

        TagTester component = container.getChild("wicket:id", "nav-component");
        assertEquals("dropdown", component.getAttribute("class"));

        assertEquals("a", component.getChild("wicket:id", "btn").getName());

        TagTester menu = component.getChild("wicket:id", "dropdown-menu");
        assertEquals("ul", menu.getName());

        assertTrue(menu.getMarkup().contains("DummyHomePage"));
        assertTrue(menu.getMarkup().contains("DummyPanelPage"));
        assertTrue(menu.getMarkup().contains("Home page</a>"));
        assertTrue(menu.getMarkup().contains("Panel page</a>"));
    }

    @Test
    public void setIconType_rendersCorrectIconInDropDown() throws Exception {
        List<INavbarComponent<? extends Component>> components = new ArrayList<>();
        NavbarDropDown dropDown = new NavbarDropDown(Model.of("DropDown")) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(String id) {
                return Arrays.asList(
                        new BookmarkablePageLink<Void>(id, DummyHomePage.class).setBody(Model.of("Home page")),
                        new BookmarkablePageLink<Void>(id, DummyPanelPage.class).setBody(Model.of("Panel page")));
            }
        };
        components.add(dropDown);

        Navbar navbar = new Navbar("navbar", Model.of(), components);

        dropDown.setIconType(GlyphIconType.BELL);

        TagTester container = renderAndCreateTag(navbar);

        TagTester btn = container.getChild("wicket:id", "btn");
        TagTester icon = btn.getChild("wicket:id", "icon");

        assertNotNull(icon);
        assertCssClasses(icon, "glyphicon", "glyphicon-bell");
    }

}
