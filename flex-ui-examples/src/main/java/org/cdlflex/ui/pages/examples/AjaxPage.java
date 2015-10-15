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

import org.apache.wicket.markup.html.panel.Panel;
import org.cdlflex.ui.ajax.markup.html.panel.AjaxLazyComponentPanel;
import org.cdlflex.ui.behavior.FrontendDependencyBehavior;
import org.cdlflex.ui.resource.FontAwesomeCssResourceReference;

public class AjaxPage extends StripTagExamplePage {
    private static final long serialVersionUID = 1L;

    public AjaxPage() {
        add(new FrontendDependencyBehavior(FontAwesomeCssResourceReference.get()));

        add(new AjaxLazyComponentPanel("lazyLoadingPanel", ComputationPanel::new));
    }

    private static class ComputationPanel extends Panel {
        private static final long serialVersionUID = 1L;

        public ComputationPanel(String id) {
            super(id);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            try {
                Thread.sleep(3000); // simulate work
            } catch (InterruptedException e) {
            }
        }
    }

}
