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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Before;

public abstract class AbstractDataTableTest extends AbstractWicketTest {

    TableTestPage page;
    DataTable<Person, String> table;

    @Before
    public void setUp() throws Exception {
        page = new TableTestPage();
        table = createTable("table");
        page.add(table);
        startPage(page);
    }

    public TableTestPage getPage() {
        return page;
    }

    public DataTable<Person, String> getTable() {
        return table;
    }

    protected DataTable<Person, String> createTable(String id) {
        return createTable(id, new ITableSkeleton<Person, String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public ISortableDataProvider<Person, String> getDataProvider() {
                return AbstractDataTableTest.this.getDataProvider();
            }

            @Override
            public Integer getRowsPerPage() {
                return AbstractDataTableTest.this.getRowsPerPage();
            }

            @Override
            public List<IColumn<Person, String>> getColumns() {
                return AbstractDataTableTest.this.getColumns();
            }
        });
    }

    protected DataTable<Person, String> createTable(String id, ITableSkeleton<Person, String> skeleton) {
        return new DataTable<>(id, skeleton);
    }

    protected ISortableDataProvider<Person, String> getDataProvider() {
        return new SortableListDataProvider<>(Person.createTestData());
    }

    protected Integer getRowsPerPage() {
        return 10;
    }

    protected List<IColumn<Person, String>> getColumns() {
        return new ArrayList<>();
    }
}
