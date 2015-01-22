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
package org.cdlflex.ui.markup.html.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Before;
import org.junit.Test;

public class DateTimePickerTest extends AbstractWicketTest {

    TestPage page;
    DateTimePicker component;

    @Before
    public void setUp() throws Exception {
        page = new TestPage();
        component = new DateTimePicker("date", "y-MM-dd HH:mm");
        component.setOutputMarkupId(true).setMarkupId("date_time_picker");
        page.getForm().add(component);
    }

    @Test
    public void setDateTimePickerProperties_changesWidgetFunctionAttributes() throws Exception {
        component.setPickDate(false).setPickTime(false).setSideBySide(false).setLanguage("de");

        String markup = render(page);

        int start = markup.indexOf("/*<![CDATA[*/");
        int end = markup.indexOf("/*]]>*/");
        String js = markup.substring(start + 13, end).trim();

        assertTrue("pickDate is not set", js.contains("\"pickDate\":false"));
        assertTrue("pickTime is not set", js.contains("\"pickTime\":false"));
        assertTrue("pickTime is not set", js.contains("\"sideBySide\":false"));
        assertTrue("pickTime is not set", js.contains("\"language\":\"de\""));
    }

    @Test
    public void defaultDateTimePicker_javaScript_callsWidgetFunction() throws Exception {
        String markup = render(page);

        int start = markup.indexOf("/*<![CDATA[*/");
        int end = markup.indexOf("/*]]>*/");
        String js = markup.substring(start + 13, end).trim();

        assertTrue("datetimepicker widget is not called", js.contains("$('#date_time_picker').datetimepicker({"));
        assertTrue("there is no momentjs date format set", js.contains("\"format\":\"YYYY-MM-DD HH:mm\""));
        assertTrue("pickDate is not set", js.contains("\"pickDate\":true"));
        assertTrue("pickTime is not set", js.contains("\"pickTime\":true"));
    }

    @Test
    public void defaultDateTimePicker_rendersInputFieldCorrectly() throws Exception {
        startPage(page);
        TagTester input = createTag(component);

        assertEquals("date", input.getAttribute("name"));
        assertEquals("", input.getAttribute("value"));
    }

    private static class TestPage extends WebPage {
        private static final long serialVersionUID = 1L;

        private Form<TestFormModel> form;

        public TestPage() {
            form = new Form<>("form", new CompoundPropertyModel<>(new TestFormModel()));
            add(form);
        }

        public Form<TestFormModel> getForm() {
            return form;
        }
    }

    private static class TestFormModel implements IClusterable {
        private static final long serialVersionUID = 1L;

        private Date date;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
