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

import org.apache.wicket.model.IModel;

/**
 * Utility functions for IModels.
 */
public final class Models {
    private Models() {

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
