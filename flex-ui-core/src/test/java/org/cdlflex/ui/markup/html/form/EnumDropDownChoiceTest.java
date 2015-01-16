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
import static org.junit.Assert.assertNotNull;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Before;
import org.junit.Test;

public class EnumDropDownChoiceTest extends AbstractWicketTest {

    TestPage page;

    @Before
    public void setUp() throws Exception {
        page = new TestPage();
    }

    @Test
    public void enumDropDownChoice_withDefaultRenderer_rendersOptionsCorrectly() throws Exception {
        EnumDropDownChoice<TestEnum> component = new EnumDropDownChoice<>("testEnum", TestEnum.class);
        page.getForm().add(component);

        startPage(page);
        TagTester select = createTag(component);

        TagTester option1 = select.getChild("value", "FOO");
        TagTester option2 = select.getChild("value", "BAR_ED");
        TagTester option3 = select.getChild("value", "BAZ_");

        assertNotNull(option1);
        assertNotNull(option2);
        assertNotNull(option3);

        assertEquals("Foo", option1.getValue());
        assertEquals("Bar ed", option2.getValue());
        assertEquals("Baz", option3.getValue());
    }

    @Test
    public void enumDropDownChoice_withCustomRenderer_rendersOptionsCorrectly() throws Exception {
        EnumDropDownChoice<TestEnum> component =
            new EnumDropDownChoice<>("testEnum", TestEnum.class, new IChoiceRenderer<TestEnum>() {
                @Override
                public Object getDisplayValue(TestEnum object) {
                    return "TestEnum-" + object;
                }

                @Override
                public String getIdValue(TestEnum object, int index) {
                    return index + "-" + object.name();
                }

                private static final long serialVersionUID = 1L;

            });
        page.getForm().add(component);

        startPage(page);
        TagTester select = createTag(component);

        TagTester option1 = select.getChild("value", "0-FOO");
        TagTester option2 = select.getChild("value", "1-BAR_ED");
        TagTester option3 = select.getChild("value", "2-BAZ_");

        assertNotNull(option1);
        assertNotNull(option2);
        assertNotNull(option3);

        assertEquals("TestEnum-FOO", option1.getValue());
        assertEquals("TestEnum-BAR_ED", option2.getValue());
        assertEquals("TestEnum-BAZ_", option3.getValue());

    }

    @Test
    public void submit_setsModelObjectSelectionCorrectly() throws Exception {
        EnumDropDownChoice<TestEnum> component = new EnumDropDownChoice<>("testEnum", TestEnum.class);
        page.getForm().add(component);

        startPage(page);

        FormTester formTester = wicketTester.newFormTester("form");
        formTester.select("testEnum", 1);
        formTester.submit();

        assertEquals(TestEnum.BAR_ED, page.getForm().getModelObject().getTestEnum());
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

        private TestEnum testEnum;

        public TestEnum getTestEnum() {
            return testEnum;
        }

        public void setTestEnum(TestEnum testEnum) {
            this.testEnum = testEnum;
        }
    }

    private static enum TestEnum {
        FOO,
        BAR_ED,
        BAZ_
    }
}
