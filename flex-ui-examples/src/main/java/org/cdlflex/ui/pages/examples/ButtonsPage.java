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
package org.cdlflex.ui.pages.examples;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.html.button.DropDownButton;
import org.cdlflex.ui.markup.html.link.BookmarkablePageButton;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * ButtonsPage.
 */
public class ButtonsPage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    public ButtonsPage() {
        add(new BookmarkablePageButton<Void>("bookmarkable-page-button", ButtonsPage.class));
        add(new BookmarkablePageButton<Void>("bookmarkable-page-button-replaced", ButtonsPage.class).setBody(Model
                .of("Replaced")));
        add(new BookmarkablePageButton<Void>("bookmarkable-page-icon-button", ButtonsPage.class)
                .setIconType(GlyphIconType.LINK));
        add(new BookmarkablePageButton<Void>("bookmarkable-page-icon-button-replaced", ButtonsPage.class)
                .setIconType(GlyphIconType.LINK).setBody(Model.of("Replaced")));
        add(new BookmarkablePageButton<Void>("bookmarkable-page-icon-button-styled", ButtonsPage.class)
                .setIconType(GlyphIconType.LINK).setType(Buttons.Type.PRIMARY).setSize(Buttons.Size.MINI));

        add(new DropDownButton("dropdown", Model.of("Pages")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newSubMenuButtons(String id) {
                List<AbstractLink> links = new ArrayList<>();

                for (ExamplePage.LinkItem linkItem : getLinks()) {
                    links.add(linkItem.newLink(id));
                }

                return links;
            }
        });
    }
}
