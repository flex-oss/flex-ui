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
package org.cdlflex.ui.markup.html.badge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.apache.wicket.core.util.string.ComponentRenderer;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketPageTest;
import org.junit.Test;

public class BadgeTest extends AbstractWicketPageTest {

    @Test
    public void getDisplayModel_returnsCorrectModel() throws Exception {
        Badge badge = new Badge("badge");

        assertNull(badge.getDisplayModel());

        Model<String> model = Model.of("Model");
        badge.setDefaultModel(model);

        assertSame(model, badge.getDisplayModel());
    }

    @Test
    public void badge_rendersCorrectly() throws Exception {
        Badge badge = new Badge("badge", Model.of(42));

        CharSequence markup = ComponentRenderer.renderComponent(badge);
        TagTester tagTester = TagTester.createTagByAttribute(markup.toString(), "wicket:id", "badge");
        assertEquals("badge", tagTester.getAttribute("class"));
        assertEquals("42", tagTester.getValue());

        // change model and render again
        badge.setDefaultModelObject(43);
        markup = ComponentRenderer.renderComponent(badge);
        tagTester = TagTester.createTagByAttribute(markup.toString(), "wicket:id", "badge");
        assertEquals("badge", tagTester.getAttribute("class"));
        assertEquals("43", tagTester.getValue());
    }
}
