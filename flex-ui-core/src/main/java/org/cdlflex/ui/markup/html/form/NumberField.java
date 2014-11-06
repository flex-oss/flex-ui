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
package org.cdlflex.ui.markup.html.form;

import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.IModel;

/**
 * NumberTextField implementation that provides some custom convenience functionality.
 * 
 * @param <N> The specific number type
 */
public class NumberField<N extends Number & Comparable<N>> extends NumberTextField<N> {
    private static final long serialVersionUID = 1L;

    public NumberField(String id) {
        super(id);
    }

    public NumberField(String id, IModel<N> model) {
        super(id, model);
    }

    public NumberField(String id, Class<N> type) {
        super(id, null, type);
    }

    public NumberField(String id, IModel<N> model, Class<N> type) {
        super(id, model, type);
    }
}
