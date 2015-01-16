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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.html.alert.Alert;
import org.junit.Before;
import org.junit.Test;

/**
 * AlertTest.
 */
public class AlertTest extends AbstractWicketTest {

    private WebPage page;
    private String componentId = "alert";

    @Before
    public void setUp() throws Exception {
        page = new AlertPage();
    }

    @Test
    public void defaultAlert_rendersAttributesCorrectly() throws Exception {
        Alert alert = new Alert(componentId);
        page.add(alert.setOutputMarkupId(true));

        TagTester tag = createTag(render(page), alert);

        assertTrue(tag.getAttributeContains("class", "alert"));
        assertTrue(tag.getAttributeContains("class", "alert-info"));
        assertTrue(tag.getAttributeIs("role", "alert"));
    }

    @Test
    public void defaultAlert_containsDismissButton() throws Exception {
        Alert alert = new Alert(componentId);
        page.add(alert.setOutputMarkupId(true));

        TagTester tag = createTag(render(page), alert);
        TagTester button = tag.getChild("data-dismiss", "alert");

        assertNotNull(button);
        assertEquals("button", button.getName());
        assertTrue(button.getAttributeIs("class", "close"));
        assertTrue(button.getAttributeIs("type", "button"));
    }

    @Test
    public void alert_notDismissible_doesNotContainDismissButton() throws Exception {
        Alert alert = new Alert(componentId).setDismissible(false);
        page.add(alert.setOutputMarkupId(true));

        TagTester tag = createTag(render(page), alert);
        TagTester button = tag.getChild("data-dismiss", "alert");

        assertNull(button);
    }

    @Test
    public void alert_withFade_containsCorrectCssClasses() throws Exception {
        Alert alert = new Alert(componentId).setFade(true);
        page.add(alert.setOutputMarkupId(true));

        TagTester tag = createTag(render(page), alert);

        assertTrue(tag.getAttributeContains("class", "fade"));
        assertTrue(tag.getAttributeContains("class", "in"));
    }

    class AlertPage extends WebPage {
        private static final long serialVersionUID = 1L;

        AlertPage() {
            super();
        }
    }
}
