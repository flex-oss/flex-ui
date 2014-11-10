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
package org.cdlflex.ui.markup.html.repeater.data.table.flex;

import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 * A toolbar that displays a message.
 */
public class MessageToolbar extends AbstractToolbar {

    private static final long serialVersionUID = 1L;

    private IModel<String> messageModel;

    /**
     * Constructor.
     * 
     * @param table data table this toolbar will be attached to
     * @param messageModel model that will be used to display the "no records found" message
     */
    public MessageToolbar(DataTable<?, ?> table, IModel<String> messageModel) {
        super(table);

        this.messageModel = messageModel;

        add(newMessageLabel("msg"));
    }

    /**
     * Returns the model used to display the message.
     * 
     * @return the display model for the message
     */
    public IModel<String> getMessageModel() {
        return messageModel;
    }

    /**
     * Creates the Label that displays the message.
     * 
     * @param id the component id
     * @return a new Label
     */
    protected Label newMessageLabel(String id) {
        return new Label(id, getMessageModel());
    }
}
