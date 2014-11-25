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
package org.cdlflex.ui.model;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.markup.css.ICssClassNameProvider;

/**
 * Wraps an ICssClassNameProvider to return its cssClassName via {@code #getObject} as String.
 * 
 * @param <T> The concrete ICssClassNameProvider type
 */
public class CssClassNameProvidingModel<T extends ICssClassNameProvider & Serializable> implements
        IGenericWrapModel<String, T> {
    private static final long serialVersionUID = 1L;

    private IModel<T> wrapped;

    public CssClassNameProvidingModel(T cssClassNameProvider) {
        this(Model.of(cssClassNameProvider));
    }

    public CssClassNameProvidingModel(IModel<T> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public IModel<T> getWrappedModel() {
        return wrapped;
    }

    @Override
    public String getObject() {
        return getWrappedModel().getObject().getCssClassName();
    }

    @Override
    public void setObject(String object) {
    }

    @Override
    public void detach() {
        wrapped.detach();
    }

}
