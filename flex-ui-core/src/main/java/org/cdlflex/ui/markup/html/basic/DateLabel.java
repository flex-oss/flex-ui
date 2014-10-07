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
package org.cdlflex.ui.markup.html.basic;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.converter.DateConverter;

/**
 * A Label that displays a Date using a custom DateFormat to render it.
 */
public class DateLabel extends ConvertingLabel<Date> {

    private static final long serialVersionUID = 1L;

    private DateFormat dateFormat;

    private DateConverter converter = new DateConverter() {
        private static final long serialVersionUID = 1L;

        @Override
        public DateFormat getDateFormat(Locale locale) {
            DateFormat format = DateLabel.this.getDateFormat();
            return (format == null) ? super.getDateFormat(locale) : format;
        }
    };

    public DateLabel(String id) {
        super(id);
    }

    public DateLabel(String id, IModel<Date> model) {
        this(id, null, model);
    }

    public DateLabel(String id, DateFormat format, IModel<Date> model) {
        super(id, model);
        setDateFormat(format);
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public DateConverter getConverter() {
        return converter;
    }

}
