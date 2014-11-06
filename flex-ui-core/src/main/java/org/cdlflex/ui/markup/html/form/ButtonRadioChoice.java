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

import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.IOnChangeListener;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.html.Tag;

/**
 * Bootstrap button rendering of a RadioChoice.
 * 
 * @param <T> The model object type
 */
public class ButtonRadioChoice<T> extends RadioChoice<T> {
    private static final long serialVersionUID = 1L;

    private IModel<Buttons.Type> buttonTypeModel = new Model<>(Buttons.Type.DEFAULT);
    private IModel<Buttons.Size> buttonSizeModel = new Model<>(Buttons.Size.MEDIUM);

    public ButtonRadioChoice(String id) {
        super(id);
    }

    public ButtonRadioChoice(String id, List<? extends T> choices) {
        super(id, choices);
    }

    public ButtonRadioChoice(String id, List<? extends T> choices, IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public ButtonRadioChoice(String id, IModel<T> model, List<? extends T> choices) {
        super(id, model, choices);
    }

    public ButtonRadioChoice(String id, IModel<T> model, List<? extends T> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    public ButtonRadioChoice(String id, IModel<? extends List<? extends T>> choices) {
        super(id, choices);
    }

    public ButtonRadioChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices) {
        super(id, model, choices);
    }

    public ButtonRadioChoice(String id, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public ButtonRadioChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    public IModel<Buttons.Type> getButtonTypeModel() {
        return buttonTypeModel;
    }

    public void setButtonTypeModel(IModel<Buttons.Type> buttonTypeModel) {
        this.buttonTypeModel = buttonTypeModel;
    }

    public IModel<Buttons.Size> getButtonSizeModel() {
        return buttonSizeModel;
    }

    public void setButtonSizeModel(IModel<Buttons.Size> buttonSizeModel) {
        this.buttonSizeModel = buttonSizeModel;
    }

    /**
     * Returns the button type used.
     *
     * @return the button type
     */
    public Buttons.Type getButtonType() {
        return getButtonTypeModel().getObject();
    }

    /**
     * Sets the button type used.
     *
     * @param buttonType the button type
     * @return this for chaining
     */
    public ButtonRadioChoice setButtonType(Buttons.Type buttonType) {
        getButtonTypeModel().setObject(buttonType);
        return this;
    }

    /**
     * Returns the button size used.
     *
     * @return the button size
     */
    public Buttons.Size getButtonSize() {
        return getButtonSizeModel().getObject();
    }

    /**
     * Sets the button type used.
     *
     * @param buttonSize the button size
     * @return this for chaining
     */
    public ButtonRadioChoice setButtonSize(Buttons.Size buttonSize) {
        getButtonSizeModel().setObject(buttonSize);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setSuffix("");
        add(new CssClassNameAppender("btn-group"));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.put("data-toggle", "buttons");
    }

    // CHECKSTYLE:OFF this method was copied and adapted from Wicket's RadioChoice
    @Override
    protected void appendOptionHtml(AppendingStringBuffer buffer, T choice, int index, String selected) {
        // CHECKSTYLE:ON
        String label = generateLabelForChoice(choice);

        // If there is a display value for the choice, then we know that the choice is automatic in some way. If label
        // is /null/ then we know that the choice is a manually created checkbox tag at some random location in the page
        // markup!
        if (label == null) {
            return;
        }

        String id = getChoiceRenderer().getIdValue(choice, index);
        String idAttr = getMarkupId() + "-" + id;

        boolean isSelected = isSelected(choice, index, selected);
        boolean enabled = isEnabledInHierarchy() && !isDisabled(choice, index, selected);

        Tag inputTag =
            new Tag("input").attr("name", getInputName()).attr("type", "radio")
                    .attr("checked", "checked", isSelected).attr("disabled", "disabled", !enabled).attr("value", id)
                    .attr("id", idAttr);

        // Should a roundtrip be made (have onSelectionChanged called) when the option is clicked?
        if (wantOnSelectionChangedNotifications()) {
            inputTag.attr("onclick", buildOnClickUrl(id));
        }

        if (getApplication().getDebugSettings().isOutputComponentPath()) {
            inputTag.attr("wicketpath", buildWicketPath(index));
        }

        // Add label for radio button
        Tag labelTag =
            new Tag("label").attr("for", idAttr).attr("class", generateLabelTagClass(choice, index, isSelected));

        inputTag.attrs(getAdditionalAttributes(index, choice));
        inputTag.attrs(getAdditionalAttributesForLabel(index, choice));

        labelTag.add(inputTag);
        labelTag.add(generateLabelBody(label).toString());

        buffer.append(getPrefix(index, choice));
        buffer.append(labelTag);
        buffer.append(getSuffix(index, choice));
    }

    /**
     * Builds the round-trip url added as "onclick" attribute to the radio choice button for the given id.
     * 
     * @param id the id value of the choice renderer
     * @return a url usable in an "onclick" attribute
     */
    protected String buildOnClickUrl(String id) {
        CharSequence url = urlFor(IOnChangeListener.INTERFACE, new PageParameters());
        Form<?> form = findParent(Form.class);

        String onclick;
        if (form != null) {
            onclick = form.getJsForInterfaceUrl(url) + ";";
        } else {
            onclick = "window.location.href='" + url;
            onclick += (url.toString().indexOf('?') > -1 ? '&' : '?') + getInputName();
            onclick += "=" + id + ";";
        }

        return onclick;
    }

    /**
     * Generates the initial label that is displayed inside the button for the given choice.
     *
     * @param choice the choice being rendered
     * @return the choice label
     */
    @SuppressWarnings("unchecked")
    protected String generateLabelForChoice(T choice) {
        Object displayValue = getChoiceRenderer().getDisplayValue(choice);
        Class<?> objectClass = displayValue == null ? null : displayValue.getClass();

        String label = "";

        if (objectClass != null && objectClass != String.class) {
            IConverter converter = getConverter(objectClass);
            label = converter.convertToString(displayValue, getLocale());
        } else if (displayValue != null) {
            label = displayValue.toString();
        }

        return label;
    }

    /**
     * Generates the css class used for a specific label tag.
     * 
     * @param choice the choice being rendered
     * @param index the index of the label in the list
     * @param isSelected whether the choice is selected or not
     * @return css classes
     */
    protected String generateLabelTagClass(T choice, int index, boolean isSelected) {
        String css = "btn " + getLabelClass(choice, index);

        if (isSelected) {
            css += " active";
        }

        return css;
    }

    /**
     * Creates the message displayed in the label's body for a choice by localizing and escaping the choice label.
     * 
     * @param label the label
     * @return the displayed label
     */
    protected CharSequence generateLabelBody(String label) {
        String display = label;

        if (localizeDisplayValues()) {
            display = getLocalizer().getString(label, this, label);
        }

        return getEscapeModelStrings() ? Strings.escapeMarkup(display) : display;
    }

    /**
     * Builds the wicketpath attribute value for the given choice index.
     *
     * @param index the index of the choice being rendered
     * @return wicketpath attribute value
     */
    protected CharSequence buildWicketPath(int index) {
        CharSequence path = getPageRelativePath();
        path = Strings.replaceAll(path, "_", "__");
        path = Strings.replaceAll(path, ":", "_");

        return path + "_input_" + index;
    }

    /**
     * Returns the css class added to the label when the checkbox is not selected.
     *
     * @param choice the choice being rendered
     * @param index the index of the label in the list
     * @return a css class
     */
    protected String getLabelClass(T choice, int index) {
        return getButtonType().getCssClassName() + " " + getButtonSize().getCssClassName();
    }

}
