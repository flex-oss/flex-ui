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
package org.cdlflex.ui.ajax.behavior.data.repeater.table;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;
import org.cdlflex.ui.behavior.FrontendDependencyBehavior;
import org.cdlflex.ui.markup.html.repeater.data.table.DataTable;
import org.cdlflex.ui.markup.html.repeater.data.table.IRowCallback;
import org.cdlflex.ui.resource.JQueryUiWidgetFactoryResourceReference;
import org.cdlflex.ui.util.ResourceReferences;
import org.rauschig.wicketjs.ajax.JsAjaxEventBehavior;
import org.rauschig.wicketjs.behavior.WidgetBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AJAX Behavior for a DataTable that enables rows to be clicked and selected.
 * 
 * @param <T> Model object type
 */
public abstract class SelectionAwareBehavior<T> extends Behavior implements IRowCallback<T> {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(SelectionAwareBehavior.class);

    @SuppressWarnings("unchecked")
    @Override
    public void bind(Component component) {
        if (!(component instanceof DataTable)) {
            LOG.warn("Can not add behavior to " + component + ". Not a DataTable");
            return;
        }

        ((DataTable) component).getRowCallbacks().add(this);

        component.add(new FrontendDependencyBehavior(new SelectionAwareWidgetReference()));
        component.add(newWidgetBehavior());
    }

    @Override
    public void call(final Item<T> item) {
        item.setOutputMarkupId(true);

        item.add(new JsAjaxEventBehavior("select") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                SelectionAwareBehavior.this.onSelect(item, target);
            }
        });
    }

    /**
     * Creates the widget behavior that is attached to the component. Sub-classes can override this to add their custom
     * options.
     * 
     * @return a new WidgetBehavior
     */
    protected Behavior newWidgetBehavior() {
        return new WidgetBehavior("selectable");
    }

    /**
     * Called when a row was selected, specifically when the DOM event <code>select</code> is fired on the given item.
     * 
     * @param item the row item selected
     * @param target ajax request target
     */
    protected abstract void onSelect(Item<T> item, AjaxRequestTarget target);

    private static class SelectionAwareWidgetReference extends JavaScriptResourceReference {
        private static final long serialVersionUID = 1L;

        public SelectionAwareWidgetReference() {
            super(SelectionAwareBehavior.class, "SelectionAwareBehavior.js");
        }

        @Override
        public Iterable<? extends HeaderItem> getDependencies() {
            return ResourceReferences.join(super.getDependencies(), JQueryResourceReference.get(),
                    JQueryUiWidgetFactoryResourceReference.get());
        }
    }

}
