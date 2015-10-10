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

import java.io.Serializable;

import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Test;

public class ComponentPropertyTransformingModelTest extends AbstractWicketTest {

    @Test
    public void convertsComponentPropertyCorrectly() throws Exception {
        String markup = ComponentRenderer.renderComponent(new TestComponent("component")).toString();

        TagTester label = TagTester.createTagByAttribute(markup, "wicket:id", "label");

        assertEquals("+42", label.getValue());
    }

    private static class TestComponent extends GenericPanel<TestModel> {
        private static final long serialVersionUID = 1L;

        public TestComponent(String id) {
            super(id, new Model<>(new TestModel(42)));

            add(new Label("label", new ComponentPropertyTransformingModel<Integer, String>("number", i -> "+" + i)));
        }
    }

    private static class TestModel implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer number;

        public TestModel(Integer number) {
            this.number = number;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }

}
