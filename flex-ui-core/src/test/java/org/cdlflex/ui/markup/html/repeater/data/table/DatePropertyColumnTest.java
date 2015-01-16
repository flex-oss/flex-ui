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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

public class DatePropertyColumnTest extends AbstractDataTableTest {

    @Override
    protected Integer getRowsPerPage() {
        return 1;
    }

    @Override
    protected List<IColumn<Person, String>> getColumns() {
        DatePropertyColumn<Person, String> column = new DatePropertyColumn<>(Model.of("Birthday"), "birthday");
        column.setDateFormat(new SimpleDateFormat("y-MM-dd"));

        return Arrays.<IColumn<Person, String>>asList(column);
    }

    @Test
    public void datePropertyColumn_withSetDateFormat_rendersCellCorrectly() throws Exception {
        TagTester cell = createTagByWicketId("cell");
        assertEquals("2001-01-01", cell.getValue());
    }
}
