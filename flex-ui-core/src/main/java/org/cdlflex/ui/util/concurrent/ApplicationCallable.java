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

import java.util.Objects;
import java.util.concurrent.Callable;

import org.apache.wicket.ThreadContext;

/**
 * This Callable decorator associates the thread the Callable call is executed in with the thread context of the caller.
 * It does this by decorating the {@link #call()} method with a call to {@link ThreadContext#restore(ThreadContext)} and
 * finally detaching the ThreadContext again.
 *
 * @param <V> the result type of method {@code call}
 */
public final class ApplicationCallable<V> implements Callable<V> {

    private final Callable<V> delegate;
    private final ThreadContext threadContext;

    public ApplicationCallable(Callable<V> delegate, ThreadContext threadContext) {
        this.delegate = Objects.requireNonNull(delegate);
        this.threadContext = Objects.requireNonNull(threadContext);
    }

    @Override
    public final V call() throws Exception {
        try {
            ThreadContext.restore(threadContext);
            return delegate.call();
        } finally {
            ThreadContext.detach();
        }
    }
}
