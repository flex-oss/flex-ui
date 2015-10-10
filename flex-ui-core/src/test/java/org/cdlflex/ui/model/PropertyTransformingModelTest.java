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

import org.apache.wicket.model.IModel;
import org.junit.Test;

public class PropertyTransformingModelTest {

    @Test
    public void getObject_returnsCorrectlyConvertedObject() throws Exception {
        IModel<String> model = new PropertyTransformingModel<>(new TestModel(1), "number", String::valueOf);

        assertEquals("1", model.getObject());
    }

    static class TestModel {
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
