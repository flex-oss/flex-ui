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
package org.cdlflex.ui.markup.html.repeater.data.table;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.util.convert.ToStringConverter;
import org.junit.Test;

public class PropertyConvertingColumnTest extends AbstractDataTableTest {
    @Override
    protected List<IColumn<Person, String>> getColumns() {
        List<IColumn<Person, String>> columns = super.getColumns();

        columns.add(new PropertyConvertingColumn<Person, String, String>(Model.of("NameX"), "name", "name",
                new ToStringConverter<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String convertToString(String value) {
                        return value + "X";
                    }
                }));

        return columns;
    }

    @Override
    protected Integer getRowsPerPage() {
        return 1;
    }

    @Test
    public void rendersConvertedPropertyValueCorrectly() throws Exception {
        TagTester cell = createTagByWicketId("cell");
        assertEquals("AX", cell.getValue());
    }
}
