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

import java.util.Objects;

import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.util.function.SerializableFunction;

/**
 * An AbstractReadOnlyModel that transforms another model's object using a function.
 * 
 * @param <T> the source model object type
 * @param <R> the target model object type
 */
public class TransformingModel<T, R> extends AbstractReadOnlyModel<R> {

    private static final long serialVersionUID = 1L;

    private IModel<T> model;
    private SerializableFunction<T, R> transformer;

    public TransformingModel(IModel<T> model, SerializableFunction<T, R> transformer) {
        this.model = Objects.requireNonNull(model);
        this.transformer = Objects.requireNonNull(transformer);
    }

    @Override
    public R getObject() {
        return transformer.apply(model.getObject());
    }

    /**
     * Returns the transformer function.
     * 
     * @return a function
     */
    public SerializableFunction<T, R> getTransformer() {
        return transformer;
    }

    /**
     * Returns the model being converted.
     * 
     * @return a model
     */
    public IModel<T> getSourceModel() {
        return model;
    }
}
