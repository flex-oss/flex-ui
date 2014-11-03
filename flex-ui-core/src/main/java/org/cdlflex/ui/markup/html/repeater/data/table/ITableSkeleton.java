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

import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;

/**
 * A service interface that helps to build DataTable instance.
 *
 * @param <T> The Model object type
 * @param <S> The type of the sorting parameter
 */
public interface ITableSkeleton<T, S> extends IColumnProvider<T, S> {

    /**
     * Returns the data provider used.
     * 
     * @return a data provider
     */
    ISortableDataProvider<T, S> getDataProvider();

    /**
     * Returns the amount of rows per page to show.
     * 
     * @return rows per page to show
     */
    Integer getRowsPerPage();
}
