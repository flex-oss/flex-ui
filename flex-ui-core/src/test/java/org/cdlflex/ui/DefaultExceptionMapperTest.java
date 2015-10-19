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

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IExceptionMapper;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.util.IProvider;
import org.apache.wicket.util.tester.DummyHomePage;
import org.cdlflex.ui.markup.html.pages.ExceptionErrorPage;
import org.junit.Test;

/**
 * DefaultExceptionMapperTest.
 */
public class DefaultExceptionMapperTest extends AbstractWicketTest {

    @Override
    protected WebApplication createMockApplication() {
        return new MockApplication() {
            @Override
            protected void init() {
                super.init();
                // make sure to always show the custom exception page
                getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_EXCEPTION_PAGE);
            }

            @Override
            public IProvider<IExceptionMapper> getExceptionMapperProvider() {
                return DefaultExceptionMapper::new;
            }
        };
    }

    @Test
    public void onException_rendersDefaultExceptionPageWithStacktrace() throws Exception {
        getWicketTester().setExposeExceptions(false);
        getWicketTester().startPage(new TestPage());
        getWicketTester().clickLink("testPage");

        getWicketTester().assertRenderedPage(ExceptionErrorPage.class);

        // the printed stacktrace should contain the exception message
        getWicketTester().assertContains("test exception message");
    }

    private static class TestException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public TestException(String message) {
            super(message);
        }
    }

    private static class TestPage extends DummyHomePage {
        private static final long serialVersionUID = 1L;

        public TestPage() {
            addOrReplace(new Link<Void>("testPage") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick() {
                    throw new TestException("test exception message");
                }
            });
        }

    }
}
