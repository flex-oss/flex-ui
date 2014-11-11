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
