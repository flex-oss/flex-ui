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
package org.cdlflex.ui.ajax.markup.html.form;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;

/**
 * An AjaxButtonRadioChoice that renders a specific enum type.
 *
 * @param <T> The Enum Type
 */
public abstract class AjaxEnumRadioChoice<T extends Enum<T>> extends AjaxButtonRadioChoice<T> {
    private static final long serialVersionUID = 1L;

    public AjaxEnumRadioChoice(String id, Class<T> enumType) {
        this(id, enumType, new EnumChoiceRenderer<T>());
    }

    public AjaxEnumRadioChoice(String id, Class<T> enumType, Component resourceProvider) {
        this(id, enumType, new EnumChoiceRenderer<T>(resourceProvider));
    }

    public AjaxEnumRadioChoice(String id, Class<T> enumType, IChoiceRenderer<T> renderer) {
        super(id, enumValues(enumType), renderer);
    }

    private static <E extends Enum<E>> List<E> enumValues(Class<E> type) {
        return Arrays.asList(type.getEnumConstants());
    }
}
