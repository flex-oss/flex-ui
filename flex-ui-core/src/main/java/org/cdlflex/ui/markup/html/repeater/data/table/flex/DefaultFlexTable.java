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

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.cdlflex.ui.markup.html.repeater.data.table.ITableSkeleton;

/**
 * Provides a basic toolbar setup for a FlexTable with common elements (table header, navigation, no-records
 * placeholder).
 * 
 * @param <T> The Model object type
 * @param <S> The type of the sorting parameter
 */
public class DefaultFlexTable<T, S> extends FlexTable<T, S> {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_ROWS_PER_PAGE = 10;

    public DefaultFlexTable(String id, ITableSkeleton<T, S> skeleton) {
        this(id, skeleton.getColumns(), skeleton.getDataProvider(), skeleton.getRowsPerPage());
    }

    public DefaultFlexTable(String id, List<? extends IColumn<T, S>> columns, ISortableDataProvider<T, S> dataProvider) {
        this(id, columns, dataProvider, DEFAULT_ROWS_PER_PAGE);
    }

    public DefaultFlexTable(String id, List<? extends IColumn<T, S>> columns,
            ISortableDataProvider<T, S> dataProvider, int rowsPerPage) {
        super(id, columns, dataProvider, rowsPerPage);

        setOutputMarkupId(true);

        addTableHeader(new HeadersToolbar<>(this, dataProvider));
        addBottomToolbar(new NavigationToolbar(this));
        addBottomToolbar(new NoRecordsToolbar(this));
    }
}
