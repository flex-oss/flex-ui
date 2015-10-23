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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;
import org.cdlflex.ui.ajax.behavior.progress.ProgressBarUpdateBehavior;
import org.cdlflex.ui.markup.html.progress.ProgressBar;
import org.cdlflex.ui.model.TransformingModel;
import org.cdlflex.ui.pages.ExamplePage;

public class ProgressBarPage extends ExamplePage {

    private static final long serialVersionUID = 1L;

    public ProgressBarPage() {
        add(new ProgressBar("basic").setValue(23.0));
        add(new ProgressBar("with-label", new Model<>(52.0), new Model<>("52%")));
        add(new ProgressBar("striped").setValue(32.0).setStriped(true));
        add(new ProgressBar("animated").setValue(84.0).setText("Shiny").setStriped(true).setAnimated(true));
        add(newAjaxProgressBar("ajax"));
    }

    private ProgressBar newAjaxProgressBar(String id) {
        IModel<Double> valueModel = new Model<>(0.0);
        IModel<String> textModel = new TransformingModel<>(valueModel, d -> String.format("%.0f%%", d));

        ProgressBar bar = new ProgressBar(id, valueModel, textModel);

        bar.add(new ProgressBarUpdateBehavior(Duration.milliseconds(100)) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onAfterTimer(AjaxRequestTarget target) {
                // simulate progress
                IModel<Double> valueModel = getProgressBar().getValueModel();
                valueModel.setObject(valueModel.getObject() + 1);
            }
        });

        return bar;
    }

}
