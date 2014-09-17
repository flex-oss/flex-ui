package org.cdlflex.ui.markup.html.repeater.data.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.cdlflex.ui.util.compare.DefaultModelPropertyValueComparator;

/**
 * Extends a {@code ListDataProvider} and provides the {@code ISortableDataProvider} interface. It uses a very primitive
 * sorting technique namely by comparing the string values of the respective columns (which will work for most data).
 * 
 * @param <T> the type of the provided data model
 * @param <S> the type of the sorting parameter
 */
public class SortableListDataProvider<T extends Serializable, S> extends ListDataProvider<T> implements
        ISortableDataProvider<T, S> {

    private static final long serialVersionUID = 1L;

    private final SingleSortState<S> sortState = new SingleSortState<>();

    public SortableListDataProvider() {
        super(new ArrayList<T>());
    }

    public SortableListDataProvider(List<T> list) {
        super(list);
    }

    @Override
    public ISortState<S> getSortState() {
        return sortState;
    }

    /**
     * Returns the {@code SortParam} of the current {@code SortState}.
     * 
     * @return the sort parameter
     */
    public SortParam<S> getSort() {
        return sortState.getSort();
    }

    @Override
    protected List<T> getData() {
        List<T> data = getListData();

        sort(data);

        return data;
    }

    /**
     * Returns the underlying list (before its sorted). Can be overridden by subclasses that provide their own lists.
     * 
     * @return the list of data being displayed
     */
    protected List<T> getListData() {
        return super.getData();
    }

    /**
     * Sorts the data returned by {@code ListDataProvider#getData()} by the current sort property.
     * 
     * @param data the input list
     */
    protected void sort(List<T> data) {
        if (getSortState() == null || getSort() == null) {
            return;
        }

        S property = getSort().getProperty();
        if (!(property instanceof String)) {
            return;
        }

        sort(data, (String) property, getSort().isAscending());
    }

    /**
     * Sorts the given list by the property declared in the given property expression.
     * 
     * @param data the input list
     * @param propertyExpression the expression that declares the property by which to sort the data
     * @param ascending whether or not to sort the data ascending
     */
    protected void sort(List<T> data, String propertyExpression, boolean ascending) {
        Comparator<T> comparator = new DefaultModelPropertyValueComparator<T>(propertyExpression);

        if (ascending) {
            Collections.sort(data, comparator);
        } else {
            Collections.sort(data, Collections.reverseOrder(comparator));
        }
    }

}
