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

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.tester.DummyHomePage;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

public class AbstractLinkColumnTest extends AbstractDataTableTest {
    @Override
    protected List<IColumn<Person, String>> getColumns() {
        AbstractLinkColumn<Person, String> column = new AbstractLinkColumn<Person, String>(Model.of("Name")) {
            private static final long serialVersionUID = 1L;

            @Override
            public AbstractLink newLink(String id, DataTableCell<Person, String> cell) {
                IModel<String> bodyModel = new PropertyModel<>(cell.getRowModel(), "name");
                return new BookmarkablePageLink<Void>(id, DummyHomePage.class).setBody(bodyModel);
            }
        };

        return Arrays.<IColumn<Person, String>>asList(column);
    }

    @Test
    public void rendersLinkCorrectly() throws Exception {
        TagTester cell = createTagById("cell1");
        TagTester link = cell.getChild("wicket:id", "link");

        assertEquals("./bookmarkable/org.apache.wicket.util.tester.DummyHomePage", link.getAttribute("href"));
        assertEquals("A", link.getValue());
    }

}
