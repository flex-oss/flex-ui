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
package org.cdlflex.ui;

import org.apache.wicket.Page;
import org.apache.wicket.core.request.handler.IPageRequestHandler;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;
import org.cdlflex.ui.markup.html.pages.ExceptionErrorPage;

/**
 * If an exception is thrown when a page is being rendered this mapper will decide which error page to show depending on
 * the exception type and {@code Application#getExceptionSettings()} application configuration.
 */
public class DefaultExceptionMapper extends AbstractExceptionMapper {

    @Override
    protected PageProvider createExceptionDisplayPageProvider(Exception e) {
        return new PageProvider(new ExceptionErrorPage(e, extractCurrentPage()));
    }

    /**
     * Returns the page being rendered when the exception was thrown, or {@code null} if it cannot be extracted.
     *
     * @return a Page
     */
    private Page extractCurrentPage() {
        RequestCycle requestCycle = RequestCycle.get();

        if (requestCycle == null) {
            return null;
        }

        IRequestHandler handler = requestCycle.getActiveRequestHandler();

        if (handler == null) {
            handler = requestCycle.getRequestHandlerScheduledAfterCurrent();
        }

        if (handler instanceof IPageRequestHandler) {
            return (Page) ((IPageRequestHandler) handler).getPage();
        } else {
            return null;
        }
    }

}
