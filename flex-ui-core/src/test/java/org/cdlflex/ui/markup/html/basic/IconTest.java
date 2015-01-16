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
package org.cdlflex.ui.markup.html.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.cdlflex.ui.markup.css.icon.FontAwesomeIconType;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.junit.Test;

public class IconTest extends AbstractWicketTest {
    @Test
    public void render_containsCorrectCssClasses() throws Exception {
        Icon icon = new Icon("icon", FontAwesomeIconType.BEER);

        TagTester tagTester = renderAndCreateTag(icon);

        List<String> classes = Arrays.asList(tagTester.getAttribute("class").split(" "));
        assertEquals(2, classes.size());
        assertTrue("missing font-awesome base icon class", classes.contains("fa"));
        assertTrue("missing concrete font-awesome icon class", classes.contains("fa-beer"));
    }

    @Test
    public void hasIconType_behavesCorrectly() throws Exception {
        Icon icon = new Icon("icon");

        assertFalse(icon.hasIconType());

        IModel<IconType> model = Model.of();
        icon.setIconType(model);

        assertFalse(icon.hasIconType());

        model.setObject(GlyphIconType.HEART);

        assertTrue(icon.hasIconType());

        model.setObject(null);

        assertFalse(icon.hasIconType());

        icon.setIconType(GlyphIconType.ADJUST);

        assertTrue(icon.hasIconType());
    }
}
