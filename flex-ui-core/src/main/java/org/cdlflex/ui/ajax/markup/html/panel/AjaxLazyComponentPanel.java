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

import java.util.Objects;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.html.Tag;
import org.cdlflex.ui.util.IComponentFactory;

/**
 * An AjaxLazyLoadPanel that uses a IComponentFactory to construct its lazy-loaded component. It shows a spinning
 * FontAwesome cog icon by default. Note that this requires the FontAwesome CSS to be loaded.
 */
public class AjaxLazyComponentPanel extends AjaxLazyLoadPanel {
    private static final long serialVersionUID = 1L;

    private IComponentFactory<? extends Component> componentFactory;

    public AjaxLazyComponentPanel(String id, IComponentFactory<? extends Component> factory) {
        this(id, null, factory);
    }

    public AjaxLazyComponentPanel(String id, IModel<?> model, IComponentFactory<? extends Component> factory) {
        super(id, model);
        this.componentFactory = Objects.requireNonNull(factory);
    }

    @Override
    public final Component getLazyLoadComponent(String markupId) {
        return componentFactory.create(markupId);
    }

    @Override
    public Component getLoadingComponent(String markupId) {
        Tag icon = new Tag("i")
                .attr("class", "fa fa-cog fa-spin")
                .attr("style", "font-size: 32px")
                .setShortTag(false);

        return new Label(markupId, icon).add(new CssClassNameAppender("spinner")).setEscapeModelStrings(false);
    }

}
