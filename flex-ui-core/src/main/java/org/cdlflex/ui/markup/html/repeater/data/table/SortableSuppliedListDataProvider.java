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

import java.io.Serializable;
import java.util.List;

import org.cdlflex.ui.util.function.SerializableSupplier;

/**
 * A SortableListDataProvider that gets its list-data from a Supplier.
 *
 * @param <T> the type of the provided data model
 * @param <S> the type of the sorting parameter
 */
public class SortableSuppliedListDataProvider<T extends Serializable, S> extends SortableListDataProvider<T, S> {

    private static final long serialVersionUID = 1L;

    private SerializableSupplier<List<T>> supplier;

    public SortableSuppliedListDataProvider(SerializableSupplier<List<T>> supplier) {
        this.supplier = supplier;
    }

    @Override
    protected final List<T> getListData() {
        return supplier.get();
    }
}
