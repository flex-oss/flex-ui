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
package org.cdlflex.ui.markup.html;

import org.apache.wicket.IGenericComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * A {@code WebMarkupContainer} with type-safe getters and setters for the model and its underlying object.
 *
 * @param <T> the type of the container's model object
 */
public class GenericMarkupContainer<T> extends WebMarkupContainer implements IGenericComponent<T> {

    private static final long serialVersionUID = 1L;

    public GenericMarkupContainer(String id) {
        super(id);
    }

    public GenericMarkupContainer(String id, IModel<T> model) {
        super(id, model);
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
