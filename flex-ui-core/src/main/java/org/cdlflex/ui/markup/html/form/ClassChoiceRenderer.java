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
package org.cdlflex.ui.markup.html.form;

import java.util.MissingResourceException;

import org.apache.wicket.Component;
import org.apache.wicket.Localizer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * An IChoiceRenderer that renders a list of Class choices. If a component is set, it will use that component to attempt
 * to localize the class labels, where the key is the canonical name of the class being rendered. By default it will use
 * the class' simple name.
 */
public final class ClassChoiceRenderer implements IChoiceRenderer<Class<?>> {

    private static final long serialVersionUID = 1L;

    private Component localizationProvider;

    public ClassChoiceRenderer() {
        this(null);
    }

    public ClassChoiceRenderer(Component localizationProvider) {
        this.localizationProvider = localizationProvider;
    }

    @Override
    public Object getDisplayValue(Class<?> object) {
        String defaultValue = object.getSimpleName();

        if (localizationProvider == null) {
            return defaultValue;
        }

        try {
            return Localizer.get().getString(object.getCanonicalName(), localizationProvider, defaultValue);
        } catch (MissingResourceException e) {
            return defaultValue;
        }
    }

    @Override
    public String getIdValue(Class<?> object, int index) {
        return object.getCanonicalName();
    }
}
