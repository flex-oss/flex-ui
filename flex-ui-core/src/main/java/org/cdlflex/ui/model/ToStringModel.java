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

import org.apache.wicket.model.IModel;
import org.cdlflex.ui.util.function.SerializableFunction;

/**
 * Specific implementation of a TransformingModel that returns a String as read only object. It also overwrites the
 * {@link #toString} function of the Model itself.
 * 
 * @param <T> the source model object type to convert
 */
public class ToStringModel<T> extends TransformingModel<T, String> {

    private static final long serialVersionUID = 1L;

    /**
     * This constructor uses the Objects::toString method by default.
     * 
     * @param model the model object
     */
    public ToStringModel(IModel<T> model) {
        this(model, Objects::toString);
    }

    public ToStringModel(IModel<T> model, SerializableFunction<T, String> transformer) {
        super(model, transformer);
    }

    @Override
    public String toString() {
        return getObject();
    }
}
