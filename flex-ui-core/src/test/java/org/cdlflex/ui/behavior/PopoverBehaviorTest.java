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
package org.cdlflex.ui.behavior;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Before;
import org.junit.Test;

public class PopoverBehaviorTest extends AbstractWicketTest {

    private Component component;

    @Before
    public void setUp() throws Exception {
        component = new WebMarkupContainer("test-component");
    }

    @Test
    public void constructor_titleAndBodyString_rendersAttributesCorrectly() throws Exception {
        component.add(new PopoverBehavior("message-title", "message-body"));

        TagTester tagTester = renderAndCreateTag(component);

        assertEquals("popover", tagTester.getAttribute("data-toggle"));
        assertEquals("message-title", tagTester.getAttribute("title"));
        assertEquals("message-body", tagTester.getAttribute("data-content"));
    }

    @Test
    public void constructor_bodyStringAndPlacement_rendersAttributesCorrectly() throws Exception {
        component.add(new PopoverBehavior("message-body", PopoverBehavior.Placement.TOP));

        TagTester tagTester = renderAndCreateTag(component);

        assertEquals("popover", tagTester.getAttribute("data-toggle"));
        assertNull(tagTester.getAttribute("title"));
        assertEquals("message-body", tagTester.getAttribute("data-content"));
        assertEquals("top", tagTester.getAttribute("data-placement"));
    }

    @Test
    public void constructor_titleAndBodyStringAndPlacement_rendersAttributesCorrectly() throws Exception {
        component.add(new PopoverBehavior("message-title", "message-body", PopoverBehavior.Placement.BOTTOM));

        TagTester tagTester = renderAndCreateTag(component);

        assertEquals("popover", tagTester.getAttribute("data-toggle"));
        assertEquals("message-title", tagTester.getAttribute("title"));
        assertEquals("message-body", tagTester.getAttribute("data-content"));
        assertEquals("bottom", tagTester.getAttribute("data-placement"));
    }

    @Test
    public void setTrigger_rendersAttributeCorrectly() throws Exception {
        component.add(new PopoverBehavior("message-body").setTrigger(PopoverBehavior.Trigger.HOVER));

        TagTester tagTester = renderAndCreateTag(component);
        assertEquals("hover", tagTester.getAttribute("data-trigger"));
    }

    @Test
    public void rendersActivationJavaScriptOnPageCorrectly() throws Exception {
        WebPage page = new TestPage();
        component.add(new PopoverBehavior("message-title", "message-body"));
        page.add(component);

        assertThat(render(page), containsString("$('#test-component').popover();"));
    }

    class TestPage extends WebPage {
        private static final long serialVersionUID = 1L;
    }
}
