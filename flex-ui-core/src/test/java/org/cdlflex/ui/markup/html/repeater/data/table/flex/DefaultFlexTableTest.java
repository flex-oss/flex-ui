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
package org.cdlflex.ui.markup.html.repeater.data.table.flex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.html.repeater.data.table.Person;
import org.cdlflex.ui.markup.html.repeater.data.table.SortableListDataProvider;
import org.cdlflex.ui.markup.html.repeater.data.table.TableTestPage;
import org.junit.Before;
import org.junit.Test;

public class DefaultFlexTableTest extends AbstractWicketTest {
    TableTestPage page;
    FlexTable<Person, String> table;

    @Before
    public void setUp() throws Exception {
        page = new TableTestPage();
    }

    @Test
    public void rendersRootTagAsDiv() throws Exception {
        table =
            new DefaultFlexTable<>("table", getColumns(), new SortableListDataProvider<Person, String>(
                    Person.createTestData()), 10);
        page.add(table);
        startPage(page);

        TagTester container = createTag(table);

        assertEquals("div", container.getName());
        assertTrue("FlexTableContainer does not contain a table tag", container.getMarkup().contains("<table"));
    }

    @Test
    public void rendersPaginationToolbarAsDivOutsideTable() throws Exception {
        table =
            new DefaultFlexTable<>("table", getColumns(), new SortableListDataProvider<Person, String>(
                    Person.createTestData()), 5);
        page.add(table);
        startPage(page);

        TagTester bottomToolbars = createTagByWicketId("bottomToolbars");
        assertEquals("div", bottomToolbars.getName());
    }

    protected List<? extends IColumn<Person, String>> getColumns() {
        List<IColumn<Person, String>> columns = new ArrayList<>();

        columns.add(new PropertyColumn<Person, String>(Model.of("Name"), "name"));
        columns.add(new PropertyColumn<Person, String>(Model.of("Birthday"), "birthday"));

        return columns;
    }
}
