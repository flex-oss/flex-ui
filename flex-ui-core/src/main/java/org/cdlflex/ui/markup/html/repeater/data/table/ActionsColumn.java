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
package org.cdlflex.ui.markup.html.repeater.data.table;

import static org.rauschig.wicketjs.JsExpression.THIS;
import static org.rauschig.wicketjs.jquery.JQuery.$;

import java.util.List;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.cdlflex.ui.markup.html.button.DropDownButton;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsStatements;
import org.rauschig.wicketjs.behavior.JsBehavior;

/**
 * A Column that provides a menu of actions for a given row model.
 * 
 * @param <T> the type of the object that will be rendered in this column's cells
 * @param <S> the type of the sort property
 */
public abstract class ActionsColumn<T, S> extends DataTableColumn<T, S> {

    private static final long serialVersionUID = 1L;

    private IModel<String> buttonDisplayModel;
    private IModel<IconType> buttonIconTypeModel;

    public ActionsColumn(IModel<String> columnDisplayModel) {
        this(columnDisplayModel, Model.of(""), (IconType) null);
    }

    public ActionsColumn(IModel<String> columnDisplayModel, IModel<String> buttonDisplayModel) {
        this(columnDisplayModel, buttonDisplayModel, (IconType) null);
    }

    protected ActionsColumn(IModel<String> displayModel, IconType buttonIconType) {
        this(displayModel, Model.of(""), Model.of(buttonIconType));
    }

    protected ActionsColumn(IModel<String> displayModel, IModel<String> buttonDisplayModel, IconType buttonIconType) {
        this(displayModel, buttonDisplayModel, Model.of(buttonIconType));
    }

    public ActionsColumn(IModel<String> displayModel, IModel<String> buttonDisplayModel,
            IModel<IconType> buttonIconTypeModel) {
        super(displayModel);
        this.buttonDisplayModel = buttonDisplayModel;
        this.buttonIconTypeModel = buttonIconTypeModel;
    }

    @Override
    public String getCssClass() {
        return "action-column";
    }

    @Override
    public boolean isSortable() {
        return false;
    }

    public IModel<String> getButtonDisplayModel() {
        return buttonDisplayModel;
    }

    public IModel<IconType> getButtonIconTypeModel() {
        return buttonIconTypeModel;
    }

    @Override
    protected void populateItem(DataTableCell<T, S> cell, String componentId) {
        DropDownButton dropDownButton = newDropDownButton(componentId, cell.getRowModel(), cell);

        dropDownButton.setIconType(getButtonIconTypeModel().getObject());

        cell.add(dropDownButton);
    }

    /**
     * Creates the drop-down button that stores the actions.
     * 
     * @param componentId the component id for the button
     * @param rowModel the model of the row the button is created for
     * @param cell the actual cell item
     * @return a new drop down button
     */
    protected DropDownButton newDropDownButton(String componentId, final IModel<T> rowModel,
        final DataTableCell<T, S> cell) {
        return new DropDownButton(componentId, getButtonDisplayModel(), getButtonIconTypeModel()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected List<AbstractLink> newSubMenuButtons(String id) {
                return createActions(id, rowModel, cell);
            }

            @Override
            protected void onInitialize() {
                super.onInitialize();
                onDropDownButtonInitialize(this, rowModel);
            }
        };
    }

    /**
     * Called by {@code onInitialize} of the DropDownButton for each row.
     * 
     * @param button the button being initialized
     * @param model the row model
     */
    protected void onDropDownButtonInitialize(DropDownButton button, IModel<T> model) {
        // hook
    }

    /**
     * Returns a list of {@code AbstractLink} objects that can be selected from the drop-down menu.
     * 
     * @param componentId the component id the links should all have
     * @param rowModel the model of the row the button is created for
     * @param cell the actual cell item
     * @return a list of Links
     */
    protected abstract List<AbstractLink> createActions(String componentId, final IModel<T> rowModel,
        DataTableCell<T, S> cell);

    /**
     * Behavior that is used in combination with ActionColumn that should have toggled visibility on mouse hover.
     */
    public static class ToggleOnHoverBehavior extends JsBehavior {

        private static final long serialVersionUID = 1L;

        @Override
        protected IJavaScript domReadyJs() {
            return new JsStatements($("tr .action-column .dropdown", this).addClass("invisible"), $("tr", this).on(
                    "mouseenter mouseleave", $(".action-column .dropdown", THIS).toggleClass("invisible")));
        }
    }

}
