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
