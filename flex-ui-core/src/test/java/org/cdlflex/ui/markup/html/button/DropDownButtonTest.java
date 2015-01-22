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
package org.cdlflex.ui.markup.html.button;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.DummyHomePage;
import org.apache.wicket.util.tester.DummyPanelPage;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.html.link.BookmarkablePageButton;
import org.junit.Before;
import org.junit.Test;

public class DropDownButtonTest extends AbstractWicketTest {

    DropDownButton dropDownButton;
    TagTester container;
    AbstractLink button1;
    AbstractLink button2;

    @Before
    public void setUp() throws Exception {
        dropDownButton = new DropDownButton("dropdown-button", Model.of("Model")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
                button1 = new BookmarkablePageButton<>(buttonMarkupId, DummyHomePage.class);
                button2 = new BookmarkablePageButton<>(buttonMarkupId, DummyPanelPage.class);

                button1.setBody(Model.of("Button 1")).setOutputMarkupId(true).setMarkupId("button-1");
                button2.setBody(Model.of("Button 2")).setOutputMarkupId(true).setMarkupId("button-2");

                return Arrays.asList(button1, button2);
            }
        };

        dropDownButton.setOutputMarkupId(true).setMarkupId("dropdown-button");
        container = renderAndCreateTag(dropDownButton);
    }

    @Test
    public void isActive_returnsFalseOnNonActiveItems() throws Exception {
        assertFalse(dropDownButton.isActive(button1));
        assertFalse(dropDownButton.isActive(button2));
    }

    @Test
    public void container_attributesRenderCorrectly() throws Exception {
        assertCssClasses(container, "dropdown");
    }

    @Test
    public void button_attributesRenderCorrectly() throws Exception {
        TagTester button = container.getChild("wicket:id", "btn");

        assertEquals("dropdown", button.getAttribute("data-toggle"));
        assertEquals("true", button.getAttribute("aria-haspopup"));
        assertEquals("false", button.getAttribute("aria-expanded"));
        assertEquals("#", button.getAttribute("href"));
        assertCssClasses(button, "btn", "btn-default", "dropdown-toggle");
    }

    @Test
    public void button_rendersBodyCorrectly() throws Exception {
        TagTester button = container.getChild("wicket:id", "btn");
        assertTrue(button.getValue().contains("Model"));
        assertNotNull("wicket:id", "icon");
        assertNotNull("class", "caret");
    }

    @Test
    public void listContainer_attributesRenderCorrectly() throws Exception {
        TagTester ul = container.getChild("wicket:id", "dropdown-menu");
        assertEquals("menu", ul.getAttribute("role"));
        assertEquals("dropdown-button", ul.getAttribute("aria-labelledby"));
    }

    @Test
    public void listContainer_rendersButtonListItemsCorrectly() throws Exception {
        TagTester ul = container.getChild("wicket:id", "dropdown-menu");

        TagTester btn1 = ul.getChild("id", "button-1");
        TagTester btn2 = ul.getChild("id", "button-2");

        assertNotNull(btn1);
        assertNotNull(btn2);

        assertEquals("Button 1", btn1.getValue());
        assertEquals("Button 2", btn2.getValue());
        assertCssClasses(btn1, "btn", "btn-default");
        assertCssClasses(btn2, "btn", "btn-default");
    }

    @Test
    public void setButtonProperties_rendersButtonCorrectly() throws Exception {
        dropDownButton.setSize(Buttons.Size.SMALL);
        dropDownButton.setType(Buttons.Type.PRIMARY);

        container = renderAndCreateTag(dropDownButton);
        TagTester button = container.getChild("wicket:id", "btn");

        assertCssClasses(button, "btn", "btn-sm", "btn-primary", "dropdown-toggle");
    }

    @Test
    public void setDropUp_rendersAddtionalCssClass() throws Exception {
        dropDownButton.setDropUp(true);
        container = renderAndCreateTag(dropDownButton);
        assertCssClasses(container, "dropdown", "dropup");
    }

    @Test
    public void setIconType_rendersIconClassesCorrectly() throws Exception {
        dropDownButton.setIconType(GlyphIconType.COG);
        container = renderAndCreateTag(dropDownButton);

        TagTester icon = container.getChild("wicket:id", "btn").getChild("wicket:id", "icon");
        assertCssClasses(icon, "glyphicon", "glyphicon-cog");
    }

    @Test
    public void addToButton_addsBehaviorToButtonCorrectly() throws Exception {
        dropDownButton.addToButton(new AttributeAppender("testAttribute", "testValue"));

        container = renderAndCreateTag(dropDownButton);
        TagTester button = container.getChild("wicket:id", "btn");

        assertEquals("testValue", button.getAttribute("testAttribute"));
    }
}
