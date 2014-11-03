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

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

/**
 * Like a PropertyColumn, but uses an additional IConverter to convert values to string representations for the display
 * label.
 *
 * @param <T> The Model object type
 * @param <S> The type of the sort property
 * @param <C> The type being converted
 */
public class PropertyConvertingColumn<T, S, C> extends PropertyColumn<T, S> {

    private static final long serialVersionUID = 1L;

    private IConverter<C> converter;

    public PropertyConvertingColumn(IModel<String> displayModel, String propertyExpression) {
        super(displayModel, propertyExpression);
    }

    public PropertyConvertingColumn(IModel<String> displayModel, S sortProperty, String propertyExpression) {
        super(displayModel, sortProperty, propertyExpression);
    }

    public PropertyConvertingColumn(IModel<String> displayModel, String propertyExpression, IConverter<C> converter) {
        super(displayModel, propertyExpression);
        this.converter = converter;
    }

    public PropertyConvertingColumn(IModel<String> displayModel, S sortProperty, String propertyExpression,
            IConverter<C> converter) {
        super(displayModel, sortProperty, propertyExpression);
        this.converter = converter;
    }

    /**
     * Sets the IConverter used to convert the data model to a string representation.
     *
     * @param converter a Converter
     */
    public void setConverter(IConverter<C> converter) {
        this.converter = converter;
    }

    /**
     * Returns the IConverter used to convert the data model to a string representation.
     *
     * @return a Converter
     */
    public IConverter<C> getConverter() {
        return converter;
    }

    @Override
    public void populateItem(Item<ICellPopulator<T>> item, String componentId, IModel<T> rowModel) {
        item.add(createLabel(componentId, rowModel));
    }

    /**
     * Creates the Label that populates the tables cell item.
     *
     * @param componentId the id of the label
     * @param rowModel the model of the row item being rendered
     * @return a new label instance
     */
    protected Label createLabel(String componentId, IModel<T> rowModel) {
        return new Label(componentId, getDataModel(rowModel)) {

            private static final long serialVersionUID = 1L;

            @Override
            @SuppressWarnings("unchecked")
            public <D> IConverter<D> getConverter(Class<D> type) {
                return (IConverter<D>) PropertyConvertingColumn.this.getConverter();
            }
        };
    }

}
