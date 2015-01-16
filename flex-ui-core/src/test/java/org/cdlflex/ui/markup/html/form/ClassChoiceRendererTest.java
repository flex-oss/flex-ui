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

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Test;

public class ClassChoiceRendererTest extends AbstractWicketTest {
    @Test
    public void getDisplayValue_withoutLocalization_returnsSimpleClassName() throws Exception {
        ClassChoiceRenderer renderer = new ClassChoiceRenderer();
        assertEquals("ClassChoiceRenderer", renderer.getDisplayValue(ClassChoiceRenderer.class));
    }

    @Test
    public void getIdValue_withoutLocalization_returnsCanonicalClassName() throws Exception {
        ClassChoiceRenderer renderer = new ClassChoiceRenderer();
        assertEquals("org.cdlflex.ui.markup.html.form.ClassChoiceRenderer",
                renderer.getIdValue(ClassChoiceRenderer.class, 0));
    }

    @Test
    public void getDisplayValue_withLocalization_existingKey_returnsKeyValue() throws Exception {
        ClassChoiceRenderer renderer = new ClassChoiceRenderer(new ClassLocalizer());
        assertEquals("Class Choice Renderer", renderer.getDisplayValue(ClassChoiceRenderer.class));
    }

    @Test
    public void getDisplayValue_withLocalization_nonExistingKey_returnsSimpleClassName() throws Exception {
        ClassChoiceRenderer renderer = new ClassChoiceRenderer(new ClassLocalizer());
        assertEquals("String", renderer.getDisplayValue(String.class));
    }

    @Test
    public void getIdValue_withLocalization_existingKey_returnsCanonicalClassName() throws Exception {
        ClassChoiceRenderer renderer = new ClassChoiceRenderer(new ClassLocalizer());
        assertEquals("org.cdlflex.ui.markup.html.form.ClassChoiceRenderer",
                renderer.getIdValue(ClassChoiceRenderer.class, 0));
    }

    @Test
    public void getIdValue_withLocalization_nonExistingKey_returnsCanonicalClassName() throws Exception {
        ClassChoiceRenderer renderer = new ClassChoiceRenderer(new ClassLocalizer());
        assertEquals("java.lang.String", renderer.getIdValue(String.class, 0));
    }

    private class ClassLocalizer extends WebMarkupContainer {

        private static final long serialVersionUID = 1L;

        public ClassLocalizer() {
            super("LocalizationProvider");
        }
    }
}
