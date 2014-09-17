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
package org.cdlflex.ui.ajax;

import org.apache.wicket.model.IModel;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsReturn;
import org.rauschig.wicketjs.ajax.JsAjaxLink;

/**
 * A JsAjaxLink that opens a confirm dialog before the ajax request is executed.
 *
 * @param <T> Model object type
 */
public abstract class AjaxConfirmLink<T> extends JsAjaxLink<T> {
    private static final long serialVersionUID = 1L;

    public AjaxConfirmLink(String id) {
        super(id);
    }

    public AjaxConfirmLink(String id, IModel<T> model) {
        super(id, model);
    }

    @Override
    public IJavaScript precondition() {
        return new JsReturn(new JsCall("confirm", getConfirmMessage()));
    }

    /**
     * Returns the message that should be used in the confirm dialog.
     *
     * @return string shown in the confirm dialog
     */
    protected abstract String getConfirmMessage();
}
