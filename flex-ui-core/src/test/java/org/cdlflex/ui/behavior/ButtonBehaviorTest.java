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

import static org.junit.Assert.assertTrue;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketPageTest;
import org.cdlflex.ui.markup.css.Buttons;
import org.junit.Before;
import org.junit.Test;

public class ButtonBehaviorTest extends AbstractWicketPageTest {

    private Component button;
    private WebPage page;

    @Before
    public void setUp() throws Exception {
        page = new TestPage();
    }

    @Test
    public void defaultButtonBehavior_addsBtnClass() throws Exception {
        button.add(new ButtonBehavior());

        TagTester tag = createTag(render(page), button);

        assertTrue(tag.getAttributeContains("class", "btn"));
        assertTrue(tag.getAttributeContains("class", "btn-default"));
    }

    @Test
    public void buttonBehaviorWithSize_addsBtnAndSizeClass() throws Exception {
        button.add(new ButtonBehavior(Buttons.Size.LARGE));

        TagTester tag = createTag(render(page), button);
        assertTrue(tag.getAttributeContains("class", "btn"));
        assertTrue(tag.getAttributeContains("class", "btn-lg"));
    }

    @Test
    public void buttonBehaviorWithType_addsBtnAndTypeClass() throws Exception {
        button.add(new ButtonBehavior(Buttons.Type.PRIMARY));

        TagTester tag = createTag(render(page), button);
        assertTrue(tag.getAttributeContains("class", "btn"));
        assertTrue(tag.getAttributeContains("class", "btn-primary"));
    }

    @Test
    public void buttonBehaviorWithTypeAndSize_addsBtnAndSizeAndTypeClass() throws Exception {
        button.add(new ButtonBehavior(Buttons.Type.PRIMARY, Buttons.Size.LARGE));

        TagTester tag = createTag(render(page), button);
        assertTrue(tag.getAttributeContains("class", "btn"));
        assertTrue(tag.getAttributeContains("class", "btn-lg"));
        assertTrue(tag.getAttributeContains("class", "btn-primary"));
    }

    @Test
    public void editSizeAfterAdd_addsCorrectSizeClass() throws Exception {
        ButtonBehavior behavior = new ButtonBehavior(Buttons.Size.MEDIUM);
        button.add(behavior);

        behavior.setSize(Buttons.Size.LARGE);

        TagTester tag = createTag(render(page), button);
        assertTrue(tag.getAttributeContains("class", "btn-lg"));
    }

    public class TestPage extends WebPage {
        private static final long serialVersionUID = 1L;

        public TestPage() {
            button = new WebMarkupContainer("button");
            add(button);
        }
    }
}
