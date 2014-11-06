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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.html.button.DropDownButton;
import org.cdlflex.ui.markup.html.repeater.data.table.ActionsColumn;
import org.cdlflex.ui.markup.html.repeater.data.table.DataTableCell;
import org.cdlflex.ui.markup.html.repeater.data.table.DefaultDataTable;
import org.cdlflex.ui.markup.html.repeater.data.table.IRowCallback;
import org.cdlflex.ui.markup.html.repeater.data.table.PropertyConvertingColumn;
import org.cdlflex.ui.markup.html.repeater.data.table.SortableListDataProvider;
import org.cdlflex.ui.model.Person;
import org.cdlflex.ui.pages.ExamplePage;
import org.cdlflex.ui.util.convert.ToStringConverter;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.ajax.JsAjaxLink;
import org.rauschig.wicketjs.markup.html.JsLink;

public class TablePage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    public TablePage() {
        add(newDefaultDataTable("default-data-table"));
        add(newDefaultDataTableActions("default-data-table-actions"));
    }

    private Component newDefaultDataTable(String id) {
        List<IColumn<Person, String>> columns = new ArrayList<>();

        columns.add(new PropertyColumn<Person, String>(Model.of("ID"), "id", "id"));
        columns.add(new PropertyColumn<Person, String>(Model.of("Name"), "name", "name"));
        columns.add(new PropertyColumn<Person, String>(Model.of("Birthday"), "birthday", "birthday"));
        columns.add(new PropertyConvertingColumn<Person, String, Date>(Model.of("Age"), "birthday", "birthday",
                new ToStringConverter<Date>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String convertToString(Date value) {
                        long dif = System.currentTimeMillis() - value.getTime();
                        return String.valueOf(dif / 1000 / 60 / 60 / 24 / 365);
                    }
                }));

        return new DefaultDataTable<>(id, columns, new SortableListDataProvider<Person, String>(
                Person.createExampleData()), 10);
    }

    private Component newDefaultDataTableActions(String id) {
        List<IColumn<Person, String>> columns = new ArrayList<>();

        columns.add(new PropertyColumn<Person, String>(Model.of("ID"), "id", "id"));
        columns.add(new PropertyColumn<Person, String>(Model.of("Name"), "name", "name"));
        columns.add(new ActionsColumn<Person, String>(Model.of(""), GlyphIconType.COG) {

            @Override
            protected void onDropDownButtonInitialize(DropDownButton button, IModel<Person> model) {
                button.setSize(Buttons.Size.MINI);
            }

            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> createActions(String id, final IModel<Person> row,
                final DataTableCell<Person, String> cell) {
                List<AbstractLink> links = new ArrayList<>();

                links.add(new JsLink(id) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public IJavaScript onClick() {
                        return new JsCall("alert", "clicked " + row.getObject().getName());
                    }
                }.setBody(Model.of("Alert person")));

                links.add(new JsAjaxLink<Person>(id, row) {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        row.getObject().setName(row.getObject().getName() + "!");
                        target.add(cell.getRowItem());
                    }
                }.setBody(Model.of("Add bang")));

                return links;
            }
        });

        DefaultDataTable<Person, String> table =
            new DefaultDataTable<>(id, columns, new SortableListDataProvider<Person, String>(
                    Person.createExampleData()), 10);

        table.getRowCallbacks().add(new IRowCallback<Person>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void call(Item<Person> rowItem) {
                rowItem.setOutputMarkupId(true);
            }
        });

        table.add(new ActionsColumn.ToggleOnHoverBehavior());

        return table;
    }
}
