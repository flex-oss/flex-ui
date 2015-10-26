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
package org.cdlflex.ui.util.concurrent;

import java.util.concurrent.Callable;

import org.apache.wicket.ThreadContext;

/**
 * Util class for concurrency related mechanics.
 */
public final class ThreadUtils {

    private ThreadUtils() {
        // util class
    }

    /**
     * Associates the given Callable with the current ThreadContext.
     * 
     * @param callable the callable to decorate
     * @param <V> the callable result type
     * @return a decorated callable
     */
    public static <V> Callable<V> associateWithCurrent(Callable<V> callable) {
        return associateWith(callable, ThreadContext.get(false));
    }

    /**
     * Associates the given Callable with the given ThreadContext.
     *
     * @param callable the callable to decorate
     * @param context the ThreadContext to associate with
     * @param <V> the callable result type
     * @return a decorated callable
     */
    public static <V> Callable<V> associateWith(Callable<V> callable, ThreadContext context) {
        return new ApplicationCallable<>(callable, context);
    }
}
