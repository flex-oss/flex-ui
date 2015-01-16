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
package org.cdlflex.ui.markup.html.basic;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.model.Model;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.util.convert.ToStringConverter;
import org.junit.Before;
import org.junit.Test;

public class ConvertingLabelTest extends AbstractWicketTest {
    ConvertingLabel<Integer> label;

    @Before
    public void setUp() throws Exception {
        label = new ConvertingLabel<>("label", Model.of(0), new ToStringConverter<Integer>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String convertToString(Integer value) {
                return String.valueOf(value + 1);
            }
        });
    }

    @Test
    public void getModelObject_returnsCorrectObject() throws Exception {
        assertEquals(0, (int) label.getModelObject());
    }

    @Test(expected = NullPointerException.class)
    // FIXME: should not throw an exception, but work transparently
    public void getDefaultModelObjectAsString_withoutConverter_throwsNullPointerException() throws Exception {
        label = new ConvertingLabel<>("label", Model.of(0));
        assertEquals("0", label.getDefaultModelObjectAsString());
    }

    @Test
    public void getDefaultModelObjectAsString_returnsConvertedObject() throws Exception {
        assertEquals("1", label.getDefaultModelObjectAsString());
    }

    @Test
    public void getDefaultModelObjectAsString_afterUpdatingModelObject_returnsConvertedObject() throws Exception {
        label.setModelObject(41);
        assertEquals("42", label.getDefaultModelObjectAsString());
    }

    @Test
    public void getDefaultModelObjectAsString_afterUpdatingModel_returnsConvertedObject() throws Exception {
        label.setModel(Model.of(41));
        assertEquals("42", label.getDefaultModelObjectAsString());
    }

    @Test
    public void getDefaultModelObjectAsString_afterUpdatingConverter_returnsConvertedObject() throws Exception {
        label.setConverter(new ToStringConverter<Integer>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String convertToString(Integer value) {
                return String.valueOf(value == 0);
            }
        });

        assertEquals("true", label.getDefaultModelObjectAsString());
    }

    @Test
    public void rendersCorrectly() throws Exception {
        assertEquals("1", renderAndCreateTag(label).getValue());
    }
}
