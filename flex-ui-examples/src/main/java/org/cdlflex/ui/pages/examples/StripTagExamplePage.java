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

import org.apache.wicket.Application;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * An ExamplePage that strips wicket tags (some bootstrap components don't like the development tags of Wicket.
 */
public abstract class StripTagExamplePage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    private final boolean stripTags;

    public StripTagExamplePage() {
        super();
        stripTags = Application.get().getMarkupSettings().getStripWicketTags();
    }

    public StripTagExamplePage(IModel<?> model) {
        super(model);
        stripTags = Application.get().getMarkupSettings().getStripWicketTags();
    }

    public StripTagExamplePage(PageParameters parameters) {
        super(parameters);
        stripTags = Application.get().getMarkupSettings().getStripWicketTags();
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        Application.get().getMarkupSettings().setStripWicketTags(true);
    }

    @Override
    protected void onAfterRender() {
        super.onAfterRender();
        Application.get().getMarkupSettings().setStripWicketTags(stripTags);
    }
}
