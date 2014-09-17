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
package org.cdlflex.ui.util.compare;

import java.util.Comparator;

import org.apache.wicket.core.util.lang.PropertyResolver;

/**
 * A Comparator that compares models based on a property. The comparison of the property values is delegated to a
 * comparator that handles the specific property type.
 * 
 * @param <M> The type of the model object
 * @param <T> The type of the model objects property
 */
public abstract class ModelPropertyValueComparator<M, T> implements Comparator<M> {

    private String propertyExpression;

    private Comparator<T> valueComparator;

    /**
     * Creates a new ModelPropertyValueComparator.
     * 
     * @param propertyExpression the expression that extracts the property from the model object
     * @param valueComparator the comparator that compares the extracted values
     */
    public ModelPropertyValueComparator(String propertyExpression, Comparator<T> valueComparator) {
        this.propertyExpression = propertyExpression;
        this.valueComparator = valueComparator;
    }

    public String getPropertyExpression() {
        return propertyExpression;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compare(M o1, M o2) {
        T v1 = getValue(o1);
        T v2 = getValue(o2);

        return valueComparator.compare(v1, v2);
    }

    /**
     * Extracts the value of the property defined in {@link #getPropertyExpression} from the given model object.
     * 
     * @param model the model object to extract the value from
     * @return the property value
     */
    @SuppressWarnings("unchecked")
    protected T getValue(M model) {
        return (T) PropertyResolver.getValue(getPropertyExpression(), model);
    }
}
