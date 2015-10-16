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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Before;
import org.junit.Test;

/**
 * CollapseBehaviorTest.
 */
public class CollapseBehaviorTest extends AbstractWicketTest {

    private TestPanel container;
    private WebMarkupContainer target;
    private WebMarkupContainer button;

    @Before
    public void setUp() throws Exception {
        container = new TestPanel("container");
        target = new WebMarkupContainer("target");
        button = new WebMarkupContainer("button");

        container.add(target);
        container.add(button);
    }

    @Test
    public void defaultBehavior_rendersComponentAttributesCorrectly() throws Exception {
        button.add(new CollapseBehavior(target));

        getWicketTester().startComponentInPage(container);

        TagTester buttonTag = getWicketTester().getTagByWicketId("button");
        TagTester targetTag = getWicketTester().getTagByWicketId("target");
        System.out.println(targetTag.getMarkup());

        assertEquals("collapse", buttonTag.getAttribute("data-toggle"));
        assertEquals(targetTag.getAttribute("id"), buttonTag.getAttribute("aria-controls"));
        assertEquals("#" + targetTag.getAttribute("id"), buttonTag.getAttribute("data-target"));
        assertEquals("false", buttonTag.getAttribute("aria-expanded"));
        assertTrue("Class 'collapsed' not found in classes [" + buttonTag.getAttribute("class") + "]",
                buttonTag.getAttributeContains("class", "collapsed"));

        assertTrue("Class 'collapse' not found in classes [" + targetTag.getAttribute("class") + "]",
                targetTag.getAttributeContains("class", "collapse"));
        assertFalse("Class 'in' not expected", targetTag.getAttributeContains("class", "in"));
    }

    @Test
    public void expandedTrue_rendersComponentAttributesCorrectly() throws Exception {
        button.add(new CollapseBehavior(target).setExpanded(true));

        getWicketTester().startComponentInPage(container);

        TagTester buttonTag = getWicketTester().getTagByWicketId("button");
        TagTester targetTag = getWicketTester().getTagByWicketId("target");

        assertEquals("true", buttonTag.getAttribute("aria-expanded"));

        assertTrue("Class 'collapse' not found in classes [" + targetTag.getAttribute("class") + "]",
                targetTag.getAttributeContains("class", "collapse"));
        assertTrue("Class 'in' not found in classes [" + targetTag.getAttribute("class") + "]",
                targetTag.getAttributeContains("class", "in"));
    }

    @Test
    public void remove_unbindsBehaviorsCorrectly() throws Exception {
        CollapseBehavior behavior = new CollapseBehavior(target);
        button.add(behavior);

        getWicketTester().startComponentInPage(container);
        button.remove(behavior);
        getWicketTester().startComponentInPage(container);

        TagTester buttonTag = getWicketTester().getTagByWicketId("button");
        TagTester targetTag = getWicketTester().getTagByWicketId("target");

        assertFalse(buttonTag.hasAttribute("data-toggle"));
        assertFalse(buttonTag.hasAttribute("data-target"));
        assertFalse(buttonTag.hasAttribute("aria-controlls"));
        assertFalse(buttonTag.hasAttribute("aria-expanded"));
        assertFalse(targetTag.hasAttribute("class"));
    }

    private static class TestPanel extends Panel {

        private static final long serialVersionUID = 1L;

        public TestPanel(String id) {
            super(id);
        }
    }
}
