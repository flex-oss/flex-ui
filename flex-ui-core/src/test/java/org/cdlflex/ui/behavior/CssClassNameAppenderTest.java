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
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketPageTest;
import org.junit.Before;
import org.junit.Test;

public class CssClassNameAppenderTest extends AbstractWicketPageTest {

    private LabelPage page;

    @Before
    public void setUp() {
        page = new LabelPage();
    }

    @Test
    public void addClassToTagContainingNoClasses_appendsClassCorrectly() throws Exception {
        page.label.add(new CssClassNameAppender("a"));

        TagTester tag = createTagById(render(page), "label");

        assertTrue(tag.getAttributeIs("class", "a"));
    }

    @Test
    public void addClassesToTagContainingNoClasses_appendsClassesCorrectly() throws Exception {
        page.label.add(new CssClassNameAppender("a"));
        page.label.add(new CssClassNameAppender("b"));
        page.label.add(new CssClassNameAppender("a"));

        TagTester tag = createTagById(render(page), "label");

        assertEquals("b a", tag.getAttribute("class"));
    }

    @Test
    public void varargsConstructor_appendsClassesCorrectly() throws Exception {
        page.label.add(new CssClassNameAppender("a", "b", "c"));

        TagTester tag = createTagById(render(page), "label");

        assertTrue(tag.getAttributeContains("class", "a"));
        assertTrue(tag.getAttributeContains("class", "b"));
        assertTrue(tag.getAttributeContains("class", "c"));
    }

    @Test
    public void addSameClassTwiceToTagContainingNoClasses_appendsClassCorrectly() throws Exception {
        page.label.add(new CssClassNameAppender("a"));
        page.label.add(new CssClassNameAppender("a"));

        TagTester tag = createTagById(render(page), "label");

        assertEquals("a", tag.getAttribute("class"));
    }

    @Test
    public void addClassToTagContainingSingleClass_appendsClassCorrectly() throws Exception {
        page.labelWithClass.add(new CssClassNameAppender("a"));

        TagTester tag = createTagById(render(page), "label-with-class");

        assertEquals("label a", tag.getAttribute("class"));
    }

    @Test
    public void addClassesToTagContainingSingleClass_appendsClassCorrectly() throws Exception {
        page.labelWithClass.add(new CssClassNameAppender("a"));
        page.labelWithClass.add(new CssClassNameAppender("b"));
        page.labelWithClass.add(new CssClassNameAppender("a"));

        TagTester tag = createTagById(render(page), "label-with-class");

        assertEquals("b label a", tag.getAttribute("class"));
    }

    @Test
    public void addSameClassToTagContainingSingleClass_doesNotAppendClass() throws Exception {
        page.labelWithClass.add(new CssClassNameAppender("label"));

        TagTester tag = createTagById(render(page), "label-with-class");

        assertEquals("label", tag.getAttribute("class"));
    }

    class LabelPage extends WebPage {
        Label label = new Label("label");
        Label labelWithClass = new Label("label-with-class");

        public LabelPage() {
            add(label, labelWithClass);
        }
    }

}
