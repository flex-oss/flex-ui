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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.value.IValueMap;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.html.Tag;

/**
 * An adapted CheckBoxMultipleChoice that renders like <a href="http://getbootstrap.com/javascript/#buttons">Bootstrap's
 * buttons</a>.
 * 
 * @param <T> Model object type
 */
public class ButtonMultipleChoice<T> extends CheckBoxMultipleChoice<T> {
    private static final long serialVersionUID = 1L;

    public ButtonMultipleChoice(String id) {
        super(id);
    }

    public ButtonMultipleChoice(String id, List<? extends T> choices) {
        super(id, choices);
    }

    public ButtonMultipleChoice(String id, List<? extends T> choices, IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public ButtonMultipleChoice(String id, IModel<? extends Collection<T>> model, List<? extends T> choices) {
        super(id, model, choices);
    }

    public ButtonMultipleChoice(String id, IModel<? extends Collection<T>> model, List<? extends T> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    public ButtonMultipleChoice(String id, IModel<? extends List<? extends T>> choices) {
        super(id, choices);
    }

    public ButtonMultipleChoice(String id, IModel<? extends Collection<T>> model,
            IModel<? extends List<? extends T>> choices) {
        super(id, model, choices);
    }

    public ButtonMultipleChoice(String id, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public ButtonMultipleChoice(String id, IModel<? extends Collection<T>> model,
            IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new CssClassNameAppender("btn-group"));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.put("data-toggle", "buttons");
    }

    // CHECKSTYLE:OFF this method was copied and marginally adapted from Wicket's CheckBoxMultipleChoice
    @SuppressWarnings("unchecked")
    @Override
    protected void appendOptionHtml(final AppendingStringBuffer buffer, final T choice, int index,
        final String selected) {
        Object displayValue = getChoiceRenderer().getDisplayValue(choice);
        Class<?> objectClass = displayValue == null ? null : displayValue.getClass();

        // Get label for choice
        String label = "";
        if (objectClass != null && objectClass != String.class) {
            @SuppressWarnings("rawtypes")
            IConverter converter = getConverter(objectClass);
            label = converter.convertToString(displayValue, getLocale());
        } else if (displayValue != null) {
            label = displayValue.toString();
        }

        // If there is a display value for the choice, then we know that the
        // choice is automatic in some way. If label is /null/ then we know
        // that the choice is a manually created checkbox tag at some random
        // location in the page markup!
        if (label != null) {
            String id = getChoiceRenderer().getIdValue(choice, index);
            final String idAttr = getCheckBoxMarkupId(id);
            boolean isSlected = isSelected(choice, index, selected);

            // Append option suffix
            buffer.append(getPrefix(index, choice));

            Tag tag = new Tag("label").attr("for", idAttr);

            List<String> labelClasses = new ArrayList<>();
            labelClasses.add("btn");
            if (isSlected) {
                labelClasses.add("active");
                labelClasses.add(getActiveLabelClass(choice, index));
            } else {
                labelClasses.add(getLabelClass(choice, index));
            }
            tag.attr("class", labelClasses);

            // Add checkbox element
            Tag input = new Tag("input").attr("name", getInputName()).attr("type", "checkbox");
            input.attr("value", id).attr("id", idAttr);
            input.attr("checked", "checked", isSlected);
            input.attr("disabled", "disabled", isDisabled(choice, index, selected) || !isEnabledInHierarchy());

            // Allows user to add attributes to the <input..> tag
            {
                IValueMap attrs = getAdditionalAttributes(index, choice);
                if (attrs != null) {
                    input.getAttributes().putAll(attrs);
                }
            }

            if (getApplication().getDebugSettings().isOutputComponentPath()) {
                CharSequence path = getPageRelativePath();
                path = Strings.replaceAll(path, "_", "__");
                path = Strings.replaceAll(path, ":", "_");
                input.attr("wicketpath", path + "_input_" + index);
            }

            tag.add(input);

            // Add label for checkbox
            tag.add(getLabelText(label));

            buffer.append(tag.toString());
            // Append option suffix
            buffer.append(getSuffix(index, choice));
        }
        // CHECKSTYLE:ON
    }

    private CharSequence getLabelText(String label) {
        String display = label;

        if (localizeDisplayValues()) {
            display = getLocalizer().getString(label, this, label);
        }
        return getEscapeModelStrings() ? Strings.escapeMarkup(display) : display;
    }

    /**
     * Returns the css class added to the label when the checkbox is not selected.
     * 
     * @param choice the choice being rendered
     * @param index the index of the label in the list
     * @return a css class
     */
    protected String getLabelClass(T choice, int index) {
        return "btn-default";
    }

    /**
     * Returns the css class added to the label when the checkbox is selected.
     * 
     * @param choice the choice being rendered
     * @param index the index of the label in the list
     * @return a css class
     */
    protected String getActiveLabelClass(T choice, int index) {
        return getLabelClass(choice, index);
    }

    @Override
    protected String getSuffix(int index, T choice) {
        return "";
    }

}
