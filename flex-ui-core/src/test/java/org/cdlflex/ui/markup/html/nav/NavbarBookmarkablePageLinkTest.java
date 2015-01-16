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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.DummyHomePage;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Test;

public class NavbarBookmarkablePageLinkTest extends AbstractWicketTest {
    @Test
    public void asNavbarComponent_rendersCorrectly() throws Exception {
        List<INavbarComponent<? extends Component>> components = new ArrayList<>();

        components.add(new NavbarBookmarkablePageLink(DummyHomePage.class, Model.of("Home")));

        Navbar navbar = new Navbar("navbar", Model.of(), components);

        TagTester container = renderAndCreateTag(navbar);
        TagTester a = container.getChild("wicket:id", "nav-component");

        assertNotNull(a);
        assertEquals("a", a.getName());
        assertEquals("./wicket/bookmarkable/org.apache.wicket.util.tester.DummyHomePage", a.getAttribute("href"));
    }
}
