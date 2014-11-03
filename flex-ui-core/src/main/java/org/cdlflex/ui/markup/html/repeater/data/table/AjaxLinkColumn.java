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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.ajax.DelegatingJsAjaxLink;
import org.rauschig.wicketjs.ajax.IAjaxCallAware;

/**
 * An AbstractLinkColumn implementation that uses a JsAjaxLink from which all IAjaxCallAware calls are delegated to this
 * column instance.
 * 
 * @param <T> The model object type
 * @param <S> the type of the sorting parameter
 */
public abstract class AjaxLinkColumn<T, S> extends AbstractLinkColumn<T, S> implements IAjaxCallAware {
    private static final long serialVersionUID = 1L;

    public AjaxLinkColumn() {
        this(Model.of(""));
    }

    public AjaxLinkColumn(IModel<String> displayModel) {
        super(displayModel);
    }

    @Override
    protected void onBind(DataTable<T, S> dataTable) {
        super.onBind(dataTable);

        dataTable.getRowCallbacks().add(new IRowCallback<T>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void call(Item<T> rowItem) {
                rowItem.setOutputMarkupId(true);
            }
        });
    }

    @Override
    public AbstractLink newLink(String id, final DataTableCell<T, S> cell) {
        return new DelegatingJsAjaxLink<T>(id, cell.getRowModel(), this) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                AjaxLinkColumn.this.onClick(target, getModel(), cell);
            }
        }.setBody(getBodyModel(cell.getRowModel(), cell));
    }

    @Override
    public IJavaScript onTrigger() {
        return null;
    }

    @Override
    public IJavaScript onBefore() {
        return null;
    }

    @Override
    public IJavaScript onSuccess() {
        return null;
    }

    @Override
    public IJavaScript onFail() {
        return null;
    }

    @Override
    public IJavaScript precondition() {
        return null;
    }

    @Override
    public IJavaScript onAfter() {
        return null;
    }

    @Override
    public IJavaScript onComplete() {
        return null;
    }

    /**
     * Template method that returns the body model for the crated link.
     *
     * @param model the row model
     * @param cell the cell
     * @return a string display model
     */
    protected IModel<String> getBodyModel(IModel<T> model, DataTableCell<T, S> cell) {
        return null;
    }

    /**
     * Triggered when the link is clicked.
     * 
     * @param target the ajax request target
     * @param model the row model, can also be accessed via cell
     * @param cell the cell item in which the button resides
     */
    protected abstract void onClick(AjaxRequestTarget target, IModel<T> model, DataTableCell<T, S> cell);

}
