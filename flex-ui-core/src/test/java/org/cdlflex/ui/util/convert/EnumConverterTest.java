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
package org.cdlflex.ui.util.convert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class EnumConverterTest {
    @Test
    public void convertToString_convertsCorrectly() throws Exception {
        EnumConverter<TestEnum> converter = new EnumConverter<>(TestEnum.class);
        assertEquals("FOO", converter.convertToString(TestEnum.FOO, null));
        assertEquals("BAR_ED", converter.convertToString(TestEnum.BAR_ED, null));
        assertEquals("BAZ_", converter.convertToString(TestEnum.BAZ_, null));
    }

    @Test
    public void convertToString_withoutEnumClassInstance_convertsCorrectly() throws Exception {
        EnumConverter<TestEnum> converter = new EnumConverter<>();
        assertEquals("FOO", converter.convertToString(TestEnum.FOO, null));
    }

    @Test
    public void convertToObject_convertsCorrectly() throws Exception {
        EnumConverter<TestEnum> converter = new EnumConverter<>(TestEnum.class);
        assertEquals(TestEnum.FOO, converter.convertToObject("FOO", null));
        assertEquals(TestEnum.BAR_ED, converter.convertToObject("BAR_ED", null));
        assertEquals(TestEnum.BAZ_, converter.convertToObject("BAZ_", null));
    }

    @Test
    public void convertToObject_fromEmptyString_returnsNull() throws Exception {
        EnumConverter<TestEnum> converter = new EnumConverter<>(TestEnum.class);
        assertNull(converter.convertToObject("", null));
    }

    @Test
    public void convertToObject_fromNullString_returnsNull() throws Exception {
        EnumConverter<TestEnum> converter = new EnumConverter<>(TestEnum.class);
        assertNull(converter.convertToObject(null, null));
    }

    @Test
    public void convertToObject_withoutEnumClassInstance_returnsNull() throws Exception {
        EnumConverter<TestEnum> converter = new EnumConverter<>();
        assertNull(converter.convertToObject("FOO", null));
    }
}
