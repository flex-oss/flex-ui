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
package org.cdlflex.ui.markup.html.panel;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.CollapseBehavior;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.model.ToStringModel;

/**
 * A Panel that displays the stack trace of a Throwable in a collapsible panel.
 */
public class StackTracePanel extends GenericPanel<Throwable> {

    private static final long serialVersionUID = 1L;

    public StackTracePanel(String id, IModel<Throwable> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new CssClassNameAppender("stacktrace-panel", "panel", "panel-danger"));

        WebMarkupContainer panelBody = new WebMarkupContainer("panel-body");
        WebMarkupContainer collapseButton = new WebMarkupContainer("collapseButton");

        collapseButton.add(new CollapseBehavior(panelBody));

        panelBody.add(new MultiLineLabel("stackTrace", new ToStringModel<>(getModel(), this::convertStackTrace)));

        add(panelBody, collapseButton);
    }

    /**
     * Returns the stack trace of the cause as string.
     *
     * @return the stack trace as string
     */
    public String convertStackTrace(Throwable cause) {
        StringWriter str = new StringWriter();
        cause.printStackTrace(new PrintWriter(str));
        return str.toString();
    }
}
