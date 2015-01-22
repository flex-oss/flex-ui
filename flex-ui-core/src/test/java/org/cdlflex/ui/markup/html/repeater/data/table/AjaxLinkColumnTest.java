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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

public class AjaxLinkColumnTest extends AbstractDataTableTest {
    @Override
    protected List<IColumn<Person, String>> getColumns() {
        List<IColumn<Person, String>> columns = new ArrayList<>();

        columns.add(new AjaxLinkColumn<Person, String>(Model.of("Ajax link")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onClick(AjaxRequestTarget target, IModel<Person> model, DataTableCell<Person, String> cell) {
                model.getObject().setName(model.getObject().getName() + "X");
                target.add(cell);
            }

            @Override
            protected IModel<String> getBodyModel(IModel<Person> model, DataTableCell<Person, String> cell) {
                return new PropertyModel<>(model, "name");
            }
        });

        return columns;
    }

    @Override
    protected Integer getRowsPerPage() {
        return 1;
    }

    @Test
    public void rendersLinkCorrectly() throws Exception {
        TagTester link = createTagByWicketId("link");
        assertEquals("javascript:;", link.getAttribute("href"));
        assertEquals("A", link.getValue());
    }
}
