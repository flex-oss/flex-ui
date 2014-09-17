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
