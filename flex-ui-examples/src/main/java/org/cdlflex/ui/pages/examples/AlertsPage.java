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
import org.apache.wicket.model.Model;
import org.cdlflex.ui.markup.css.Alerts.Level;
import org.cdlflex.ui.markup.html.alert.Alert;
import org.cdlflex.ui.markup.html.panel.AlertPanel;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * AlertPanelPage.
 */
public class AlertsPage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    private boolean stripTags;

    public AlertsPage() {
        stripTags = Application.get().getMarkupSettings().getStripWicketTags();

        add(new Alert("alert-01"));
        add(new Alert("alert-02", Level.SUCCESS));
        add(new Alert("alert-03", Level.WARNING).setFade(true));
        add(new Alert("alert-04").setLevel(Level.DANGER).setDismissible(false));
        add(new Alert("alert-05").setLevel(Level.DANGER).setDismissible(true));

        add(new AlertPanel("alert-panel-01", Model.of("Default alert")));
        add(new AlertPanel("alert-panel-02", Model.of("Dismissible success alert"), Level.SUCCESS));
        add(new AlertPanel("alert-panel-03", Model.of("Dismissible warn alert that fades on dismiss"), Level.WARNING)
                .setFade(true));
        add(new AlertPanel("alert-panel-04", Model.of("Non-dismissible danger alert")).setLevel(Level.DANGER)
                .setDismissible(false));
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
