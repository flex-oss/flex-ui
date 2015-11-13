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

import java.lang.reflect.Field;

/**
 * Reflection utilities.
 */
public final class Reflections {

    private Reflections() {
        // util class
    }

    /**
     * Calls {@link Field#set(Object, Object)}, manages the accessible flag of the field and wraps checked
     * IllegalAccessException as UnsupportedOperationException.
     * 
     * @param field the field to access
     * @param target the object to access
     * @param value the value to set
     */
    public static void set(Field field, Object target, Object value) {
        boolean accessible = field.isAccessible();
        try {
            field.setAccessible(true);
            field.set(target, value);
        } catch (IllegalAccessException e) {
            throw new UnsupportedOperationException(e);
        } finally {
            field.setAccessible(accessible);
        }
    }

    /**
     * Calls {@link Field#get(Object)}, manages the accessible flag of the field and wraps checked
     * IllegalAccessException as UnsupportedOperationException.
     * 
     * @param field the field to access
     * @param target the object to access
     * @param <T> the type
     * @return the value of the field
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Field field, Object target) {
        boolean accessible = field.isAccessible();
        try {
            field.setAccessible(true);
            return (T) field.get(target);
        } catch (IllegalAccessException e) {
            throw new UnsupportedOperationException(e);
        } finally {
            field.setAccessible(accessible);
        }
    }

    /**
     * Calls {@link Class#getDeclaredField(String)} on the given class with the given name and wraps the checked
     * exception into an unchecked IllegalArgumentException.
     * 
     * @param clazz the class
     * @param name the name of the field
     * @return the Field
     * @throws IllegalArgumentException if there is no such field
     */
    public static Field getDeclaredField(Class<?> clazz, String name) throws IllegalArgumentException {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
