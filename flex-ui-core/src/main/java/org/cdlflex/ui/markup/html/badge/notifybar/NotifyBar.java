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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.html.badge.BadgeList;
import org.cdlflex.ui.markup.html.badge.BadgeListItem;
import org.cdlflex.ui.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Component that dynamically populates a {@link org.cdlflex.ui.markup.html.badge.BadgeList} with components provided
 * by a {@link org.cdlflex.ui.markup.html.badge.notifybar.INotifyBarComponentProvider}.
 * <p/>
 * Its purpose is to bridge the gap between the {@link BadgeList} and OSGi service-based design. In an OSGi use-case,
 * one would implement an INotifyBarComponentProvider as a service aggregator, which allows INotifyBarComponents to be
 * provided from different bundles.
 */
public class NotifyBar extends Panel {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(NotifyBar.class);

    private INotifyBarComponentProvider componentProvider;

    public NotifyBar(String id, INotifyBarComponentProvider componentProvider) {
        super(id);
        this.componentProvider = componentProvider;
    }

    public INotifyBarComponentProvider getComponentProvider() {
        return componentProvider;
    }

    public void setComponentProvider(INotifyBarComponentProvider componentProvider) {
        this.componentProvider = componentProvider;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(newBadgeList("badge-list"));
    }

    /**
     * Factory method that creates the BadgeList to be displayed.
     * 
     * @param id the component id
     * @return a new BadgeList
     */
    protected Component newBadgeList(String id) {
        return new BadgeList(id, new BadgeItemProvidingModel());
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        CssClassNameAppender.append(tag, "notifybar");
    }

    /**
     * Model that provides BadgeListItems from the INotifyBarComponentProvider of this NotifyBar instance.
     */
    protected class BadgeItemProvidingModel extends AbstractReadOnlyModel<List<? extends BadgeListItem>> {
        private static final long serialVersionUID = 1L;

        @Override
        public List<? extends BadgeListItem> getObject() {
            INotifyBarComponentProvider provider = getComponentProvider();
            if (provider == null) {
                LOG.warn("INotifyBarComponentProvider is null, can not populate NotifyBar");
                return Collections.emptyList();
            }

            List<INotifyBarComponent> components = new ArrayList<>(provider.getComponents());
            Collections.retain(components, new VisibilityFilter()); // remove all components that are not visible
            Collections.sort(components, new ComponentPriorityComparator()); // sort them by priority
            return Collections.map(components, new BadgeListItemFactory()); // create the BadgeListItems
        }
    }

    /**
     * A Comparator that compares INotifyBarComponent elements based on their priority.
     */
    private static class ComponentPriorityComparator implements Comparator<INotifyBarComponent> {
        @Override
        public int compare(INotifyBarComponent o1, INotifyBarComponent o2) {
            return Integer.compare(o1.getPriority(), o2.getPriority());
        }
    }

    /**
     * Callback that creates BadgeListItemAdapter instances from INotifyBarComponent.
     */
    private static class BadgeListItemFactory implements Collections.Callback<INotifyBarComponent, BadgeListItem> {
        @Override
        public BadgeListItem call(INotifyBarComponent component) {
            return new BadgeListItemAdapter(component);
        }
    }

    /**
     * Predicate that delegates access to {@link INotifyBarComponent#isVisible()}.
     */
    private static class VisibilityFilter implements Collections.Predicate<INotifyBarComponent> {
        @Override
        public Boolean call(INotifyBarComponent object) {
            return object.isVisible();
        }
    }
}
