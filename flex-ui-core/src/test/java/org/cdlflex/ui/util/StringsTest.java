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
package org.cdlflex.ui.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class StringsTest {
    @Test
    public void joinInto_joinsIterableCorrectly() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        Strings.joinInto(stringBuilder, ",", Arrays.asList("0", "1", "2"));
        assertEquals("0,1,2", stringBuilder.toString());
    }

    @Test
    public void join_joinsIterableCorrectly() throws Exception {
        assertEquals("0,1,2", Strings.join(",", (Iterable<String>) Arrays.asList("0", "1", "2")));
    }

    @Test
    public void join_emptyIterable_returnsEmptyString() throws Exception {
        assertEquals("", Strings.join(",", (Iterable<String>) new ArrayList<String>()));
    }

    @Test
    public void join_nullIterable_returnsEmptyString() throws Exception {
        assertEquals("", Strings.join(",", (Iterable<String>) null));
    }
}
