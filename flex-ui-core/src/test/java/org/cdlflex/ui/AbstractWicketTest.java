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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.mock.MockHttpServletResponse;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;

/**
 * Basic helper parent class that provides convenience functionality to test wicket components.
 */
public class AbstractWicketTest {

    protected WicketTester wicketTester;

    @Before
    public void setUpWicketTestEnvironment() throws Exception {
        wicketTester = new WicketTester(createMockApplication());
    }

    public WicketTester getWicketTester() {
        return wicketTester;
    }

    public <T extends Page> void startPage(T page) {
        getWicketTester().startPage(page);
    }

    public MockHttpServletResponse getLastResponse() {
        return getWicketTester().getLastResponse();
    }

    public String getLastResponseDocument() {
        return getLastResponse().getDocument();
    }

    public <T extends WebPage> String render(T page) {
        startPage(page);
        return getLastResponseDocument();
    }

    public TagTester createTagById(String id) {
        return createTagById(getLastResponseDocument(), id);
    }

    public TagTester createTagById(String html, String id) {
        return TagTester.createTagByAttribute(html, "id", id);
    }

    public TagTester createTagByWicketId(String id) {
        return createTagByWicketId(getLastResponseDocument(), id);
    }

    public TagTester createTagByWicketId(String html, String id) {
        return TagTester.createTagByAttribute(html, "wicket:id", id);
    }

    public TagTester createTag(Component component) {
        return createTag(wicketTester.getLastResponse().getDocument(), component);
    }

    public TagTester createTag(String html, Component component) {
        return TagTester.createTagByAttribute(html, "wicket:id", component.getId());
    }

    public TagTester renderAndCreateTag(Component component) {
        CharSequence markup = ComponentRenderer.renderComponent(component);
        return TagTester.createTagByAttribute(markup.toString(), "wicket:id", component.getId());
    }

    public static void assertCssClasses(TagTester tag, String... expected) {
        List<String> actualList = Arrays.asList(tag.getAttribute("class").trim().split(" "));
        List<String> expectedList = Arrays.asList(expected);

        for (String cssClass : expected) {
            assertTrue("Expected css class " + cssClass + " not in list " + actualList, actualList.contains(cssClass));
        }

        for (String cssClass : actualList) {
            if (!expectedList.contains(cssClass)) {
                fail("Unexpected css class " + cssClass);
            }
        }

    }

    protected WebApplication createMockApplication() {
        return new MockApplication();
    }
}
