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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Test;

public class AbstractNavbarComponentTest extends AbstractWicketTest {
    @Test
    public void positionRight_rendersInCorrectContainer() throws Exception {
        List<INavbarComponent<? extends Component>> components = new ArrayList<>();

        components.add(new AbstractNavbarComponent<Component>(Position.RIGHT) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component create(String id) {
                return new Label(id, Model.of("Label"));
            }
        });

        Navbar navbar = new Navbar("navbar", Model.of(), components);

        TagTester container = renderAndCreateTag(navbar);

        assertEquals("", container.getChild("class", "nav navbar-nav").getValue().trim());
        assertTrue(container.getChild("class", "nav navbar-nav navbar-right").getValue().contains("Label"));
    }

}
