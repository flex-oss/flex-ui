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
package org.cdlflex.ui.util;

import java.util.Optional;

import org.apache.wicket.model.IModel;

/**
 * Utility functions for IModels.
 */
public final class Models {
    private Models() {

    }

    /**
     * Returns an Optional describing the object value of the given model. If the model or the object in the model is
     * null, an empty optional is returned.
     * 
     * @param model the possibly-null value to describe
     * @param <T> the model object type
     * @return an Optional
     */
    public static <T> Optional<T> optional(IModel<T> model) {
        return Optional.ofNullable(model).map(IModel::getObject);
    }

    /**
     * Nullsafe way of returning model's object.
     *
     * @param model the possibly-null model to extract the object from
     * @param <T> the model object type
     * @return the model's object, or null
     */
    public static <T> T getObject(IModel<T> model) {
        return (model == null) ? null : model.getObject();
    }

    /**
     * Nullsafe way of setting a model's object.
     *
     * @param model the possibly-null model to set the object in
     * @param object the object to set
     * @param <T> the model object type
     */
    public static <T> void setObject(IModel<T> model, T object) {
        if (model != null) {
            model.setObject(object);
        }
    }

    /**
     * Checks whether the given Model, or the Object it wraps, is null, and returns true if either is null.
     *
     * @param model The model to check
     * @return true if either the given model, or the object it wraps is null
     */
    public static boolean isNull(IModel<?> model) {
        return model == null || model.getObject() == null;
    }

    /**
     * Checks whether the given Model is null, or the String it wraps is null or empty.
     *
     * @param model The model to check
     * @return True if the model is null, the object it wraps is null or the wrapped string is empty
     */
    public static boolean isEmpty(IModel<String> model) {
        return isNull(model) || model.getObject().isEmpty();
    }
}
