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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.wicket.Component;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.html.alert.Alert;
import org.junit.Test;

public class FeedbackPanelTest extends AbstractWicketTest {
    @Test
    public void singleInfoMessage_rendersAsAlertPanelCorrectly() throws Exception {
        FeedbackPanel panel = new FeedbackPanel("feedback");

        panel.info("Message body");

        String markup = ComponentRenderer.renderComponent(panel).toString();

        // div
        TagTester div = TagTester.createTagByAttribute(markup, "wicket:id", "message");
        assertCssClasses(div, "alert", "alert-info", "alert-dismissible", "feedbackPanelINFO");

        // dismiss button
        TagTester button = div.getChild("wicket:id", "dismiss-button");
        assertCssClasses(button, "close");
        assertEquals("alert", button.getAttribute("data-dismiss"));

        // message
        TagTester message = div.getChild("wicket:id", "label");
        assertEquals("Message body", message.getValue());
    }

    @Test
    public void nonDismissiblePanel_rendersAlertCorrectly() throws Exception {
        FeedbackPanel panel = new FeedbackPanel("feedback");
        panel.setDismissible(false);

        panel.info("Message body");

        String markup = ComponentRenderer.renderComponent(panel).toString();

        // div
        TagTester div = TagTester.createTagByAttribute(markup, "wicket:id", "message");
        assertCssClasses(div, "alert", "alert-info", "feedbackPanelINFO");

        // dismiss button
        TagTester button = div.getChild("wicket:id", "dismiss-button");
        assertNull(button);

        // message
        TagTester message = div.getChild("wicket:id", "label");
        assertEquals("Message body", message.getValue());
    }

    @Test
    public void multipleMessages_rendersAllMessages() throws Exception {
        FeedbackPanel panel = new FeedbackPanel("feedback", false) {
            private static final long serialVersionUID = 1L;

            @SuppressWarnings({ "unchecked", "rawtypes" })
            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                // necessary to test the presence of all messages
                ((ListView) get("feedbackul").get("messages")).visitChildren(new IVisitor<Component, Void>() {
                    int cnt = 0;

                    @Override
                    public void component(Component object, IVisit<Void> visit) {
                        if (object instanceof Alert) {
                            object.setOutputMarkupId(true);
                            object.setMarkupId("message-" + (cnt++));
                        }
                    }
                });
            }
        };

        panel.success("success");
        panel.info("info");
        panel.debug("debug");
        panel.warn("warn");
        panel.error("error");
        panel.fatal("fatal");

        TagTester tagTester = renderAndCreateTag(panel);
        assertNotNull(tagTester.getChild("id", "message-0"));
        assertNotNull(tagTester.getChild("id", "message-1"));
        assertNotNull(tagTester.getChild("id", "message-2"));
        assertNotNull(tagTester.getChild("id", "message-3"));
        assertNotNull(tagTester.getChild("id", "message-4"));
        assertNotNull(tagTester.getChild("id", "message-5"));
        assertNull(tagTester.getChild("id", "message-6"));
    }
}
