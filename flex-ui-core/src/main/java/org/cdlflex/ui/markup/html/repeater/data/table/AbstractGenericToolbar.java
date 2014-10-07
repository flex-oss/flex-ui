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

import org.apache.wicket.IGenericComponent;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.model.IModel;

/**
 * Model type-safe version of an AbstractToolbar.
 * 
 * @param <T> The model object type
 */
public abstract class AbstractGenericToolbar<T> extends AbstractToolbar implements IGenericComponent<T> {

    private static final long serialVersionUID = 1L;

    public AbstractGenericToolbar(DataTable<?, ?> table) {
        super(table);
    }

    public AbstractGenericToolbar(IModel<T> model, DataTable<?, ?> table) {
        super(model, table);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IModel<T> getModel() {
        return (IModel<T>) getDefaultModel();
    }

    @Override
    public void setModel(IModel<T> model) {
        setDefaultModel(model);
    }

    @Override
    public void setModelObject(T object) {
        setDefaultModelObject(object);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getModelObject() {
        return (T) getDefaultModelObject();
    }
}
