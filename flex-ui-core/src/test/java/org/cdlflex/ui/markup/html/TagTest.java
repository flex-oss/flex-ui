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
package org.cdlflex.ui.markup.html;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
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

    @Test
    public void addAttributeMap_generatesCorrectHtml() throws Exception {
        Tag tag = new Tag("span");

        tag.attr("bar", "baz");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("foo", "bar");
        map.put("answer", "42");
        map.put("string", new StringModel("stringModel"));
        map.put("list", Arrays.asList("foo", "bar"));

        tag.attrs(map);

        assertEquals("<span string=\"stringModel\" answer=\"42\" list=\"foo bar\" foo=\"bar\" bar=\"baz\"></span>",
                tag.toString());
    }

    public class StringModel extends Model<String> {
        private static final long serialVersionUID = 1L;

        public StringModel(String object) {
            super(object);
        }

        @Override
        public String toString() {
            return getObject();
        }
    }

}
