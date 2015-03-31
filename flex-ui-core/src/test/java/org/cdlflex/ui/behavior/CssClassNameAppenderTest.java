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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.css.ICssClassNameProvider;
import org.junit.Before;
import org.junit.Test;

public class CssClassNameAppenderTest extends AbstractWicketTest {

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

        List<String> classes = Arrays.asList(tag.getAttribute("class").split(" "));
        assertThat(classes.size(), is(2));
        assertThat(classes, hasItem("a"));
        assertThat(classes, hasItem("b"));
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

        List<String> classes = Arrays.asList(tag.getAttribute("class").split(" "));
        assertThat(classes.size(), is(2));
        assertThat(classes, hasItem("label"));
        assertThat(classes, hasItem("a"));
    }

    @Test
    public void addClassesToTagContainingSingleClass_appendsClassCorrectly() throws Exception {
        page.labelWithClass.add(new CssClassNameAppender("a"));
        page.labelWithClass.add(new CssClassNameAppender("b"));
        page.labelWithClass.add(new CssClassNameAppender("a"));

        TagTester tag = createTagById(render(page), "label-with-class");

        List<String> classes = Arrays.asList(tag.getAttribute("class").split(" "));
        assertThat(classes.size(), is(3));
        assertThat(classes, hasItem("a"));
        assertThat(classes, hasItem("b"));
        assertThat(classes, hasItem("label"));
    }

    @Test
    public void addSameClassToTagContainingSingleClass_doesNotAppendClass() throws Exception {
        page.labelWithClass.add(new CssClassNameAppender("label"));

        TagTester tag = createTagById(render(page), "label-with-class");

        assertEquals("label", tag.getAttribute("class"));
    }

    @Test
    public void append_withVarargs_appendsAllClasses() throws Exception {
        ComponentTag a = new ComponentTag("a", XmlTag.TagType.OPEN);
        CssClassNameAppender.append(a, "foo", "bar", "foo");

        List<String> classes = Arrays.asList(a.getAttribute("class").split(" "));
        assertEquals(2, classes.size());
        assertTrue("class 'foo' not set", classes.contains("foo"));
        assertTrue("class 'bar' not set", classes.contains("bar"));
    }

    @Test
    public void append_withICssClassNameProvider_appendsClassesCorrectly() throws Exception {
        ComponentTag a = new ComponentTag("a", XmlTag.TagType.OPEN);

        CssClassNameAppender.append(a, new CssClassNameProvider());

        List<String> classes = Arrays.asList(a.getAttribute("class").split(" "));
        assertEquals(2, classes.size());
        assertTrue("class 'foo' not set", classes.contains("foo"));
        assertTrue("class 'bar' not set", classes.contains("bar"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void append_withICssClassNameProviderModel_appendsClassesCorrectly() throws Exception {
        ComponentTag a = new ComponentTag("a", XmlTag.TagType.OPEN);

        Model<CssClassNameProvider> model = new Model<>(new CssClassNameProvider());
        CssClassNameAppender.append(a, model);

        List<String> classes = Arrays.asList(a.getAttribute("class").split(" "));
        assertEquals(2, classes.size());
        assertTrue("class 'foo' not set", classes.contains("foo"));
        assertTrue("class 'bar' not set", classes.contains("bar"));
    }

    class LabelPage extends WebPage {
        private static final long serialVersionUID = 1L;

        Label label = new Label("label");
        Label labelWithClass = new Label("label-with-class");

        public LabelPage() {
            add(label, labelWithClass);
        }
    }

    static class CssClassNameProvider implements ICssClassNameProvider, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public String getCssClassName() {
            return "foo bar foo";
        }
    }
}
