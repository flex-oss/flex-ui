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
package org.cdlflex.ui.markup.html.badge.notifybar;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.markup.html.badge.BadgeListItem;

/**
 * Wraps an INotifyBarComponent to be used as a BadgeListItem.
 */
public class BadgeListItemAdapter extends BadgeListItem {

    private static final long serialVersionUID = 1L;

    private INotifyBarComponent component;

    public BadgeListItemAdapter(final INotifyBarComponent component) {
        super();
        this.component = component;
        setBadgeModel(new BadgeModel());
        setLabelModel(new LabelModel());
    }

    @Override
    public AbstractLink createLink(String id) {
        if (component instanceof INavigatableNotifyBarComponent) {
            return ((INavigatableNotifyBarComponent) component).createLink(id);
        } else {
            return super.createLink(id);
        }
    }

    /**
     * Readonly Model that reads the badge display model from the INotifyBarComponent.
     */
    private class BadgeModel extends AbstractReadOnlyModel<Object> {
        private static final long serialVersionUID = 1L;

        @Override
        public Object getObject() {
            Object value = component.getValue();
            return (value instanceof IModel) ? ((IModel) value).getObject() : value;
        }
    }

    /**
     * Readonly model that reads the label display model from the INotifyBarComponent.
     */
    private class LabelModel extends AbstractReadOnlyModel<String> {
        private static final long serialVersionUID = 1L;

        @Override
        public String getObject() {
            return component.getLabel();
        }
    }
}
