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
package org.cdlflex.ui.ajax.markup.html.panel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanelTester;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.cdlflex.ui.AbstractWicketTest;
import org.junit.Test;

public class AjaxLazyComponentPanelTest extends AbstractWicketTest {

    @Test
    public void ajaxLazyComponentPanel_behavesCorrectly() {
        WicketTester tester = getWicketTester();

        AjaxLazyComponentPanel panel =
            new AjaxLazyComponentPanel("panel", id -> new Label(id, "lazy panel test").setRenderBodyOnly(true));

        tester.startComponentInPage(panel);

        TagTester content = tester.getTagByWicketId("content");
        System.out.println(content.getMarkup());

        assertNotNull(content);
        assertTrue("Container missing css class 'spinner'", content.getAttributeContains("class", "spinner"));
        assertEquals("<i style=\"font-size: 32px\" class=\"fa fa-cog fa-spin\"></i>", content.getValue());

        AjaxLazyLoadPanelTester.executeAjaxLazyLoadPanel(tester, panel.getParent());
        // tester.debugComponentTrees();

        tester.assertLabel("panel:content", "lazy panel test");
    }
}
