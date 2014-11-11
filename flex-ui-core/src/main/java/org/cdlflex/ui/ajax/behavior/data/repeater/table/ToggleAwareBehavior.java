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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.repeater.Item;
import org.rauschig.wicketjs.ajax.JsAjaxEventBehavior;
import org.rauschig.wicketjs.behavior.WidgetBehavior;

/**
 * SelectionAwareBehavior that allows to de-select already selected rows.
 * 
 * @param <T> Model object type
 */
public abstract class ToggleAwareBehavior<T> extends SelectionAwareBehavior<T> {
    private static final long serialVersionUID = 1L;

    @Override
    public void call(final Item<T> item) {
        super.call(item);

        item.add(new JsAjaxEventBehavior("deselect") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onEvent(AjaxRequestTarget target) {
                ToggleAwareBehavior.this.onDeselect(item, target);
            }
        });
    }

    @Override
    protected Behavior newWidgetBehavior() {
        return new WidgetBehavior("toggleable");
    }

    /**
     * Called when a selected row was unselected, specifically when the DOM event <code>unselect</code> is fired on the
     * given item.
     *
     * @param item the row item that was previously selected
     * @param target ajax request target
     */
    protected abstract void onDeselect(Item<T> item, AjaxRequestTarget target);
}
