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
import org.cdlflex.ui.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Converts Enum types to strings and vice versa.
 * 
 * @param <T> The enum type
 */
public class EnumConverter<T extends Enum<T>> implements IConverter<T> {

    private static final Logger LOG = LoggerFactory.getLogger(EnumConverter.class);

    private static final long serialVersionUID = 1L;

    private Class<T> enumType;

    public EnumConverter() {
        this(null);
    }

    public EnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convertToObject(String value, Locale locale) throws ConversionException {
        if (value == null || Strings.isEmpty(value)) {
            return null;
        }
        if (enumType == null) {
            LOG.warn("Enum type is not set, can not convert value");
            return null;
        }

        return Enum.valueOf(enumType, value);
    }

    @Override
    public String convertToString(T value, Locale locale) {
        return value.name();
    }
}
