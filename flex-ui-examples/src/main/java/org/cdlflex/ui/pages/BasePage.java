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
package org.cdlflex.ui.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.cdlflex.ui.behavior.FrontendDependencyBehavior;
import org.cdlflex.ui.resource.BootstrapCssResourceReference;
import org.cdlflex.ui.resource.BootstrapJsResourceReference;
import org.cdlflex.ui.resource.BootstrapThemeResourceReference;

/**
 * Base page containing the general bootstrap layout.
 */
public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;

    public BasePage() {
        super();
    }

    public BasePage(IModel<?> model) {
        super(model);
    }

    public BasePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new FrontendDependencyBehavior(BootstrapCssResourceReference.get(), BootstrapJsResourceReference.get(),
                BootstrapThemeResourceReference.get(), new CssResourceReference(BasePage.class, "style.css")));
    }
}
