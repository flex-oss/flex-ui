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
package org.cdlflex.ui.model;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;

/**
 * A read-only Model that converts the object of the given property model into something else (e.g. extract a value).
 * 
 * @param <T> The type of the property model
 * @param <C> The target type of the conversion
 */
public abstract class PropertyConvertingModel<T, C> extends AbstractReadOnlyModel<C> {

    private static final long serialVersionUID = 1L;

    /**
     * The PropertyModel being converted
     */
    private PropertyModel<T> propertyModel;

    /**
     * Construct with a wrapped (IModel) or unwrapped (non-IModel) object and a property expression that works on the
     * given model.
     * 
     * @param modelObject The model object, which may or may not implement IModel
     * @param expression the expression accessing the property
     */
    public PropertyConvertingModel(Object modelObject, String expression) {
        super();

        this.propertyModel = new PropertyModel<>(modelObject, expression);
    }

    @Override
    public C getObject() {
        return convert(propertyModel.getObject());
    }

    /**
     * Converts the extracted property object into something else.
     * 
     * @param object the object to be transformed
     * @return the transformed object
     */
    protected abstract C convert(T object);

    /**
     * Returns the underlying property model.
     * 
     * @return the property model
     */
    protected PropertyModel<T> getPropertyModel() {
        return propertyModel;
    }
}
