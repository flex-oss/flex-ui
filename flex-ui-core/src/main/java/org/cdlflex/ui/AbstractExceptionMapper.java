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

import java.util.Optional;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.authorization.AuthorizationException;
import org.apache.wicket.core.request.handler.IPageProvider;
import org.apache.wicket.core.request.handler.ListenerInvocationNotAllowedException;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler.RedirectPolicy;
import org.apache.wicket.core.request.mapper.StalePageException;
import org.apache.wicket.protocol.http.PageExpiredException;
import org.apache.wicket.protocol.http.servlet.ResponseIOException;
import org.apache.wicket.request.IExceptionMapper;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.EmptyRequestHandler;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.http.handler.ErrorCodeRequestHandler;
import org.apache.wicket.request.resource.PackageResource;
import org.apache.wicket.settings.IExceptionSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * If an exception is thrown when a page is being rendered this mapper will decide which error page to show depending on
 * the exception type and {@link Application#getExceptionSettings() application configuration}. An implementation is
 * required to provide the {@link #createExceptionDisplayPageProvider(Exception)} method that returns a PageProvider to
 * display the mapped Exception.
 * 
 * Most of the code was copied from Wicket's {@code org.apache.wicket.DefaultExceptionMapper}, but was cleaned up and
 * extended with Java 8 features.
 */
public abstract class AbstractExceptionMapper implements IExceptionMapper {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractExceptionMapper.class);

    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NOT_FOUND = 404;

    @SuppressWarnings("illegalcatch")
    @Override
    public IRequestHandler map(Exception e) {
        try {
            // we don't want to cache an exceptional reply in the browser
            getWebResponse().ifPresent(WebResponse::disableCaching);

            return internalMap(e);
        } catch (RuntimeException ie) { // exception thrown while handling the exception
            if (LOG.isDebugEnabled()) {
                LOG.error("An error occurred while handling a previous error: " + ie.getMessage(), ie);
            }

            LOG.error("Unexpected exception when handling another exception: " + e.getMessage(), e);
            // the mapping process failed, so this is the fallback error handler (nothing more we can really do)
            return new ErrorCodeRequestHandler(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Returns the PageProvider that is used for displaying an unhandled Exception.
     * 
     * @param e the exception that should be displayed
     * @return a PageProvider
     */
    protected abstract IPageProvider createExceptionDisplayPageProvider(Exception e);

    private IRequestHandler internalMap(Exception e) {
        final Application application = Application.get();

        // check if we are processing an Ajax request and if we want to invoke the failure handler
        if (isAjaxRequest() && shouldInvokeClientSideFailureHandler(application)) {
            return new ErrorCodeRequestHandler(INTERNAL_SERVER_ERROR);
        }

        if (e instanceof StalePageException) {
            // If the page was stale, just re-render it (the url should always be updated by an redirect in that case)
            return new RenderPageRequestHandler(new PageProvider(((StalePageException) e).getPage()));
        } else if (e instanceof PageExpiredException) {
            return createPageRequestHandler(application.getApplicationSettings().getPageExpiredErrorPage());
        } else if (e instanceof AuthorizationException || e instanceof ListenerInvocationNotAllowedException) {
            return createPageRequestHandler(application.getApplicationSettings().getAccessDeniedPage());
        } else if (e instanceof ResponseIOException) {
            LOG.error("Connection lost, give up responding.", e);
            return new EmptyRequestHandler();
        } else
            if (e instanceof PackageResource.PackageResourceBlockedException && application.usesDeploymentConfig()) {
            LOG.debug(e.getMessage(), e);
            return new ErrorCodeRequestHandler(NOT_FOUND);
        }

        return mapUnexpectedException(e);
    }

    private boolean shouldInvokeClientSideFailureHandler(Application application) {
        IExceptionSettings.AjaxErrorStrategy strategy =
            application.getExceptionSettings().getAjaxErrorHandlingStrategy();

        return strategy == IExceptionSettings.AjaxErrorStrategy.INVOKE_FAILURE_HANDLER;
    }

    private IRequestHandler mapUnexpectedException(Exception e) {
        LOG.error("{}: An unexpected error occurred", getExceptionId(e), e);

        Application application = Application.get();
        IExceptionSettings.UnexpectedExceptionDisplay display =
            application.getExceptionSettings().getUnexpectedExceptionDisplay();

        if (IExceptionSettings.SHOW_EXCEPTION_PAGE.equals(display)) {
            return createPageRequestHandler(createExceptionDisplayPageProvider(e));
        } else if (IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE.equals(display)) {
            return createPageRequestHandler(application.getApplicationSettings().getInternalErrorPage());
        } else {
            // IExceptionSettings.SHOW_NO_EXCEPTION_PAGE
            return new ErrorCodeRequestHandler(INTERNAL_SERVER_ERROR);
        }
    }

    private String getExceptionId(Exception e) {
        return String.valueOf(e.hashCode());
    }

    private RenderPageRequestHandler createPageRequestHandler(Class<? extends Page> pageClass) {
        return createPageRequestHandler(new PageProvider(pageClass));
    }

    private RenderPageRequestHandler createPageRequestHandler(IPageProvider pageProvider) {
        return new RenderPageRequestHandler(pageProvider, getRedirectPolicy());
    }

    private RedirectPolicy getRedirectPolicy() {
        return isAjaxRequest() ? RedirectPolicy.AUTO_REDIRECT : RedirectPolicy.NEVER_REDIRECT;
    }

    private boolean isAjaxRequest() {
        return getWebRequest().map(WebRequest::isAjax).orElse(false);
    }

    private Optional<WebRequest> getWebRequest() {
        return Optional.ofNullable(RequestCycle.get())
                .map(RequestCycle::getRequest)
                .filter((r) -> r instanceof WebRequest)
                .map((r) -> (WebRequest) r);
    }

    private Optional<WebResponse> getWebResponse() {
        return Optional.ofNullable(RequestCycle.get())
                .map(RequestCycle::getResponse)
                .filter((r) -> r instanceof WebResponse)
                .map((r) -> (WebResponse) r);
    }

}
