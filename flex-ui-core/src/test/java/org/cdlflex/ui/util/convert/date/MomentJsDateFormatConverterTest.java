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
package org.cdlflex.ui.util.convert.date;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This does not test the java <-> momentjs date format token mapping, only the basic functionality.
 */
public class MomentJsDateFormatConverterTest {
    DateFormatConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new MomentJsDateFormatConverter();
    }

    @Test
    public void convert_convertsCorrectly() throws Exception {
        // 1999-01-23 19:03
        assertEquals("YYYY-MM-DD HH:mm", converter.convert("y-MM-dd HH:mm"));
    }

    @Test
    public void convert_escapedString() throws Exception {
        assertEquals("YYYY [escaped] YYYY", converter.convert("y 'escaped' yyyy"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void convert_unknownToken_throwsException() throws Exception {
        converter.convert("F");
    }

}
