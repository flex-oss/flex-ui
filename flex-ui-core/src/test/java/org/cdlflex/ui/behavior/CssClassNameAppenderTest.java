package org.cdlflex.ui.behavior;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class CssClassNameAppenderTest {

    private WicketTester tester;
    private LabelPage page;

    @Before
    public void setUp() {
        tester = new WicketTester(new MockApplication());

        page = new LabelPage();
    }

    @Test
    public void addClassToTagContainingNoClasses_appendsClassCorrectly() throws Exception {
        page.label.add(new CssClassNameAppender("a"));
        tester.startPage(page);
        TagTester tag = TagTester.createTagByAttribute(tester.getLastResponse().getDocument(), "id", "label");

        assertTrue(tag.getAttributeIs("class", "a"));
    }

    @Test
    public void addClassesToTagContainingNoClasses_appendsClassesCorrectly() throws Exception {
        page.label.add(new CssClassNameAppender("a"));
        page.label.add(new CssClassNameAppender("b"));
        page.label.add(new CssClassNameAppender("a"));

        tester.startPage(page);
        TagTester tag = TagTester.createTagByAttribute(tester.getLastResponse().getDocument(), "id", "label");

        assertEquals("b a", tag.getAttribute("class"));
    }

    @Test
    public void addSameClassTwiceToTagContainingNoClasses_appendsClassCorrectly() throws Exception {
        page.label.add(new CssClassNameAppender("a"));
        page.label.add(new CssClassNameAppender("a"));

        tester.startPage(page);
        TagTester tag = TagTester.createTagByAttribute(tester.getLastResponse().getDocument(), "id", "label");

        assertEquals("a", tag.getAttribute("class"));
    }

    @Test
    public void addClassToTagContainingSingleClass_appendsClassCorrectly() throws Exception {
        page.labelWithClass.add(new CssClassNameAppender("a"));

        tester.startPage(page);
        TagTester tag =
            TagTester.createTagByAttribute(tester.getLastResponse().getDocument(), "id", "label-with-class");

        assertEquals("label a", tag.getAttribute("class"));
    }

    @Test
    public void addClassesToTagContainingSingleClass_appendsClassCorrectly() throws Exception {
        page.labelWithClass.add(new CssClassNameAppender("a"));
        page.labelWithClass.add(new CssClassNameAppender("b"));
        page.labelWithClass.add(new CssClassNameAppender("a"));

        tester.startPage(page);
        TagTester tag =
            TagTester.createTagByAttribute(tester.getLastResponse().getDocument(), "id", "label-with-class");

        assertEquals("b label a", tag.getAttribute("class"));
    }

    @Test
    public void addSameClassToTagContainingSingleClass_doesNotAppendClass() throws Exception {
        page.labelWithClass.add(new CssClassNameAppender("label"));

        tester.startPage(page);
        TagTester tag =
            TagTester.createTagByAttribute(tester.getLastResponse().getDocument(), "id", "label-with-class");

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
