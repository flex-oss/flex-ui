package org.cdlflex.ui.markup.html;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;

public class TagTest {
    @Test
    public void toString_returnsCorrectHtml() throws Exception {
        Tag a = new Tag("a", new LinkedHashMap<String, Object>(), (Tag) null);

        a.attr("href", "http://rauschig.org");
        a.attr("title", "foo");

        assertEquals("<a href=\"http://rauschig.org\" title=\"foo\"></a>", a.toString());
    }

    @Test
    public void toString_withArrayAttribute_returnsCorrectHtml() throws Exception {
        Tag a = new Tag("a");
        a.attr("class", new String[] { "foo", "bar", "ed" });
        assertEquals("<a class=\"foo bar ed\"></a>", a.toString());
    }

    @Test
    public void toString_noAttributes_returnsCorrectHtml() throws Exception {
        Tag hr = new Tag("hr");

        assertEquals("<hr></hr>", hr.toString());
    }

    @Test
    public void toString_noAttributes_shortTag_returnsCorrectHtml() throws Exception {
        Tag hr = new Tag("hr");
        hr.setShortTag(true);

        assertEquals("<hr/>", hr.toString());
    }

    @Test
    public void toString_withBodyTag_returnsCorrectHtml() {
        Tag a = new Tag("a", new Tag("span", "some text node"));

        a.attr("class", "foo");

        assertEquals("<a class=\"foo\"><span>some text node</span></a>", a.toString());
    }

    @Test
    public void toString_withMultipleBodyTags_returnsCorrectHtml() throws Exception {
        Tag a = new Tag("a");

        a.add(new Tag("span", "some text node"));
        a.add(new Tag("i").attr("class", "icon").setShortTag(true));

        assertEquals("<a><span>some text node</span><i class=\"icon\"/></a>", a.toString());
    }

}
