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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Before;
import org.junit.Test;

public class DefaultDataTableTest extends AbstractWicketTest {

    TableTestPage page;
    DataTable<Person, String> table;

    @Before
    public void setUp() throws Exception {
        page = new TableTestPage();
    }

    protected DataTable<Person, String> createTable(String id, ITableSkeleton<Person, String> skeleton) {
        return new DefaultDataTable<>(id, skeleton.getColumns(), skeleton.getDataProvider(),
                skeleton.getRowsPerPage());
    }

    protected List<IColumn<Person, String>> getColumns() {
        List<IColumn<Person, String>> columns = new ArrayList<>();

        columns.add(new PropertyColumn<Person, String>(Model.of("Name"), "name"));
        columns.add(new PropertyColumn<Person, String>(Model.of("Birthday"), "birthday"));

        return columns;
    }

    @Test
    public void rendersNoFooterPerDefault() throws Exception {
        table =
            new DefaultDataTable<>("table", getColumns(), new SortableListDataProvider<Person, String>(
                    Person.createTestData()), 10);
        page.add(table);
        startPage(page);

        assertNull(createTagByWicketId("bottomToolbars"));
    }

    @Test
    public void rendersTopToolBarAsTableHeader() throws Exception {
        table =
            new DefaultDataTable<>("table", getColumns(), new SortableListDataProvider<Person, String>(
                    Person.createTestData()), 10);
        page.add(table);
        startPage(page);

        TagTester toolbars = createTagByWicketId("topToolbars");
        assertNotNull(toolbars);
        assertEquals("thead", toolbars.getName());
    }

    @Test
    public void rendersHeaderBarCorrectly() throws Exception {
        table =
            new DefaultDataTable<>("table", getColumns(), new SortableListDataProvider<Person, String>(
                    Person.createTestData()), 10);
        page.add(table);
        startPage(page);

        TagTester toolbars = createTagByWicketId("topToolbars");
        TagTester tr = toolbars.getChild("class", "headers");

        assertEquals("tr", tr.getName());
        // FIXME: TagTester really needs an extension to allow testing of multiple non-uniquely identifiable children
        assertTrue(tr.getMarkup().contains("<th"));
        assertTrue(tr.getMarkup().contains("Name</span>"));
        assertTrue(tr.getMarkup().contains("Birthday</span>"));
    }

    @Test
    public void noRecords_rendersNoRecordsToolbarCorrectly() throws Exception {
        table =
            new DefaultDataTable<>("table", getColumns(), new SortableListDataProvider<Person, String>(
                    new ArrayList<Person>()), 10);
        page.add(table);
        startPage(page);

        TagTester tfoot = createTagByWicketId("bottomToolbars");
        assertEquals("tfoot", tfoot.getName());

        TagTester div = tfoot.getChild("wicket:id", "msg");
        assertNotNull("tfoot has no msg element", div);
        assertEquals("No Records Found", div.getValue());
    }

    @Test
    public void noRecords_rendersEmptyBody() throws Exception {
        table =
            new DefaultDataTable<>("table", getColumns(), new SortableListDataProvider<Person, String>(
                    new ArrayList<Person>()), 10);
        page.add(table);
        startPage(page);

        TagTester tbody = createTagByWicketId("body");
        assertEquals("tbody", tbody.getName());

        assertEquals("Body should be empty", "", tbody.getValue().trim());
    }

    @Test
    public void paginated_rendersNavigationBarCorrectly() throws Exception {
        table =
            new DefaultDataTable<>("table", getColumns(), new SortableListDataProvider<Person, String>(
                    Person.createTestData()), 5);
        page.add(table);
        startPage(page);

        TagTester toolbars = createTagByWicketId("topToolbars");
        TagTester tr = toolbars.getChild("class", "navigation");

        assertEquals("tr", tr.getName());

        TagTester first = tr.getChild("wicket:id", "first");
        TagTester prev = tr.getChild("wicket:id", "prev");
        TagTester next = tr.getChild("wicket:id", "next");
        TagTester last = tr.getChild("wicket:id", "last");

        assertNotEquals("Navigator to 'first' should not be a link", "a", first.getName());
        assertNotEquals("Navigator to 'prev' should not be a link", "a", prev.getName());
        assertEquals("Navigator to 'next' should  be a link", "a", next.getName());
        assertEquals("Navigator to 'last' should  be a link", "a", last.getName());
    }
}
