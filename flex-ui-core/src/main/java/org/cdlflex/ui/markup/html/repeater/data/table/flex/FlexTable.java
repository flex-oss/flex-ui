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

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;
import org.cdlflex.ui.markup.html.repeater.data.table.DataTable;
import org.cdlflex.ui.markup.html.repeater.data.table.ITableSkeleton;

/**
 * A DataTable implementation that is optimized for the cdlflex EngineeringCockpit and its panels.
 * 
 * The main difference to Wicket's DataTable is how the toolbars are added to the markup. Top and bottom toolbars are no
 * longer added to the <code>&lt;thead&gt;</code> and <code>&lt;tfoot&gt;</code> containers, but above and below the
 * table in bootstrap row divs.
 * 
 * There is an additional header bar which is added to the <code>&lt;thead&gt;</code> container.
 *
 * @param <T> The model object type
 * @param <S> the type of the sorting parameter
 */
public class FlexTable<T, S> extends DataTable<T, S> {

    private static final long serialVersionUID = 1L;

    private ToolbarsContainer tableHeaders;

    /**
     * Constructor.
     * 
     * @param id component id
     * @param skeleton a table skeleton
     */
    public FlexTable(String id, ITableSkeleton<T, S> skeleton) {
        super(id, skeleton);
        initHeaderContainer();
    }

    /**
     * Constructor.
     * 
     * @param id component id
     * @param columns list of columns
     * @param dataProvider data provider
     * @param rows number of rows per page
     */
    public FlexTable(String id, List<? extends IColumn<T, S>> columns, IDataProvider<T> dataProvider, long rows) {
        super(id, columns, dataProvider, rows);
        initHeaderContainer();
    }

    /**
     * Add the given toolbar as table header row.
     * 
     * @param toolbar the toolbar to add
     */
    public void addTableHeader(final AbstractToolbar toolbar) {
        addToolbar(toolbar, tableHeaders);
    }

    /**
     * Adds the given Toolbar to the given ToolbarsContainer.
     * 
     * @param toolbar the toolbar to add
     * @param container the container to add the toolbar to
     */
    protected void addToolbar(final AbstractToolbar toolbar, final ToolbarsContainer container) {
        Args.notNull(toolbar, "toolbar");

        container.getRepeatingView().add(toolbar);
    }

    private void initHeaderContainer() {
        tableHeaders = new ToolbarsContainer("tableHeaders");
        add(tableHeaders);
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.setName("div"); // this ugly hack is brought to you by WICKET-5534
    }

    /**
     * This class acts as a repeater that will contain the toolbar. It makes sure that the table row group (e.g. thead)
     * tags are only visible when they contain rows in accordance with the HTML specification.

     * Copied from Wicket's {@code DataTable} as it is private there.
     */
    protected static final class ToolbarsContainer extends WebMarkupContainer {

        private static final long serialVersionUID = 1L;

        private final RepeatingView toolbars;

        private ToolbarsContainer(final String id) {
            super(id);
            toolbars = new RepeatingView("toolbars");
            add(toolbars);
        }

        /**
         * Returns the repeating view that holds the toolbars.
         * 
         * @return toolbar container repeating view
         */
        public RepeatingView getRepeatingView() {
            return toolbars;
        }

        @Override
        public void onConfigure() {
            super.onConfigure();

            toolbars.configure();

            Boolean visible = toolbars.visitChildren(new IVisitor<Component, Boolean>() {
                @Override
                public void component(Component object, IVisit<Boolean> visit) {
                    object.configure();
                    if (object.isVisible()) {
                        visit.stop(Boolean.TRUE);
                    } else {
                        visit.dontGoDeeper();
                    }
                }
            });
            if (visible == null) {
                visible = false;
            }
            setVisible(visible);
        }
    }

}
