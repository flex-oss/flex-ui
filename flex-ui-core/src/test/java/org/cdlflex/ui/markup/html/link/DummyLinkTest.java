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
package org.cdlflex.ui.markup.html.link;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.Application;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.AbstractWicketPageTest;
import org.junit.Before;
import org.junit.Test;

public class DummyLinkTest extends AbstractWicketPageTest {

    private ContainerPanel containerPanel;

    @Before
    public void setUp() throws Exception {
        AbstractLink linkWithStringBody = new DummyLink("link-with-string-body");
        AbstractLink linkWithMarkupBody = new DummyLink("link-with-markup-body");
        AbstractLink linkWithMixedBody = new DummyLink("link-with-mixed-body", Model.of("Ignored"));

        containerPanel = new ContainerPanel("container-panel");
        containerPanel.add(linkWithStringBody, linkWithMarkupBody, linkWithMixedBody);
    }

    @Test
    public void rendersCorrectly() throws Exception {
        String markup = ComponentRenderer.renderComponent(containerPanel).toString().trim();

        assertEquals("Body<i></i><i></i> Body", markup);
    }

    public class ContainerPanel extends Panel {
        private static final long serialVersionUID = 1L;

        public ContainerPanel(String id) {
            super(id);
            Application.get().getMarkupSettings().setStripWicketTags(true);
        }
    }
}
