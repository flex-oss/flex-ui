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
import org.apache.wicket.markup.repeater.Item;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.html.repeater.data.table.DataTable;
import org.cdlflex.ui.markup.html.repeater.data.table.IRowCallback;
import org.rauschig.wicketjs.ajax.JsAjaxEventBehavior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AJAX Behavior for a DataTable that enables rows to be clicked.
 *
 * @param <T> Model object type
 */
public abstract class ClickAwareBehavior<T> extends Behavior implements IRowCallback<T> {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(ClickAwareBehavior.class);

    @SuppressWarnings("unchecked")
    @Override
    public void bind(Component component) {
        if (!(component instanceof DataTable)) {
            LOG.warn("Can not add behavior to " + component + ". Not a DataTable");
            return;
        }

        ((DataTable) component).getRowCallbacks().add(this);
        component.add(new CssClassNameAppender("clickable"));
    }

    @Override
    public void call(final Item<T> item) {
        item.setOutputMarkupId(true);

        item.add(new JsAjaxEventBehavior("click") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                ClickAwareBehavior.this.onClick(item, target);
            }
        });
    }

    /**
     * Called when the row item is clicked.
     *
     * @param item the row item being clicked
     * @param target the ajax request target
     */
    protected abstract void onClick(Item<T> item, AjaxRequestTarget target);

}
