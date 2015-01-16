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
package org.cdlflex.ui.markup.html.link;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.css.Buttons;
import org.junit.Before;
import org.junit.Test;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsCall;

public class JsButtonTest extends AbstractWicketTest {

    JsButton button;

    @Before
    public void setUp() throws Exception {
        button = new JsButton("button") {
            private static final long serialVersionUID = 1L;

            @Override
            public IJavaScript onClick() {
                return new JsCall("alert", "clicked");
            }
        }.setBody(Model.of("Button body"));
    }

    @Test
    public void defaultButton_rendersCorrectly() throws Exception {
        TagTester tag = renderAndCreateTag(button);

        List<String> classes = Arrays.asList(tag.getAttribute("class").trim().split(" "));
        assertEquals(2, classes.size());
        assertTrue(classes.contains("btn"));
        assertTrue(classes.contains("btn-default"));

        assertEquals("Button body", tag.getValue());
    }

    @Test
    public void afterChangingButtonAttributes_rendersCorrectly() throws Exception {
        button.size(Buttons.Size.SMALL).type(Buttons.Type.PRIMARY);

        TagTester tag = renderAndCreateTag(button);

        List<String> classes = Arrays.asList(tag.getAttribute("class").trim().split(" "));
        assertEquals(3, classes.size());
        assertTrue(classes.contains("btn"));
        assertTrue(classes.contains("btn-sm"));
        assertTrue(classes.contains("btn-primary"));
    }
}
