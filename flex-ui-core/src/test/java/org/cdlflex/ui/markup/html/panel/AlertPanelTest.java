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
package org.cdlflex.ui.markup.html.panel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.css.Alerts;
import org.junit.Test;

public class AlertPanelTest extends AbstractWicketTest {
    @Test
    public void dismissiblePanel_rendersCorrectly() throws Exception {
        AlertPanel panel = new AlertPanel("alert-panel", Alerts.Level.SUCCESS);
        panel.setDefaultModel(Model.of("Message body"));

        String markup = ComponentRenderer.renderComponent(panel).toString();

        // div
        TagTester div = TagTester.createTagByAttribute(markup, "wicket:id", "alert");
        assertCssClasses(div, "alert", "alert-success", "alert-dismissible");

        // dismiss button
        TagTester button = div.getChild("wicket:id", "dismiss-button");
        assertCssClasses(button, "close");
        assertEquals("alert", button.getAttribute("data-dismiss"));

        // message
        TagTester message = div.getChild("wicket:id", "message");
        assertEquals("Message body", message.getValue());
    }

    @Test
    public void nonDismissiblePanel_rendersCorrectly() throws Exception {
        AlertPanel panel = new AlertPanel("alert-panel", Model.of("Message body"), false);
        panel.setLevel(Alerts.Level.SUCCESS);

        String markup = ComponentRenderer.renderComponent(panel).toString();

        // div
        TagTester div = TagTester.createTagByAttribute(markup, "wicket:id", "alert");
        assertCssClasses(div, "alert", "alert-success");

        // dismiss button
        TagTester button = div.getChild("wicket:id", "dismiss-button");
        assertNull(button);

        // message
        TagTester message = div.getChild("wicket:id", "message");
        assertEquals("Message body", message.getValue());
    }

    @Test
    public void propertiesAreSetCorrectly() throws Exception {
        AlertPanel panel = new AlertPanel("alert-panel");

        assertEquals(Alerts.Level.INFO, panel.getLevel());
        assertFalse(panel.isFade());
        assertTrue(panel.isDismissible());

        panel.setFade(true).setDismissible(false).setLevel(Alerts.Level.PRIMARY);

        assertEquals(Alerts.Level.PRIMARY, panel.getLevel());
        assertTrue(panel.isFade());
        assertFalse(panel.isDismissible());
    }
}
