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
