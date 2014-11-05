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
package org.cdlflex.ui.fruit.model;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;
import org.apache.wicket.model.Model;
import org.cdlflex.fruit.Filter;

/**
 * Readonly model that returns a {@code org.cdlflex.fruit.Filter} from a {@link IFilterProvider}.
 */
public class FilterModel extends AbstractReadOnlyModel<Filter> implements IFilterProvider, IWrapModel<Filter> {

    private static final long serialVersionUID = 1L;

    private IModel<? extends IFilterProvider> filterProviderModel;

    public FilterModel(IFilterProvider filterProvider) {
        this(Model.of(filterProvider));
    }

    public FilterModel(IModel<? extends IFilterProvider> filterProviderModel) {
        this.filterProviderModel = filterProviderModel;
    }

    @Override
    public IModel<? extends IFilterProvider> getWrappedModel() {
        return filterProviderModel;
    }

    @Override
    public Filter getObject() {
        return getFilter();
    }

    @Override
    public Filter getFilter() {
        IFilterProvider filterProvider = getWrappedModel().getObject();

        return (filterProvider == null) ? null : filterProvider.getFilter();
    }

    @Override
    public void detach() {
        super.detach();
        getWrappedModel().detach();
    }
}
