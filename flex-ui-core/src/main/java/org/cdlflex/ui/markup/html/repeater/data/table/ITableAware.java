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

/**
 * Interface for columns of DataTable that know of the table they are attached to.
 * 
 * @param <T> The model object type
 * @param <S> the type of the sorting parameter
 */
public interface ITableAware<T, S> extends IColumn<T, S> {
    /**
     * Return the table this column is attached to.
     * 
     * @return a DataTable
     */
    DataTable<T, S> getTable();

    /**
     * Sets the table this column is attached to. Called by the table itself.
     * 
     * @param table a DataTable
     */
    void setTable(DataTable<T, S> table);
}
