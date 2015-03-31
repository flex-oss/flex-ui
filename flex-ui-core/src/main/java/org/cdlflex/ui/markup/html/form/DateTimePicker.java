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

import java.util.Date;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.FrontendDependencyBehavior;
import org.cdlflex.ui.resource.BootstrapDatePickerCssResourceReference;
import org.cdlflex.ui.resource.BootstrapDatePickerJsResourceReference;
import org.cdlflex.ui.util.convert.date.MomentJsDateFormatConverter;
import org.rauschig.wicketjs.behavior.WidgetBehavior;
import org.rauschig.wicketjs.util.options.PropertyBindingOptions;

/**
 * A {@code DateTextField} extension that uses <a
 * href="http://eonasdan.github.io/bootstrap-datetimepicker">bootstrap-datepicker</a> as frontend and can handle a
 * specified date pattern that conforms to SimpleDatePattern.
 *
 * The components properties map to the ones of bootstrap-datepicker.
 */
public class DateTimePicker extends DateTextField {

    private static final long serialVersionUID = 1L;

    private boolean pickDate = true;
    private boolean pickTime = true;
    private boolean sideBySide;
    private String language = "en";

    public DateTimePicker(String id) {
        super(id);
    }

    public DateTimePicker(String id, IModel<Date> model) {
        super(id, model);
    }

    public DateTimePicker(String id, String datePattern) {
        super(id, datePattern);
    }

    public DateTimePicker(String id, IModel<Date> model, String datePattern) {
        super(id, model, datePattern);
    }

    public boolean isPickDate() {
        return pickDate;
    }

    /**
     * Enables/Disables the date picker.
     * 
     * @param pickDateFlag true if date picker should be enabled
     * @return this for chaining
     */
    public DateTimePicker setPickDate(boolean pickDateFlag) {
        this.pickDate = pickDateFlag;
        return this;
    }

    public boolean isPickTime() {
        return pickTime;
    }

    /**
     * Enables/Disables the time picker.
     * 
     * @param pickTimeFlag true if time picker should be enabled
     * @return this for chaining
     */
    public DateTimePicker setPickTime(boolean pickTimeFlag) {
        this.pickTime = pickTimeFlag;
        return this;
    }

    public boolean isSideBySide() {
        return sideBySide;
    }

    /**
     * Show the date and time picker side by side.
     * 
     * @param sideBySideFlag flag
     * @return this for chaining
     */
    public DateTimePicker setSideBySide(boolean sideBySideFlag) {
        this.sideBySide = sideBySideFlag;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    /**
     * Sets language locale.
     * 
     * @param languageLocale the language string, e.g. 'en'
     * @return this for chaining
     */
    public DateTimePicker setLanguage(String languageLocale) {
        this.language = languageLocale;
        return this;
    }

    /**
     * Returns the result of {@link #getTextFormat()} (which is a java DateFormat pattern) as a text format that is
     * understood by moment.js.
     * 
     * @return a moment.js date format string
     */
    public String getMomentJsDateFormat() {
        if (getTextFormat() == null) {
            return null;
        } else {
            return new MomentJsDateFormatConverter().convert(getTextFormat());
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        getPage().add(
                new FrontendDependencyBehavior(BootstrapDatePickerJsResourceReference.get(),
                        BootstrapDatePickerCssResourceReference.get()));

        add(newWidgetBehavior());
    }

    /**
     * Creates the WidgetBehavior that provides the frontend behavior for this component.
     * 
     * @return a new WidgetBehavior instance
     */
    protected WidgetBehavior newWidgetBehavior() {
        PropertyBindingOptions options =
            new PropertyBindingOptions(this, "pickDate", "pickTime", "language", "sideBySide");
        options.add("format", "momentJsDateFormat");

        return new WidgetBehavior("datetimepicker", options);
    }

}
