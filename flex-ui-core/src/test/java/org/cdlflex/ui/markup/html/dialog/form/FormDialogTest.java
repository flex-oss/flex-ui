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
package org.cdlflex.ui.markup.html.dialog.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Before;
import org.junit.Test;

public class FormDialogTest extends AbstractWicketTest {

    TestFormDialog component;
    TagTester tag;

    @Before
    public void setUp() throws Exception {
        component = new TestFormDialog("test-form-dialog");
        component.setModel(Model.of(new TestModel()));
        component.setTitle(Model.of("Header"));

        tag = renderAndCreateTag(component);
    }

    @Test
    public void defaultDialog_rendersFormInBody() throws Exception {
        TagTester body = tag.getChild("wicket:id", "modal-body");
        TagTester form = body.getChild("wicket:id", "form");

        assertNotNull(form);
        assertEquals("post", form.getAttribute("method"));
        assertNotNull("There is no IFormSubmitListener", form.getAttribute("action").contains("IFormSubmitListener"));
        assertTrue("Form did not render body correctly", form.getValue().contains("This is a form!"));
    }

    @Test
    public void defaultDialog_rendersSubmitAndCancelButtons() throws Exception {
        TagTester footer = tag.getChild("wicket:id", "modal-footer");

        // FIXME: can't get cancel button individually because it doesn't render an id
        assertTrue("There is no cancel button", footer.getMarkup().contains("Cancel"));

        TagTester submit = footer.getChild("id", "button4");
        assertNotNull("There is no submit button", submit);
        assertEquals("Submit", submit.getValue());
    }

    private class TestFormDialog extends FormDialog<TestModel> {
        private static final long serialVersionUID = 1L;

        public TestFormDialog(String markupId) {
            super(markupId);
        }

        @Override
        protected Form<TestModel> newForm(IModel<TestModel> model) {
            return new Form<>("form", new CompoundPropertyModel<>(model));
        }
    }

    private static class TestModel implements IClusterable {
        private static final long serialVersionUID = 1L;

    }
}
