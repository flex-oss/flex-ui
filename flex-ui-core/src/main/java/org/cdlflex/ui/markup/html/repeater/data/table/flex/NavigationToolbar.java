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
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * NavigationToolbar.
 */
public class NavigationToolbar extends AbstractToolbar {

    private static final long serialVersionUID = 1L;

    public NavigationToolbar(DataTable<?, ?> table) {
        super(table);

        WebMarkupContainer span = new WebMarkupContainer("span");
        add(span);

        span.add(newPagingNavigator("navigator", table));
        span.add(newNavigatorLabel("navigatorLabel", table).setVisible(false));
    }

    /**
     * Factory method used to create the paging navigator that will be used by the DataTable.
     * 
     * @param navigatorId component id the navigator should be created with
     * @param table dataview used by datatable
     * @return paging navigator that will be used to navigate the data table
     */
    protected PagingNavigator newPagingNavigator(final String navigatorId, final DataTable<?, ?> table) {
        return new PagingNavigator(navigatorId, table);
    }

    /**
     * Factory method used to create the navigator label that will be used by the DataTable.
     * 
     * @param navigatorId component id navigator label should be created with
     * @param table dataview used by datatable
     * @return navigator label that will be used to navigate the data table
     */
    protected WebComponent newNavigatorLabel(final String navigatorId, final DataTable<?, ?> table) {
        return new NavigatorLabel(navigatorId, table);
    }

    @Override
    public boolean isVisible() {
        return getTable().getPageCount() > 1;
    }
}
