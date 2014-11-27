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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.cdlflex.ui.AbstractWicketPageTest;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.junit.Before;
import org.junit.Test;

public class BookmarkablePageButtonTest extends AbstractWicketPageTest {

    private TestPage testPage;

    private BookmarkablePageButton<?> btnDefault;
    private BookmarkablePageButton<?> btnDefaultIcon;
    private BookmarkablePageButton<?> btnDefaultReplaceModel;
    private BookmarkablePageButton<?> btnDefaultReplaceModelIcon;
    private BookmarkablePageButton<?> btnNone;

    @Before
    public void setUp() throws Exception {
        btnDefault = new BookmarkablePageButton<Void>("button-default", TestPage.class);
        btnDefaultIcon = new BookmarkablePageButton<Void>("button-default-icon", TestPage.class);
        btnDefaultReplaceModel = new BookmarkablePageButton<Void>("button-default-replacemodel", TestPage.class);
        btnDefaultReplaceModelIcon =
            new BookmarkablePageButton<Void>("button-default-replacemodel-icon", TestPage.class);
        btnNone = new BookmarkablePageButton<Void>("button-none", TestPage.class);

        btnDefault.setType(Buttons.Type.DEFAULT);
        btnDefaultIcon.setType(Buttons.Type.DEFAULT).setIconType(GlyphIconType.ASTERISK);
        btnDefaultReplaceModel.setType(Buttons.Type.DEFAULT).setBody(Model.of("Replace"));
        btnDefaultReplaceModelIcon.setType(Buttons.Type.DEFAULT).setIconType(GlyphIconType.ASTERISK)
                .setBody(Model.of("Replace"));
        btnNone.setType(Buttons.Type.NONE);

        testPage = new TestPage();
        testPage.add(btnDefault, btnDefaultIcon, btnDefaultReplaceModel, btnDefaultReplaceModelIcon, btnNone);
    }

    @Test
    public void default_rendersCorrectly() throws Exception {
        wicketTester.startPage(testPage);

        TagTester tag = createTag(btnDefault);
        assertTrue(tag.getAttributeContains("class", "btn"));
        assertFalse(tag.hasChildTag("i"));
        assertEquals("Body", tag.getValue());
    }

    @Test
    public void defaultWithIcon_rendersCorrectly() throws Exception {
        wicketTester.startPage(testPage);

        TagTester tag = createTag(btnDefaultIcon);
        assertTrue(tag.getAttributeContains("class", "btn"));
        assertTrue(tag.hasChildTag("i"));
        assertNotNull(tag.getChild("class", "glyphicon glyphicon-asterisk"));
        assertTrue(tag.getValue().endsWith("Body"));
    }

    @Test
    public void defaultWithReplaceModel_rendersCorrectly() throws Exception {
        wicketTester.startPage(testPage);

        TagTester tag = createTag(btnDefaultReplaceModel);
        assertTrue(tag.getAttributeContains("class", "btn"));
        assertFalse(tag.hasChildTag("i"));
        assertEquals("Replace", tag.getValue());
    }

    @Test
    public void defaultWithIconAndReplaceModel_rendersCorrectly() throws Exception {
        wicketTester.startPage(testPage);

        TagTester tag = createTag(btnDefaultReplaceModelIcon);
        assertTrue(tag.getAttributeContains("class", "btn"));
        assertTrue(tag.hasChildTag("i"));
        assertNotNull(tag.getChild("class", "glyphicon glyphicon-asterisk"));
        assertTrue(tag.getValue().endsWith("Replace"));
        assertFalse(tag.getValue().contains("Body"));
    }

    public class TestPage extends WebPage {
        private static final long serialVersionUID = 1L;

        @Override
        protected void onInitialize() {
            super.onInitialize();

            visitChildren(new IVisitor<Component, Object>() {
                @Override
                public void component(Component object, IVisit<Object> visit) {
                    object.setOutputMarkupId(true);
                }
            });
        }
    }
}
