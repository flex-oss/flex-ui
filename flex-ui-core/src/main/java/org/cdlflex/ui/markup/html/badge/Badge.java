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

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.CssClassNameAppender;

/**
 * A <a href="http://getbootstrap.com/components/#badges">bootstrap badge</a>. The passed model is used as body.
 */
public class Badge extends WebMarkupContainer {

    private static final long serialVersionUID = 1L;

    public Badge(String id) {
        super(id);
    }

    public Badge(String id, IModel<?> model) {
        super(id, model);
    }

    public IModel<?> getDisplayModel() {
        return getDefaultModel();
    }

    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        super.onComponentTagBody(markupStream, openTag);

        Object obj = getDefaultModelObject();
        if (obj != null) {
            replaceComponentTagBody(markupStream, openTag, String.valueOf(obj));
        }
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        CssClassNameAppender.append(tag, "badge");
    }
}
