package org.cdlflex.ui.util.convert;

import java.util.Locale;

import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;

/**
 * Simplified version of an IConverter that converts objects to string (i.e. a functional interface for externalising a
 * "toString" method.
 * 
 * @param <T> The type to be converted
 */
public abstract class ToStringConverter<T> implements IConverter<T> {

    @Override
    public T convertToObject(String value, Locale locale) throws ConversionException {
        throw new UnsupportedOperationException("ToStringConverter can only convert one way");
    }

    @Override
    public String convertToString(T value, Locale locale) {
        return convertToString(value);
    }

    /**
     * Convert the given value to a string.
     * 
     * @param value the value to convert
     * @return a string representation of the value
     */
    public abstract String convertToString(T value);
}
