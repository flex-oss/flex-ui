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
package org.cdlflex.ui.util.convert;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.tester.DummyHomePage;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Test;

public class EnumRendererTest extends AbstractWicketTest {

    @Test
    public void convertToString_withoutLocalizationProvider_behavesCorrectly() throws Exception {
        EnumRenderer<TestEnum> renderer = new EnumRenderer<>();
        assertEquals("Foo", renderer.convertToString(TestEnum.FOO));
        assertEquals("Bar ed", renderer.convertToString(TestEnum.BAR_ED));
        assertEquals("Baz", renderer.convertToString(TestEnum.BAZ_));
    }

    @Test
    public void converToString_withLocalizationProvider_rendersPropertyValues() throws Exception {
        Component component = new TestPage();

        EnumRenderer<TestEnum> renderer = new EnumRenderer<>(component);
        assertEquals("foo", renderer.convertToString(TestEnum.FOO));
        assertEquals("bared", renderer.convertToString(TestEnum.BAR_ED));
        assertEquals("baz", renderer.convertToString(TestEnum.BAZ_));
    }

    @Test
    public void converToString_withLocalizationProvider_noProperties_rendersKeys() throws Exception {
        Component component = new DummyHomePage();

        EnumRenderer<TestEnum> renderer = new EnumRenderer<>(component);
        assertEquals("FOO", renderer.convertToString(TestEnum.FOO));
        assertEquals("BAR_ED", renderer.convertToString(TestEnum.BAR_ED));
        assertEquals("BAZ_", renderer.convertToString(TestEnum.BAZ_));
    }

    private static class TestPage extends WebPage {
        // used as localization provider
        private static final long serialVersionUID = 1L;
    }
}
