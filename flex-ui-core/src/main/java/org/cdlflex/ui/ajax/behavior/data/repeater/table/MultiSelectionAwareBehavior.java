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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.repeater.Item;
import org.rauschig.wicketjs.behavior.WidgetBehavior;

/**
 * ToggleAwareBehavior enables multiple rows to be selected.
 *
 * @param <T> Model object type
 */
public abstract class MultiSelectionAwareBehavior<T> extends ToggleAwareBehavior<T> {

    private static final long serialVersionUID = 1L;

    private List<Item<T>> selection = new ArrayList<>();

    @Override
    protected Behavior newWidgetBehavior() {
        return new WidgetBehavior("multiselectable");
    }

    @Override
    protected final void onSelect(Item<T> item, AjaxRequestTarget target) {
        if (!selection.contains(item)) {
            selection.add(item);
            onSelectionChanged(selection, target);
        }
    }

    @Override
    protected void onDeselect(Item<T> item, AjaxRequestTarget target) {
        if (selection.contains(item)) {
            selection.remove(item);
            onSelectionChanged(selection, target);
        }
    }

    /**
     * Returns the currently selected items.
     *
     * @return the list of selected items
     */
    public List<Item<T>> getSelection() {
        return selection;
    }

    /**
     * Called when the selection changes, i.e. when onSelect or onDeselect is called.
     * 
     * @param selection the current selection
     * @param target the ajax request target
     */
    protected abstract void onSelectionChanged(List<Item<T>> selection, AjaxRequestTarget target);
}
