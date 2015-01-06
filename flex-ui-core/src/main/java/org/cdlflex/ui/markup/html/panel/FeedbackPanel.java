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

import static org.apache.wicket.feedback.FeedbackMessage.DEBUG;
import static org.apache.wicket.feedback.FeedbackMessage.ERROR;
import static org.apache.wicket.feedback.FeedbackMessage.FATAL;
import static org.apache.wicket.feedback.FeedbackMessage.INFO;
import static org.apache.wicket.feedback.FeedbackMessage.SUCCESS;
import static org.apache.wicket.feedback.FeedbackMessage.UNDEFINED;
import static org.apache.wicket.feedback.FeedbackMessage.WARNING;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.cdlflex.ui.markup.css.Alerts.Level;
import org.cdlflex.ui.markup.html.alert.Alert;

/**
 * Engineering Cockpit FeedbackPanel that displays messages as a list of bootstrap alerts with the respective alert
 * level.
 */
public class FeedbackPanel extends org.apache.wicket.markup.html.panel.FeedbackPanel {
    private static final long serialVersionUID = 1L;

    private boolean dismissible;

    public FeedbackPanel(String id) {
        this(id, true);
    }

    public FeedbackPanel(String id, boolean dismissible) {
        this(id, null, dismissible);
    }

    public FeedbackPanel(String id, IFeedbackMessageFilter filter) {
        this(id, filter, true);
    }

    public FeedbackPanel(String id, IFeedbackMessageFilter filter, boolean dismissible) {
        super(id, filter);
        this.dismissible = dismissible;
    }

    public boolean isDismissible() {
        return dismissible;
    }

    /**
     * Sets whether the alert panels are dismissible (can be closed by the user).
     *
     * @param isDismissible the flag
     * @return this for chaining
     */
    public FeedbackPanel setDismissible(boolean isDismissible) {
        this.dismissible = isDismissible;
        return this;
    }

    @Override
    protected Component newMessageDisplayComponent(String id, FeedbackMessage message) {
        Alert alert = new Alert("message", getAlertLevel(message.getLevel())) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isDismissible() {
                return FeedbackPanel.this.isDismissible();
            }
        };
        alert.add(super.newMessageDisplayComponent("label", message));
        return alert;
    }

    /**
     * Maps the FeedbackMessage alert level to Level enum value.
     * 
     * @param level the FeedbackMessage level
     * @return the Alerts.Level enum value
     */
    protected Level getAlertLevel(int level) {
        switch (level) {
            case INFO:
                return Level.INFO;
            case SUCCESS:
                return Level.SUCCESS;
            case WARNING:
                return Level.WARNING;
            case ERROR:
            case FATAL:
                return Level.DANGER;
            case DEBUG:
            case UNDEFINED:
            default:
                return Level.UNKNOWN;
        }
    }

}
