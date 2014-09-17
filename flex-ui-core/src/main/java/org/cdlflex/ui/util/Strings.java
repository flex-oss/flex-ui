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

import java.util.Collection;
import java.util.Iterator;

/**
 * String utility class.
 */
public final class Strings {

    /**
     * An empty String.
     */
    public static final String EMPTY = "";

    private Strings() {
        // utility class
    }

    /**
     * Checks whether the given CharSequence is null or its length is zero.
     * 
     * @param sequence CharSequence to check
     * @return true if the sequence is null or has length 0
     */
    public static boolean isEmpty(CharSequence sequence) {
        return sequence == null || sequence.length() == 0;
    }

    /**
     * Joins the string values of every given object with the given separator as glue.
     * 
     * @param separator the glue
     * @param values an array of objects
     * @return a concatenated string
     */
    public static String join(CharSequence separator, Object... values) {
        if (values == null || values.length == 0) {
            return EMPTY;
        }

        StringBuilder str = new StringBuilder();
        joinInto(str, separator, values);
        return str.toString();
    }

    /**
     * Joins the string values of every given object with the given separator as glue.
     *
     * @param separator the glue
     * @param collection a collection of objects
     * @return a concatenated string
     */
    /**
     * Joins the string values of every given object with the given separator as glue.
     *
     * @param separator the glue
     * @param collection a collection of objects
     * @param <T> the collection type
     * @return a concatenated string
     */
    public static <T> String join(CharSequence separator, Collection<T> collection) {
        return join(separator, collection.toArray(new Object[collection.size()]));
    }

    /**
     * Joins the string values of every given object with the given separator as glue.
     *
     * @param separator the glue
     * @param collection a collection of objects
     * @param <T> the collection type
     * @return a concatenated string
     */
    public static <T> String join(CharSequence separator, Iterable<T> collection) {
        if (collection == null) {
            return EMPTY;
        }

        StringBuilder str = new StringBuilder();
        joinInto(str, separator, collection.iterator());
        return str.toString();
    }

    /**
     * Joins the string value sof every given object with the given separator as glue and appends those strings into the
     * given StringBuilder.
     * 
     * @param str the string builder to append to
     * @param separator the glue
     * @param array an array of objects
     */
    public static void joinInto(StringBuilder str, CharSequence separator, Object[] array) {
        for (int i = 0; i < array.length; i++) {
            str.append(valueOf(array[i]));

            if (i < (array.length - 1)) {
                str.append(separator);
            }
        }
    }

    /**
     * Joins the string value sof every given object with the given separator as glue and appends those strings into the
     * given StringBuilder.
     *
     * @param str the string builder to append to
     * @param separator the glue
     * @param <T> the collection type
     * @param collection a collection of objects
     */
    public static <T> void joinInto(StringBuilder str, CharSequence separator, Iterable<T> collection) {
        joinInto(str, separator, collection.iterator());
    }

    /**
     * Joins the string value sof every given object with the given separator as glue and appends those strings into the
     * given StringBuilder.
     *
     * @param str the string builder to append to
     * @param separator the glue
     * @param iterator a iterator of objects
     */
    public static void joinInto(StringBuilder str, CharSequence separator, Iterator<?> iterator) {
        if (iterator.hasNext()) {
            str.append(valueOf(iterator.next()));
        }

        while (iterator.hasNext()) {
            str.append(separator);
            str.append(valueOf(iterator.next()));
        }
    }

    /**
     * Returns the string value of the given object.
     * 
     * @param object the object
     * @return if the argument is null, then a string equal to "null"; otherwise, the value of obj.toString() is
     *         returned.
     */
    public static String valueOf(Object object) {
        return String.valueOf(object);
    }
}
