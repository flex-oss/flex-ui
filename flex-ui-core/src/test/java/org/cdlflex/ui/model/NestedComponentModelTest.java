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
package org.cdlflex.ui.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.html.GenericMarkupContainer;
import org.junit.Before;
import org.junit.Test;

public class NestedComponentModelTest extends AbstractWicketTest {

    private IModel<String> model;
    private GenericMarkupContainer<String> outer;
    private GenericMarkupContainer<String> inner;

    @Before
    public void setUp() throws Exception {
        model = new Model<>("42");
        outer = new GenericMarkupContainer<>("outer", model);
        inner = new GenericMarkupContainer<>("inner", new NestedComponentModel<>());

        outer.add(inner);
    }

    @Test
    public void getModelObject_ofNestedComponent_returnsCorrectModelObject() throws Exception {
        assertEquals("42", inner.getModelObject());
    }

    @Test
    public void setModelObject_ofNestedComponent_changesModelCorrectly() throws Exception {
        inner.setModelObject("43");
        assertEquals("43", model.getObject());
        assertEquals("43", outer.getModelObject());
        assertEquals("43", inner.getModelObject());
    }

    @Test
    public void getObject_notAttached_throwsException() throws Exception {
        try {
            new NestedComponentModel<>().getObject(new GenericMarkupContainer<>("invalid"));
            fail("Exception should have been thrown");
        } catch (NullPointerException e) {
            assertEquals("Parent Component is null", e.getMessage());
        }
    }
}
