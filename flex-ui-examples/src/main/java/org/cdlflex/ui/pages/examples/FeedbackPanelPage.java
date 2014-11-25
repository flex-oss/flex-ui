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
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.cdlflex.ui.markup.html.panel.FeedbackPanel;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * FeedbackPanelPage.
 */
public class FeedbackPanelPage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    private boolean stripWicketTags;
    private FeedbackPanel feedbackPanel;

    public FeedbackPanelPage() {
        stripWicketTags = Application.get().getMarkupSettings().getStripWicketTags();

        add(feedbackPanel = newFeedbackPanel("feedback"));
        feedbackPanel.setOutputMarkupId(true);
        add(new Buttons("buttons"));
    }

    protected FeedbackPanel newFeedbackPanel(String id) {
        return new FeedbackPanel(id) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onBeforeRender() {
                super.onBeforeRender();
                Application.get().getMarkupSettings().setStripWicketTags(true);
            }

            @Override
            protected void onAfterRender() {
                super.onAfterRender();
                Application.get().getMarkupSettings().setStripWicketTags(stripWicketTags);
            }
        };
    }

    public class Buttons extends WebMarkupContainer {
        private static final long serialVersionUID = 1L;

        public Buttons(String id) {
            super(id);

            add(new AjaxLink("infoTrigger") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick(AjaxRequestTarget target) {
                    info("Heads up! This alert needs your attention, but it's not super important.");
                    target.add(feedbackPanel);
                }
            });
            add(new AjaxLink("successTrigger") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick(AjaxRequestTarget target) {
                    success("Well done! You successfully read this important alert message.");
                    target.add(feedbackPanel);
                }
            });
            add(new AjaxLink("warnTrigger") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick(AjaxRequestTarget target) {
                    warn("Warning! Better check yourself, you're not looking too good.");
                    target.add(feedbackPanel);
                }
            });
            add(new AjaxLink("errorTrigger") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick(AjaxRequestTarget target) {
                    error("Oh snap! Change a few things up and try submitting again.");
                    target.add(feedbackPanel);
                }
            });
            add(new AjaxLink("allTrigger") {
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick(AjaxRequestTarget target) {
                    info("Heads up! This alert needs your attention, but it's not super important.");
                    success("Well done! You successfully read this important alert message.");
                    warn("Warning! Better check yourself, you're not looking too good.");
                    error("Oh snap! Change a few things up and try submitting again.");
                    target.add(feedbackPanel);
                }
            });

        }

    }
}
