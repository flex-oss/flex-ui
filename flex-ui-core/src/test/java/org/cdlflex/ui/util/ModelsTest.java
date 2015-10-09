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
/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cdlflex.ui.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.apache.wicket.model.Model;
import org.junit.Test;

public class ModelsTest {
    @Test
    public void isNull_onNullObject_returnsTrue() throws Exception {
        assertTrue(Models.isNull(null));
    }

    @Test
    public void isNull_onNullModelObject_returnsTrue() throws Exception {
        assertTrue(Models.isNull(Model.of()));
    }

    @Test
    public void isNull_onModelObject_returnsFalse() throws Exception {
        assertFalse(Models.isNull(Model.of("SomeModel")));
        assertFalse(Models.isNull(Model.of(0)));
    }

    @Test
    public void isEmpty_onNullObject_returnsTrue() throws Exception {
        assertTrue(Models.isEmpty(null));
    }

    @Test
    public void isEmpty_onEmptyStringModelObject_returnsTrue() throws Exception {
        assertTrue(Models.isEmpty(Model.of("")));
    }

    @Test
    public void isEmpty_onStringModel_returnsFalse() throws Exception {
        assertFalse(Models.isEmpty(Model.of("42")));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test(expected = ClassCastException.class)
    public void isEmpty_onIllegalModel_throwsException() throws Exception {
        Model model = Model.of(0);
        Models.isEmpty(model);
    }

    @Test
    public void optional_onNullModel_returnEmpty() throws Exception {
        assertFalse(Models.optional(null).isPresent());
    }

    @Test
    public void optional_onModelWithNullObject_returnEmpty() throws Exception {
        assertFalse(Models.optional(Model.of((String) null)).isPresent());
    }

    @Test
    public void optional_onModelWithObject_returnValue() throws Exception {
        Optional<String> optional = Models.optional(Model.of("42"));

        assertTrue(optional.isPresent());
        assertEquals("42", optional.get());
    }
}
