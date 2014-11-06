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

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.WildcardListModel;
import org.cdlflex.ui.util.convert.EnumRenderer;

/**
 * A DropDownChoice that gets its choices from an enum type.
 *
 * @param <T> The specific enum type.
 */
public class EnumDropDownChoice<T extends Enum<T>> extends DropDownChoice<T> {

    private static final long serialVersionUID = 1L;

    public EnumDropDownChoice(String id, Class<T> enumType) {
        this(id, null, enumType);
    }

    public EnumDropDownChoice(String id, IModel<T> model, Class<T> enumType) {
        this(id, model, enumType, new DefaultEnumChoiceRenderer<T>());
    }

    public EnumDropDownChoice(String id, Class<T> enumType, IChoiceRenderer<T> choiceRenderer) {
        this(id, null, enumType, choiceRenderer);
    }

    public EnumDropDownChoice(String id, IModel<T> model, Class<T> enumType, IChoiceRenderer<T> choiceRenderer) {
        super(id, model, modelOf(enumType), choiceRenderer);
    }

    /**
     * Creates a new WildcardListModel for all values of the given enum type.
     *
     * @param enumType the enum type class
     * @param <E> the enum type
     * @return a new WildcardListModel
     */
    public static <E extends Enum> WildcardListModel<E> modelOf(Class<E> enumType) {
        return new WildcardListModel<>(getEnumValues(enumType));
    }

    /**
     * Returns a list of all enum constants of the given enum type.
     * 
     * @param enumType the enum type class
     * @param <E> the enum type
     * @return a list of enum values
     */
    public static <E extends Enum> List<E> getEnumValues(Class<E> enumType) {
        return Arrays.asList(enumType.getEnumConstants());
    }

    /**
     * A IChoiceRenderer that renders enum values by capitalizing their name and replacing all underscores with
     * whitespaces.
     * 
     * @param <E> the enum type
     */
    public static class DefaultEnumChoiceRenderer<E extends Enum<E>> extends EnumRenderer<E> implements
            IChoiceRenderer<E> {
        private static final long serialVersionUID = 1L;

        @Override
        public Object getDisplayValue(E object) {
            return convertToString(object);
        }

        @Override
        public String getIdValue(E object, int index) {
            return object.name();
        }
    }

}
