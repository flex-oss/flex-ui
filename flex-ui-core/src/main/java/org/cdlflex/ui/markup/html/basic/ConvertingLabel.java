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
package org.cdlflex.ui.markup.html.basic;

import org.apache.wicket.IGenericComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

/**
 * A Label with a generic type that uses a specified converter to convert this type into strings. This can be useful in
 * combination with ToStringConverter.
 *
 * @param <T> the type of the model object
 */
public class ConvertingLabel<T> extends Label implements IGenericComponent<T> {

    private static final long serialVersionUID = 1L;

    private IConverter<T> converter;

    public ConvertingLabel(String id) {
        super(id);
    }

    public ConvertingLabel(String id, IModel<T> model) {
        super(id, model);
    }

    public ConvertingLabel(String id, IConverter<T> converter) {
        this(id, null, converter);
    }

    public ConvertingLabel(String id, IConverter<T> converter, IModel<T> model) {
        this(id, model, converter);
    }

    public ConvertingLabel(String id, IModel<T> model, IConverter<T> converter) {
        super(id, model);
        setConverter(converter);
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

    @SuppressWarnings("unchecked")
    @Override
    public <C> IConverter<C> getConverter(Class<C> type) {
        return (IConverter) getConverter();
    }

    public IConverter<T> getConverter() {
        return converter;
    }

    public void setConverter(IConverter<T> converter) {
        this.converter = converter;
    }
}
