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

import org.apache.wicket.model.IModel;
import org.cdlflex.fruit.Filter;
import org.cdlflex.fruit.Identifiable;
import org.cdlflex.fruit.OrderBy;

/**
 * SortableRepositoryDataProvider that holds an additional Filter.
 * 
 * @param <T> The Identifiable type of the repository.
 */
public abstract class FilterableRepositoryDataProvider<T extends Identifiable<?>> extends
        SortableRepositoryDataProvider<T> {

    private static final long serialVersionUID = 1L;

    private IModel<Filter> filterModel;

    public FilterableRepositoryDataProvider() {
        super();
    }

    public FilterableRepositoryDataProvider(IModel<Filter> filterModel) {
        this.filterModel = filterModel;
    }

    @Override
    public Iterator<? extends T> iterator(long first, long count) {
        Filter filter = getFilter();

        if (filter == null) {
            return super.iterator((int) count, (int) first);
        }

        OrderBy orderBy = getOrderBy(getSort());

        if (orderBy == null) {
            orderBy = new OrderBy();
        }

        List<T> page = getRepository().findPage(filter, orderBy, (int) count, (int) first);

        return page.iterator();
    }

    @Override
    public long size() {
        Filter filter = getFilter();

        if (filter == null) {
            return getRepository().count();
        } else {
            return getRepository().count(filter);
        }
    }

    /**
     * Returns the object held by filterModel, if any.
     * 
     * @return the Filter
     */
    public Filter getFilter() {
        IModel<Filter> model = getFilterModel();

        return (model != null) ? model.getObject() : null;
    }

    public IModel<Filter> getFilterModel() {
        return filterModel;
    }

    public void setFilterModel(IModel<Filter> filterModel) {
        this.filterModel = filterModel;
    }
}
