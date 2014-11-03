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

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Special ITableAware tagged column used in cdlflex DataTables. It populates the table with DataTableCell instances.
 * 
 * @param <T> The model object type
 * @param <S> the type of the sorting parameter
 */
public abstract class DataTableColumn<T, S> extends AbstractColumn<T, S> implements ITableAware<T, S> {

    private static final long serialVersionUID = 1L;

    private DataTable<T, S> table;

    public DataTableColumn() {
        this(Model.of(""));
    }

    public DataTableColumn(IModel<String> displayModel) {
        super(displayModel);
    }

    public DataTableColumn(IModel<String> displayModel, S sortProperty) {
        super(displayModel, sortProperty);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
        if (cellItem instanceof DataTableCell) {
            populateItem((DataTableCell) cellItem, componentId);
        } else {
            throw new IllegalStateException("DataTableColumn expectes a DataTableCell, was " + cellItem.getClass());
        }
    }

    @Override
    public DataTable<T, S> getTable() {
        return table;
    }

    @Override
    public void setTable(DataTable<T, S> table) {
        this.table = table;
        onBind(table);
    }

    /**
     * Hook called when the table is set.
     *
     * @param dataTable the data table the column is added to
     */
    protected void onBind(DataTable<T, S> dataTable) {
        // hook
    }

    /**
     * Populates the given cellItem.
     * 
     * Propagated from
     * {@code IColumn#populateItem(org.apache.wicket.markup.repeater.Item,String, org.apache.wicket.model.IModel)} where
     * a DataTableCell is expected.
     *
     * @param cell the cell being populated
     * @param componentId the component id
     */
    protected abstract void populateItem(final DataTableCell<T, S> cell, final String componentId);

}
