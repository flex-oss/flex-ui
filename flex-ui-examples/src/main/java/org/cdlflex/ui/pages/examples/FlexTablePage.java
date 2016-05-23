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
package org.cdlflex.ui.pages.examples;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.markup.html.repeater.data.table.ActionsColumn;
import org.cdlflex.ui.markup.html.repeater.data.table.DataTableCell;
import org.cdlflex.ui.markup.html.repeater.data.table.DatePropertyColumn;
import org.cdlflex.ui.markup.html.repeater.data.table.flex.DefaultFlexTableBuilder;
import org.cdlflex.ui.model.Person;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * FlexTablePage.
 */
public class FlexTablePage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    public FlexTablePage() {
        add(newFlexTable("flex-table"));
        add(newFlexTablePagination("flex-table-pagination"));
    }

    private Component newFlexTable(String id) {
        return new DefaultFlexTableBuilder<Person, String>()
                .add(new PropertyColumn<Person, String>(Model.of("Name"), "name", "name"))
                .add(new DatePropertyColumn<Person, String>(Model.of("Birthday"), "birthday", "birthday"))
                .add(new ActionsColumn<Person, String>(Model.of("")) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected List<AbstractLink> createActions(String componentId, IModel<Person> rowModel, DataTableCell<Person, String> cell) {
                        return null;
                    }
                })
                .data(Person.createExampleData()).create(id);
    }

    private Component newFlexTablePagination(String id) {
        return new DefaultFlexTableBuilder<Person, String>()
                .add(new PropertyColumn<Person, String>(Model.of("Name"), "name", "name"))
                .add(new DatePropertyColumn<Person, String>(Model.of("Birthday"), "birthday", "birthday"))
                .add(new ActionsColumn<Person, String>(Model.of("")) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected List<AbstractLink> createActions(String componentId, IModel<Person> rowModel, DataTableCell<Person, String> cell) {
                        return null;
                    }
                })
                .set(2)
                .data(Person.createExampleData()).create(id);
    }
}
