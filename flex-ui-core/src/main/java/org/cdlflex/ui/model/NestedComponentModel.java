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

import java.util.Objects;

import org.apache.wicket.Component;
import org.apache.wicket.model.ComponentModel;
import org.apache.wicket.model.IModel;

/**
 * A ComponentModel that delegates {@link IModel#setObject(Object)} and {@link IModel#getObject()} calls to the parent
 * of the component it is attached to.
 * 
 * This is an alternative to using {@code add(new SomeModel("someId", getModel()}, in situations where it is not clear
 * whether {@code #getModel()} would actually return something yet.
 *
 * @param <T> The model object type
 */
public class NestedComponentModel<T> extends ComponentModel<T> {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    @Override
    protected T getObject(Component component) {
        return (T) requireParentComponent(component).getDefaultModelObject();
    }

    @Override
    protected void setObject(Component component, T object) {
        requireParentComponent(component).setDefaultModelObject(object);
    }

    private Component requireParentComponent(Component component) {
        return Objects.requireNonNull(component.getParent(), () -> "Parent Component is null");
    }
}
