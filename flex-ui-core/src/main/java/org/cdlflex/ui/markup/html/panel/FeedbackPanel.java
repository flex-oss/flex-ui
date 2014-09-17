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

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

/**
 * Flex UI FeedbackPanel that displays messages as a list of bootstrap alerts with the respective alert level.
 */
public class FeedbackPanel extends org.apache.wicket.markup.html.panel.FeedbackPanel {
    private static final long serialVersionUID = 1L;

    public FeedbackPanel(String id) {
        super(id);
    }

    public FeedbackPanel(String id, IFeedbackMessageFilter filter) {
        super(id, filter);
    }

    @Override
    protected String getCSSClass(FeedbackMessage message) {
        switch (message.getLevel()) {
            case INFO:
                return "alert-info";
            case SUCCESS:
                return "alert-success";
            case WARNING:
                return "alert-warning";
            case ERROR:
            case FATAL:
                return "alert-danger";
            case DEBUG:
            case UNDEFINED:
            default:
                return "alert-primary";
        }
    }
}
