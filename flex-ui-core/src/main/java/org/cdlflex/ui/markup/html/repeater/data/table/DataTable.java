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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitFilter;
import org.apache.wicket.util.visit.IVisitor;
import org.apache.wicket.util.visit.Visits;

/**
 * Custom DataTable implementation that extends functionality for ITableAware columns and provides manipulation of rows
 * via IRowCallback. It also allows construction from an ITableSkeleton.
 * 
 * @param <T> The model object type
 * @param <S> the type of the sorting parameter
 */
public class DataTable<T, S> extends org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable<T, S> {

    private static final long serialVersionUID = 1L;

    private List<IRowCallback<T>> rowCallbacks;

    /**
     * Constructor.
     * 
     * @param id component id
     * @param skeleton a table skeleton
     */
    public DataTable(String id, ITableSkeleton<T, S> skeleton) {
        this(id, skeleton.getColumns(), skeleton.getDataProvider(), skeleton.getRowsPerPage());
    }

    /**
     * Constructor.
     * 
     * @param id component id
     * @param columns list of columns
     * @param dataProvider data provider
     * @param rows number of rows per page
     */
    public DataTable(String id, List<? extends IColumn<T, S>> columns, IDataProvider<T> dataProvider, long rows) {
        super(id, columns, dataProvider, rows);
        rowCallbacks = new ArrayList<>();

        visitColumns(new IVisitor<IColumn<T, S>, Void>() {
            @Override
            public void component(IColumn<T, S> object, IVisit<Void> visit) {
                setTable(object);
            }
        });
    }

    /**
     * Returns the callbacks that are executed on each created row.
     * 
     * @return the row callbacks
     */
    public List<IRowCallback<T>> getRowCallbacks() {
        return rowCallbacks;
    }

    /**
     * Adds the given column to the table.
     * 
     * @param column a column to add
     * @return this for chaining
     */
    @SuppressWarnings("unchecked")
    public DataTable<T, S> addColumn(IColumn<T, S> column) {
        List<IColumn<T, S>> columns = (List<IColumn<T, S>>) getColumns();
        columns.add(column);
        setTable(column);
        return this;
    }

    /**
     * Visit the columns of this table.
     * 
     * @param visitor the visitor
     * @param <R> the return type of the visitor
     * @return visitor return value
     */
    @SuppressWarnings("unchecked")
    public <R> R visitColumns(IVisitor<IColumn<T, S>, R> visitor) {
        return Visits.visitChildren((List<IColumn<T, S>>) getColumns(), visitor);
    }

    /**
     * Visit the columns of this table.
     * 
     * @param visitor the visitor
     * @param filter a filter
     * @param <R> the return type of the visitor
     * @return visitor return value
     */
    @SuppressWarnings("unchecked")
    public <R> R visitColumns(IVisitor<IColumn<T, S>, R> visitor, IVisitFilter filter) {
        return Visits.visitChildren((List<IColumn<T, S>>) getColumns(), visitor, filter);
    }

    @Override
    protected Item<T> newRowItem(String id, int index, IModel<T> model) {
        Item<T> item = super.newRowItem(id, index, model);

        onAfterCreateRowItem(item);

        return item;
    }

    @Override
    protected Item<IColumn<T, S>> newCellItem(String id, int index, IModel<IColumn<T, S>> model) {
        return new DataTableCell<>(id, index, model, this);
    }

    /**
     * Executes the {@link #rowCallbacks} on the created row item. Called by
     * {@link #newRowItem(String, int, org.apache.wicket.model.IModel)} after the item was created by the superclass.
     * 
     * @param item the item that was created.
     */
    private void onAfterCreateRowItem(Item<T> item) {
        for (IRowCallback<T> callback : getRowCallbacks()) {
            if (callback != null) {
                callback.call(item);
            }
        }
    }

    /**
     * Will call {@link #setTable(ITableAware)} if the given column is of ITableAware type.
     * 
     * @param column a column
     */
    protected void setTable(IColumn<T, S> column) {
        if (column instanceof ITableAware) {
            setTable((ITableAware<T, S>) column);
        }
    }

    /**
     * Sets this table to the given ITableAware.
     * 
     * @param tableAware the table aware column to set this table for
     */
    protected void setTable(ITableAware<T, S> tableAware) {
        tableAware.setTable(this);
    }

}
