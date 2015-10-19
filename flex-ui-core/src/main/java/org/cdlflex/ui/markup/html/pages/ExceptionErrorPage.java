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
package org.cdlflex.ui.markup.html.pages;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.pages.AbstractErrorPage;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.io.IClusterable;
import org.cdlflex.ui.behavior.FrontendDependencyBehavior;
import org.cdlflex.ui.markup.html.panel.StackTracePanel;
import org.cdlflex.ui.resource.BootstrapCssResourceReference;
import org.cdlflex.ui.resource.BootstrapJsResourceReference;
import org.cdlflex.ui.resource.BootstrapThemeResourceReference;

/**
 * This page is used by the {@code DefaultExceptionMapper} to display an unhandled unexpected Exception. It displays the
 * exception in a friendly manner using bootstrap.
 */
public class ExceptionErrorPage extends AbstractErrorPage {

    private static final long serialVersionUID = 1L;

    private static final int INTERNAL_SERVER_ERROR = 500;

    /**
     * Construct.
     * 
     * @param cause the exception
     * @param page the page causing the exception
     */
    public ExceptionErrorPage(Exception cause, Page page) {
        this(new PageException(cause, page));
    }

    public ExceptionErrorPage(PageException model) {
        this(new CompoundPropertyModel<>(model));
    }

    public ExceptionErrorPage(IModel<PageException> model) {
        super(model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new FrontendDependencyBehavior(BootstrapCssResourceReference.get(), BootstrapJsResourceReference.get(),
                BootstrapThemeResourceReference.get()));

        add(new Label("cause.message"));
        add(new Label("cause.class.simpleName"));

        add(new Label("errorId"));
        add(new StackTracePanel("stackTracePanel", new ComponentPropertyModel<>("cause")));

        add(homePageLink("homePageLink"));
    }

    @Override
    protected void setHeaders(final WebResponse response) {
        response.setStatus(INTERNAL_SERVER_ERROR);
    }

    /**
     * Container object for an Exception and the Page that caused it.
     */
    protected static class PageException implements IClusterable {

        private static final long serialVersionUID = 1L;

        private final Exception cause;
        private final Page causingPage;

        public PageException(Exception cause, Page causingPage) {
            this.cause = cause;
            this.causingPage = causingPage;
        }

        public Exception getCause() {
            return cause;
        }

        public Page getCausingPage() {
            return causingPage;
        }

        public String getErrorId() {
            return String.valueOf(cause.hashCode());
        }

    }

}
