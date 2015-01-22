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

import static org.junit.Assert.assertSame;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Test;

public class GenericMarkupContainerTest extends AbstractWicketTest {
    @Test
    public void setModel_setsDefaultModel() throws Exception {
        IModel<String> model = Model.of("String");
        GenericMarkupContainer<String> container = new GenericMarkupContainer<>("id");
        container.setModel(model);
        assertSame(model, container.getDefaultModel());
    }

    @Test
    public void getModel_returnsDefaultModel() throws Exception {
        IModel<String> model = Model.of("String");
        GenericMarkupContainer<String> container = new GenericMarkupContainer<>("id", model);
        assertSame(container.getDefaultModel(), container.getModel());
    }

    @Test
    public void getModelObject_returnsDefaultModelObject() throws Exception {
        IModel<String> model = Model.of("String");
        GenericMarkupContainer<String> container = new GenericMarkupContainer<>("id", model);
        assertSame(container.getDefaultModelObject(), container.getModelObject());
        assertSame(model.getObject(), container.getModelObject());
    }

    @Test
    public void setModelObject_setsDefaultModelObject() throws Exception {
        IModel<String> model = Model.of("String");
        GenericMarkupContainer<String> container = new GenericMarkupContainer<>("id", model);
        container.setModelObject("42");

        assertSame(container.getDefaultModelObject(), container.getModelObject());
        assertSame(model.getObject(), container.getModelObject());
    }
}
