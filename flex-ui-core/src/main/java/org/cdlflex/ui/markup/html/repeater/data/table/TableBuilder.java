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
import java.util.Collections;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;

/**
 * Fluent interface for creating DataTable instances. The TableBuilder itself is a ITableSkeleton that can be used as an
 * argument of DataTable constructors.
 * 
 * @param <T> The Model object type
 * @param <S> The type of the sorting parameter
 */
public class TableBuilder<T, S> implements ITableSkeleton<T, S> {

    private static final long serialVersionUID = 1L;

    private List<IColumn<T, S>> columns;
    private ISortableDataProvider<T, S> dataProvider;
    private Integer rowsPerPage;

    private List<Behavior> behaviors;

    public TableBuilder() {
        this.columns = new ArrayList<>();
        this.behaviors = new ArrayList<>();
        this.rowsPerPage = Integer.MAX_VALUE;
    }

    /**
     * Adds behaviors that will be added to the table after construction.
     * 
     * @param behavior the behaviors to add
     * @return this for chaining
     */
    public TableBuilder<T, S> add(Behavior... behavior) {
        Collections.addAll(behaviors, behavior);
        return this;
    }

    /**
     * Adds a behavior that will be added to the table after construction.
     * 
     * @param behavior the behavior to add
     * @return this for chaining
     */
    public TableBuilder<T, S> add(Behavior behavior) {
        behaviors.add(behavior);
        return this;
    }

    /**
     * Adds a column. Alias for {@code #column(IColumn)}.
     * 
     * @param column the column to add
     * @return this for chaining
     */
    public TableBuilder<T, S> add(IColumn<T, S> column) {
        addColumn(column);
        return this;
    }

    /**
     * Adds a column.
     * 
     * @param column the column to add
     * @return this for chaining
     */
    public TableBuilder<T, S> column(IColumn<T, S> column) {
        addColumn(column);
        return this;
    }

    /**
     * Sets a list of columns.
     *
     * @param columns the list of columns to add
     * @return this for chaining
     */
    public TableBuilder<T, S> columns(List<IColumn<T, S>> columns) {
        getColumns().addAll(columns);
        return this;
    }

    /**
     * Sets the rows per page displayed. Defaults to Integer.MAX_VALUE. Alias for {@code #rows(Integer)}.
     * 
     * @param rows rows per page
     * @return this for chaining
     */
    public TableBuilder<T, S> set(Integer rows) {
        setRowsPerPage(rows);
        return this;
    }

    /**
     * Sets the data provider used. Alias for {@code #data(ISortableDataProvider)}.
     * 
     * @param provider the data provider to use
     * @return this for chaining
     */
    public TableBuilder<T, S> set(ISortableDataProvider<T, S> provider) {
        setDataProvider(provider);
        return this;
    }

    /**
     * Sets the rows per page displayed. Defaults to Integer.MAX_VALUE.
     * 
     * @param rows rows per page
     * @return this for chaining
     */
    public TableBuilder<T, S> rows(Integer rows) {
        setRowsPerPage(rows);
        return this;
    }

    /**
     * Sets the data provider used.
     * 
     * @param provider the data provider to use
     * @return this for chaining
     */
    public TableBuilder<T, S> data(ISortableDataProvider<T, S> provider) {
        setDataProvider(provider);
        return this;
    }

    /**
     * Constructs a new SortableListDataProvider for the given list data and sets it as data provider.
     * 
     * @param data the data to use
     * @return this for chaining
     */
    @SuppressWarnings("unchecked")
    public TableBuilder<T, S> data(List<T> data) {
        return data(new SortableListDataProvider(data));
    }

    /**
     * Adds a column.
     * 
     * @param column the column to add
     */
    public void addColumn(IColumn<T, S> column) {
        columns.add(column);
    }

    @Override
    public List<IColumn<T, S>> getColumns() {
        return columns;
    }

    public void setColumns(List<IColumn<T, S>> columns) {
        this.columns = columns;
    }

    @Override
    public ISortableDataProvider<T, S> getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(ISortableDataProvider<T, S> dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public Integer getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    /**
     * Creates a new DataTable with the given id from the components fed to the TableBuilder.
     * 
     * @param id the component id
     * @return a new DataTable instance
     */
    public DataTable<T, S> create(String id) {
        return addBehaviors(createTable(id, this));
    }

    /**
     * Applies all collected behaviors by the table builder to the given component and returns it.
     * 
     * @param component the component to add the behaviors to
     * @param <C> the component type
     * @return the component
     */
    @SuppressWarnings("unchecked")
    protected <C extends Component> C addBehaviors(C component) {
        if (!behaviors.isEmpty()) {
            component.add(behaviors.toArray(new Behavior[behaviors.size()]));
        }
        return component;
    }

    /**
     * Factory method that can be overridden ins subclasses to create different instances.
     * 
     * @param id the component id
     * @param skeleton the table skeleton
     * @return a new DataTable instance
     */
    protected DataTable<T, S> createTable(String id, ITableSkeleton<T, S> skeleton) {
        return new DataTable<>(id, skeleton);
    }
}
