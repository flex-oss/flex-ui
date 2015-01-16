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
package org.cdlflex.ui.markup.html.dialog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.css.Buttons;
import org.junit.Before;
import org.junit.Test;

public class DialogTest extends AbstractWicketTest {

    TestDialog dialog;
    TagTester container;

    @Before
    public void setUp() throws Exception {
        dialog = new TestDialog("test-dialog");
        dialog.setOutputMarkupId(true).setMarkupId("test-dialog");
        container = renderAndCreateTag(dialog);
    }

    @Test
    public void dialogWithoutButtons_rendersNoFooter() throws Exception {
        assertNull(container.getChild("wicket:id", "modal-footer"));
    }

    @Test
    public void body_rendersBodyCorrectly() throws Exception {
        assertTrue(container.getChild("wicket:id", "modal-body").getValue().contains("Modal body"));
    }

    @Test
    public void body_rendersAttributesCorrectly() throws Exception {
        TagTester body = container.getChild("wicket:id", "modal-body");
        assertCssClasses(body, "modal-body");
    }

    @Test
    public void setAttributes_rendersUpdatedAttributes() throws Exception {
        dialog.setFade(false).setSize(Dialog.Size.SMALL);

        container = renderAndCreateTag(dialog);
        TagTester dialogInnerWrapperTag = container.getChild("wicket:id", "modal-dialog");

        assertCssClasses(container, "modal");
        assertCssClasses(dialogInnerWrapperTag, "modal-dialog", "modal-sm");
    }

    @Test
    public void dialog_defaultAttributesRenderCorrectly() throws Exception {
        assertEquals("dialog", container.getAttribute("role"));
        assertEquals("-1", container.getAttribute("tabindex"));
        assertEquals("true", container.getAttribute("aria-hidden"));
        assertCssClasses(container, "modal", "fade");
    }

    @Test
    public void title_renderedCorrectly() throws Exception {
        TagTester title = container.getChild("wicket:id", "modal-title");
        assertEquals("h4", title.getName());
        assertEquals("Title", title.getValue());
        assertCssClasses(title, "modal-title");
    }

    @Test
    public void emptyTitle_rendersCorrectly() throws Exception {
        dialog.getTitle().setObject(null);
        TagTester title = renderAndCreateTag(dialog).getChild("wicket:id", "modal-title");
        assertEquals("&nbsp;", title.getValue());
    }

    @Test
    public void setTitle_rendersUpdatedTitle() throws Exception {
        dialog.setTitle(Model.of("foo"));
        TagTester title = renderAndCreateTag(dialog).getChild("wicket:id", "modal-title");
        assertEquals("foo", title.getValue());
    }

    @Test
    public void createOpenLink_linkRendersCorrectly() throws Exception {
        DialogOpenLink openLink = dialog.createOpenLink(Dialog.BUTTON_MARKUP_ID);
        openLink.setBody(Model.of("Open"));

        TagTester tag = renderAndCreateTag(openLink);

        assertEquals("modal", tag.getAttribute("data-toggle"));
        assertEquals("#test-dialog", tag.getAttribute("data-target"));
        assertEquals("Open", tag.getValue());
    }

    @Test
    public void addButton_DialogCloseLink_rendersCloseLinkCorrectly() throws Exception {
        DialogCloseLink closeLink = new DialogCloseLink(Dialog.BUTTON_MARKUP_ID);
        closeLink.setOutputMarkupId(true).setMarkupId("dialog-close-link-0");
        closeLink.setSize(Buttons.Size.SMALL);
        closeLink.setType(Buttons.Type.PRIMARY);
        closeLink.setBody(Model.of("Close"));

        dialog.addButton(closeLink);

        container = renderAndCreateTag(dialog);

        assertNotNull(container.getChild("wicket:id", "modal-footer"));

        TagTester tag = container.getChild("id", closeLink.getMarkupId());
        assertCssClasses(tag, "btn", "btn-sm", "btn-primary");
        assertEquals("modal", tag.getAttribute("data-dismiss"));
        assertEquals("button", tag.getAttribute("type"));
        assertEquals("Close", tag.getValue());
    }

    class TestDialog extends Dialog<String> {
        private static final long serialVersionUID = 1L;

        public TestDialog(String id) {
            super(id, Model.of("Title"), Model.of("String"));
        }
    }
}
