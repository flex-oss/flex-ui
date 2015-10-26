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

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.wicket.Component;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Test;

public class ApplicationCallableTest extends AbstractWicketTest {

    @Test
    public void createComponentInNewThread_attachesThreadContextCorrectly() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Component> associated = ThreadUtils.associateWithCurrent(() -> new WebMarkupContainer("test"));

        Future<Component> test = executor.submit(associated);

        // without the association, this would throw an exception because no ThreadContext is associated with the thread
        // that generated the component.
        Component component = test.get();
        assertNotNull(component);
        assertNotNull(ComponentRenderer.renderComponent(component));

        executor.shutdown();
    }
}
