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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.DummyHomePage;
import org.apache.wicket.util.tester.DummyPanelPage;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

public class ActionsColumnTest extends AbstractDataTableTest {
    @Override
    protected List<IColumn<Person, String>> getColumns() {
        IColumn<Person, String> column = new ActionsColumn<Person, String>(Model.of("Links"), Model.of("Dropdown")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> createActions(String id, IModel<Person> rowModel,
                DataTableCell<Person, String> cell) {

                return Arrays.asList(
                        new BookmarkablePageLink<>(id, DummyHomePage.class).setBody(Model.of("HomeLink")),
                        new BookmarkablePageLink<>(id, DummyPanelPage.class).setBody(Model.of("PanelLink")));
            }
        };

        return Arrays.asList(column);
    }

    @Override
    protected Integer getRowsPerPage() {
        return 1;
    }

    @Test
    public void rendersDropDownLinkCorrectly() throws Exception {
        TagTester cell = createTagByWicketId("cell");

        TagTester btn = cell.getChild("wicket:id", "btn");
        assertCssClasses(btn, "btn", "btn-default", "dropdown-toggle");
        assertTrue(btn.getValue().contains("Dropdown"));

        TagTester menu = cell.getChild("wicket:id", "dropdown-menu");
        assertNotNull("Link does not contain a dropdown menu", menu);
        assertTrue("Menu does not contain a link to DummyHomePage", menu.getMarkup().contains("HomeLink"));
        assertTrue("Menu does not contain a link to DummyPanelPage", menu.getMarkup().contains("PanelLink"));
    }
}
