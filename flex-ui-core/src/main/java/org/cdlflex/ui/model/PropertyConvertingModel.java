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
