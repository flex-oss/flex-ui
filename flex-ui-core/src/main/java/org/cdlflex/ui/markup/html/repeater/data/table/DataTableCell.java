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

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;

/**
 * Extension of a cell Item that provides access to the row and table it belongs to.
 *
 * @param <T> Model object type
 * @param <S> The type of the sorting parameter
 */
public class DataTableCell<T, S> extends Item<IColumn<T, S>> {

    private static final long serialVersionUID = 1L;

    private DataTable<T, S> table;

    DataTableCell(String id, int index, IModel<IColumn<T, S>> model, DataTable<T, S> table) {
        super(id, index, model);
        this.table = table;
    }

    /**
     * Returns the DataTable that holds this cell.
     * 
     * @return a data table
     */
    public DataTable<T, S> getTable() {
        return table;
    }

    /**
     * Returns the row Item that holds this cell.
     * 
     * @return a row Item
     */
    @SuppressWarnings("unchecked")
    public Item<T> getRowItem() {
        // FIXME: generalize this
        return (Item<T>) this.getParent().getParent();
    }

    /**
     * Returns the model of the row. Convenience accessor to {@code ListItem#getModel()} of the row Item.
     * 
     * @return the model of the row
     */
    public IModel<T> getRowModel() {
        return getRowItem().getModel();
    }

}
