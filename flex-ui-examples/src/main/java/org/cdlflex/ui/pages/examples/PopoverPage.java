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

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.behavior.PopoverBehavior;
import org.cdlflex.ui.behavior.PopoverBehavior.Placement;
import org.cdlflex.ui.behavior.PopoverBehavior.Trigger;
import org.cdlflex.ui.markup.css.Buttons.Size;
import org.cdlflex.ui.markup.css.Buttons.Type;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.html.basic.Icon;

public class PopoverPage extends StripTagExamplePage {
    private static final long serialVersionUID = 1L;

    public PopoverPage() {

        add(new WebMarkupContainer("default-button").add(new ButtonBehavior(Type.DANGER, Size.LARGE)).add(
                new PopoverBehavior("Popover title", "And here's some amazing content. It's very engaging. Right?")));

        IModel<String> body = Model.of("Vivamus sagittis lacus vel augue laoreet rutrum faucibus.");
        IModel<String> title = Model.of();

        add(new WebMarkupContainer("left").add(new PopoverBehavior(title, body, Model.of(Placement.LEFT))));
        add(new WebMarkupContainer("top").add(new PopoverBehavior(title, body, Model.of(Placement.TOP))));
        add(new WebMarkupContainer("bottom").add(new PopoverBehavior(title, body, Model.of(Placement.BOTTOM))));
        add(new WebMarkupContainer("right").add(new PopoverBehavior(title, body, Model.of(Placement.RIGHT))));

        add(new Icon("hover", GlyphIconType.INFOSIGN).add(new PopoverBehavior(body).setTrigger(Trigger.HOVER)));
        add(new WebMarkupContainer("focus").add(new PopoverBehavior(body).setTrigger(Trigger.FOCUS)));
    }
}
