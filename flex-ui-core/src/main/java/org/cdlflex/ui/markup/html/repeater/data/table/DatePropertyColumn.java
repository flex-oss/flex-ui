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
package org.cdlflex.ui.markup.html.repeater.data.table;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.converter.DateConverter;

/**
 * A PropertyConvertingColumn that lets you explicitly state a date transformation using a DateFormat.
 * 
 * @param <T> The Model object type
 * @param <S> The type of the sort property
 */
public class DatePropertyColumn<T, S> extends PropertyConvertingColumn<T, S, Date> {

    private static final long serialVersionUID = 1L;

    /**
     * The date format used to convert the date
     */
    private DateFormat dateFormat;

    public DatePropertyColumn(IModel<String> displayModel, String propertyExpression) {
        super(displayModel, propertyExpression);
        setConverter(new DateConverter0());
    }

    public DatePropertyColumn(IModel<String> displayModel, S sortProperty, String propertyExpression) {
        super(displayModel, sortProperty, propertyExpression);
        setConverter(new DateConverter0());
    }

    public DatePropertyColumn(IModel<String> displayModel, String propertyExpression, DateFormat dateFormat) {
        super(displayModel, propertyExpression);
        setConverter(new DateConverter0());
        setDateFormat(dateFormat);
    }

    public DatePropertyColumn(IModel<String> displayModel, S sortProperty, String propertyExpression,
            DateFormat dateFormat) {
        super(displayModel, sortProperty, propertyExpression);
        setConverter(new DateConverter0());
        setDateFormat(dateFormat);
    }

    /**
     * Sets the DateFormat being used to display the date.
     * 
     * @param dateFormat a DateFormat
     */
    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Returns the DateFormat being used to display the date.
     * 
     * @return a DateFormat
     */
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * DateConverter that ignores the Locale and prefers the DateFormat set in this DatePropertyColumn instance.
     */
    private class DateConverter0 extends DateConverter {

        private static final long serialVersionUID = 1L;

        @Override
        public DateFormat getDateFormat(Locale locale) {
            return (dateFormat == null) ? super.getDateFormat(locale) : DatePropertyColumn.this.getDateFormat();
        }
    }
}
