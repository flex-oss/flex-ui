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
package org.cdlflex.ui.fruit;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.cdlflex.fruit.Identifiable;
import org.cdlflex.fruit.OrderBy;
import org.cdlflex.fruit.Repository;
import org.cdlflex.fruit.SortOrder;

/**
 * SortableDataProvider that wraps a Repository to load data.
 * 
 * @param <T> The Identifiable type of the repository.
 */
public abstract class SortableRepositoryDataProvider<T extends Identifiable<?>> extends
        SortableDataProvider<T, String> {
    private static final long serialVersionUID = 1L;

    @Override
    public Iterator<? extends T> iterator(long first, long count) {
        OrderBy orderBy = getOrderBy(getSort());

        List<T> page;
        if (orderBy == null) {
            page = getRepository().getPage((int) count, (int) first);
        } else {
            page = getRepository().getPage(orderBy, (int) count, (int) first);
        }

        return page.iterator();
    }

    /**
     * Creates an OrderBy instance from the given SortParam.
     * 
     * @param sortParam the sort parameter
     * @return a new OrderBy instance
     */
    public OrderBy getOrderBy(SortParam<String> sortParam) {
        if (sortParam == null) {
            return null;
        }

        return new OrderBy(sortParam.getProperty(), sortParam.isAscending() ? SortOrder.ASC : SortOrder.DESC);
    }

    @Override
    public long size() {
        return getRepository().count();
    }

    @Override
    public IModel<T> model(final T object) {
        final Object id = object.getId();

        if (id == null) {
            return Model.of(object);
        }

        return new LoadableDetachableModel<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            protected T load() {
                return getRepository().get(id);
            }
        };
    }

    /**
     * Returns the used Repository.
     *
     * @return a Repository
     */
    public abstract Repository<T> getRepository();

}
