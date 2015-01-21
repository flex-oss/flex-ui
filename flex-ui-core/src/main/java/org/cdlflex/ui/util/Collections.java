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
package org.cdlflex.ui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Such λ. So Fn. Wow.
 */
public final class Collections {

    private Collections() {
        // Much utility.
    }

    /**
     * Returns an empty list from java.util.Collections.
     * 
     * @param <T> the element type
     * @return empty list
     */
    public static <T> List<T> emptyList() {
        return java.util.Collections.emptyList();
    }

    /**
     * Returns an empty map from java.util.Collections.
     * 
     * @param <K> the map key type
     * @param <V> the map value type
     * @return an empty map
     */
    public static <K, V> Map<K, V> emptyMap() {
        return java.util.Collections.emptyMap();
    }

    /**
     * Checks if the given Collection is null or empty.
     *
     * @param collection the collection to check
     * @return true if the Collection is null or empty.
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Applies the given visitor to each element in the collection.
     *
     * @param collection the collection to iterate
     * @param visitor the visitor
     * @param <T> collection type
     * @param <E> exception type
     * @throws E an exception
     */
    public static <T, E extends Exception> void walk(Iterable<T> collection, CheckedVisitor<T, E> visitor) throws E {
        for (T element : collection) {
            visitor.visit(element);
        }
    }

    /**
     * Applies the given visitor to each element in the collection. If the visitor returns BREAK, the loop is exited.
     *
     * @param collection the collection to iterate
     * @param visitor the visitor
     * @param <T> collection type
     * @param <E> exception type
     * @throws E an exception
     */
    public static <T, E extends Exception> void walk(Iterable<T> collection, TerminatingCheckedVisitor<T, E> visitor)
        throws E {
        for (T element : collection) {
            if (visitor.call(element) == VisitorAction.BREAK) {
                break;
            }
        }
    }

    /**
     * Returns a list obtained by applying the given callback to each element in the list.
     *
     * @param collection the collection to iterate
     * @param fn the callback
     * @param <A> argument type
     * @param <R> return type
     * @param <E> exception type
     * @return a list of elements
     * @throws E an exception
     */
    public static <A, R, E extends Exception> List<R> map(Collection<A> collection, CheckedCallback<A, R, E> fn)
        throws E {
        List<R> result = new ArrayList<>(collection.size());

        for (A object : collection) {
            result.add(fn.call(object));
        }

        return result;
    }

    /**
     * Returns a list of all elements of the collection that satisfy the predicate.
     *
     * @param collection the collection to iterate
     * @param predicate the predicate
     * @param <T> collection element type
     * @return a list of all elements of the collection that satisfy the predicate
     */
    public static <T> List<T> filter(Collection<T> collection, Callback<T, Boolean> predicate) {
        List<T> result = new ArrayList<>(collection.size());

        for (T object : collection) {
            if (predicate.call(object)) {
                result.add(object);
            }
        }

        return result;
    }

    /**
     * Retain all elements of the given iterable that satisfy the given predicate. Others will be removed via
     * {@link java.util.Iterator#remove()}.
     * 
     * @param iterable the iterable, iterators must support {@link java.util.Iterator#remove()}
     * @param predicate the predicate
     * @param <T> element type
     */
    public static <T> void retain(Iterable<T> iterable, Callback<T, Boolean> predicate) {
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            if (!predicate.call(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Removes all objects from the given collection that satisfy the given predicate. Requires a collection that
     * supports {@link java.util.Iterator#remove()}.
     *
     * @param collection the collection
     * @param predicate the predicate
     * @param <T> collection type
     */
    public static <T> void remove(Iterable<T> collection, Callback<T, Boolean> predicate) {
        Iterator<T> iterator = collection.iterator();

        while (iterator.hasNext()) {
            if (predicate.call(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Counts the amount of elements that satisfy the given predicate.
     *
     * @param collection the collection to iterate
     * @param predicate the predicate
     * @param <T> collection element type
     * @return the amount of elements that satisfy the given predicate.
     */
    public static <T> int count(Collection<T> collection, Callback<T, Boolean> predicate) {
        int cnt = 0;

        for (T object : collection) {
            if (predicate.call(object)) {
                cnt++;
            }
        }

        return cnt;
    }

    /**
     * Returns the first element of the collection that satisfies the predicate.
     *
     * @param collection the collection to iterate
     * @param predicate the predicate
     * @param <T> collection element type
     * @return the first element of the collection that satisfies the predicate
     */
    public static <T> T first(Iterable<T> collection, Callback<T, Boolean> predicate) {
        for (T object : collection) {
            if (predicate.call(object)) {
                return object;
            }
        }

        return null;
    }

    /**
     * Determines if all elements of the collection satisfies the predicate.
     *
     *
     * @param collection the collection to iterate
     * @param predicate the predicate
     * @param <T> collection element type
     * @return true if all element satisfy the predicate
     */
    public static <T> boolean all(Iterable<T> collection, Callback<T, Boolean> predicate) {
        boolean bool = true;

        for (T object : collection) {
            bool &= predicate.call(object);
        }

        return bool;
    }

    /**
     * Determines if any element of the collection satisfies the predicate.
     *
     * @param collection the collection to iterate
     * @param predicate the predicate
     * @param <T> collection element type
     * @return true as soon as an element is found that satisfies the predicate
     */
    public static <T> boolean any(Iterable<T> collection, Callback<T, Boolean> predicate) {
        for (T object : collection) {
            if (predicate.call(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds every element in the given Iterable into a new list and returns it.
     * 
     * @param iterable the iterable
     * @param <T> the element type
     * @return a new array list of all elements in the iterable
     */
    public static <T> List<T> asList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();

        for (T e : iterable) {
            list.add(e);
        }

        return list;
    }

    /**
     * Sorts a list.
     * 
     * @param list list to sort
     * @param <T> Collection element type
     * @see java.util.Collections#sort(java.util.List)
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        java.util.Collections.sort(list);
    }

    /**
     * Sorts a list.
     * 
     * @param list list to sort
     * @param comparator comparator to use
     * @param <T> Collection element type
     * @see java.util.Collections#sort(java.util.List, java.util.Comparator)
     */
    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        java.util.Collections.sort(list, comparator);
    }

    /**
     * A functional interface for a callback that may throw a checked exception.
     *
     * @param <A> argument type
     * @param <R> return type
     * @param <E> exception type
     */
    public interface CheckedCallback<A, R, E extends Exception> {

        /**
         * Execute a call on the given object.
         *
         * @param object the target object
         * @return a return value
         * @throws E an exception
         */
        R call(A object) throws E;
    }

    /**
     * A functional interface for a callback that may throw an unchecked exception.
     *
     * @param <A> argument type
     * @param <R> return type
     */
    public interface Callback<A, R> extends CheckedCallback<A, R, RuntimeException> {
        @Override
        R call(A object);
    }

    /**
     * A callback that returns a boolean.
     *
     * @param <T> element type
     */
    public interface Predicate<T> extends Callback<T, Boolean> {
        @Override
        Boolean call(T object);
    }

    /**
     * A functional interface for visiting elements in a collection that may throw a checked exception.
     *
     * @param <T> element type
     * @param <E> exception type
     */
    public interface CheckedVisitor<T, E extends Exception> {

        /**
         * Visit the given object.
         *
         * @param object the object to visit
         * @throws E an exception
         */
        void visit(T object) throws E;
    }

    /**
     * A functional interface for visiting elements in a collection that may throw an unchecked exception.
     *
     * @param <T> element type
     */
    public interface Visitor<T> extends CheckedVisitor<T, RuntimeException> {
        @Override
        void visit(T object);
    }

    /**
     * A functional interface for visiting elements in a collection that may throw a checked exception and provide an
     * additional loop termination action.
     *
     * @param <T> element type
     * @param <E> exception type
     */
    public interface TerminatingCheckedVisitor<T, E extends Exception> extends CheckedCallback<T, VisitorAction, E> {
        @Override
        VisitorAction call(T object) throws E;
    }

    /**
     * A functional interface for visiting elements in a collection that may throw an unchecked exception and provide an
     * additional loop termination action.
     *
     * @param <T> element type
     */
    public interface TerminatingVisitor<T> extends TerminatingCheckedVisitor<T, RuntimeException>,
            Callback<T, VisitorAction> {
        @Override
        VisitorAction call(T object);
    }

    /**
     * The return type of a TerminatingCheckedVisitor or TerminatingVisitor.
     */
    public static enum VisitorAction {
        CONTINUE,
        BREAK
    }

    /**
     * Predicate implementation that returns true if the given object is not null.
     * 
     * @param <T> element type
     */
    public static class NotNullFilter<T> implements Predicate<T> {

        @Override
        public Boolean call(T object) {
            return object != null;
        }
    }

}
