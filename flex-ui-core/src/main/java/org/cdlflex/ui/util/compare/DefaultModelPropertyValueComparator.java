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

/**
 * A ModelPropertyValueComparator that checks whether property values being compared use the Comparable interface, and
 * if not compares them using their string values.
 * 
 * @param <M> The Model object type
 */
public class DefaultModelPropertyValueComparator<M> extends ModelPropertyValueComparator<M, Object> {

    /**
     * Creates a new ModelPropertyValueComparator.
     * 
     * @param propertyExpression the expression that extracts the property from the model object
     */
    public DefaultModelPropertyValueComparator(String propertyExpression) {
        super(propertyExpression, new Comparator<Object>() {

            @SuppressWarnings("unchecked")
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Comparable) {
                    return ((Comparable) o1).compareTo(o2);
                } else {
                    return String.valueOf(o1).compareTo(String.valueOf(o2));
                }
            }
        });
    }
}
