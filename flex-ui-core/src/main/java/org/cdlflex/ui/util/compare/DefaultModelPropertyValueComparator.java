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
