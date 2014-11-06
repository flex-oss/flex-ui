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

import org.apache.wicket.Component;
import org.apache.wicket.Localizer;

/**
 * ToStringConverter that renders enum values. It can render an enum value by looking up the enum values name as a
 * resource property using the given localization provider, or if no localization provider is supplied, it will replace
 * underscores with whitespaces and capitalize the name. <code>FOO_BAR</code> would render as "Foo bar".
 *
 * @param <T> The specific enum type
 */
public class EnumRenderer<T extends Enum> extends ToStringConverter<T> {

    private static final long serialVersionUID = 1L;

    private Component localizationProvider;

    public EnumRenderer() {
        this(null);
    }

    public EnumRenderer(Component localizationProvider) {
        this.localizationProvider = localizationProvider;
    }

    @Override
    public String convertToString(T value) {
        if (localizationProvider != null) {
            return Localizer.get().getString(value.name(), localizationProvider, value.name());
        } else {
            return capitalize(value.name().replaceAll("_", " ").trim());
        }
    }

    private String capitalize(String string) {
        char[] buffer = string.toLowerCase().toCharArray();

        buffer[0] = Character.toTitleCase(buffer[0]);

        return new String(buffer);
    }

}
