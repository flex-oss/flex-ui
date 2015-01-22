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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ButtonMultipleChoiceTest extends AbstractWicketTest {

    List<String> choices = new ArrayList<>(Arrays.asList("ChoiceA", "ChoiceB", "ChoiceC"));
    TestPage page;
    ButtonMultipleChoice<String> component;

    @Before
    public void setUp() throws Exception {
        page = new TestPage();
        component = new ButtonMultipleChoice<>("selection", choices);
        component.setOutputMarkupId(true).setMarkupId("selection");
        page.getForm().add(component);
    }

    @Test
    public void defaultFormInput_rendersInputInsideLabelCorrectly() throws Exception {
        String markup = render(page);

        TagTester label = TagTester.createTagByAttribute(markup, "for", "selection-selection_0");
        assertNotNull(label);
        assertEquals("selection-selection_0", label.getAttribute("for"));
        assertCssClasses(label, "btn", "btn-default");

        TagTester input = label.getChild("type", "checkbox");
        assertNotNull(input);

        assertTrue("Label did not contain selection label", label.getValue().contains("ChoiceA"));
    }

    @Test
    public void defaultFormInput_rendersRadioButtonValuesCorrectly() throws Exception {
        String markup = render(page);

        TagTester selection0 = createTagById(markup, "selection-selection_0");
        TagTester selection1 = createTagById(markup, "selection-selection_1");
        TagTester selection2 = createTagById(markup, "selection-selection_2");

        assertEquals("0", selection0.getAttribute("value"));
        assertEquals("1", selection1.getAttribute("value"));
        assertEquals("2", selection2.getAttribute("value"));
    }

    @Test
    public void defultFormInput_rendersThreeSelections() throws Exception {
        String markup = render(page);

        assertNotNull(createTagById(markup, "selection-selection_0"));
        assertNotNull(createTagById(markup, "selection-selection_1"));
        assertNotNull(createTagById(markup, "selection-selection_2"));
        assertNull(createTagById(markup, "selection-selection_3"));
    }

    @Test
    @Ignore
    public void setButtonType_rendersLabelsCorrectly() throws Exception {
        // FIXME: component needs to support this!
        // component.setButtonSize(Buttons.Size.SMALL);
        // component.setButtonType(Buttons.Type.PRIMARY);

        String markup = render(page);

        TagTester label0 = TagTester.createTagByAttribute(markup, "for", "selection-selection_0");
        TagTester label1 = TagTester.createTagByAttribute(markup, "for", "selection-selection_1");
        TagTester label2 = TagTester.createTagByAttribute(markup, "for", "selection-selection_2");

        assertCssClasses(label0, "btn", "btn-sm", "btn-primary");
        assertCssClasses(label1, "btn", "btn-sm", "btn-primary");
        assertCssClasses(label2, "btn", "btn-sm", "btn-primary");
    }

    @Test
    public void submit_setsSelectionCorrectly() throws Exception {
        startPage(page);

        FormTester form = wicketTester.newFormTester("form");

        form.selectMultiple("selection", new int[] { 1, 2 });
        form.submit();

        List<String> selection = page.getForm().getModelObject().getSelection();
        assertEquals(2, selection.size());
        assertTrue("Selection " + selection + " does not contain ChoiceB", selection.contains("ChoiceB"));
        assertTrue("Selection " + selection + " does not contain ChoiceC", selection.contains("ChoiceC"));
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

        private List<String> selection;

        public List<String> getSelection() {
            return selection;
        }

        public void setSelection(List<String> selection) {
            this.selection = selection;
        }
    }
}
