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
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.IComponentAssignedModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;
import org.cdlflex.ui.util.function.SerializableFunction;

/**
 * Used like a ComponentPropertyModel, this additionally transforms what the component property model would return using
 * a function, like a TransformingModel.
 *
 * @param <T> the source model object type
 * @param <R> the target model object type
 */
public class ComponentPropertyTransformingModel<T, R> extends AbstractReadOnlyModel<R>
        implements IComponentAssignedModel<R> {

    private static final long serialVersionUID = 1L;

    private String expression;
    private SerializableFunction<T, R> transformer;

    public ComponentPropertyTransformingModel(String expression, SerializableFunction<T, R> transformer) {
        this.expression = Objects.requireNonNull(expression);
        this.transformer = Objects.requireNonNull(transformer);
    }

    public String getExpression() {
        return expression;
    }

    /**
     * Returns the transformer function.
     *
     * @return a function
     */
    public SerializableFunction<T, R> getTransformer() {
        return transformer;
    }

    @Override
    public R getObject() {
        throw new IllegalStateException("Wrapper should have been called");
    }

    @Override
    public IWrapModel<R> wrapOnAssignment(Component component) {
        // reuses the functionality of ComponentPropertyModel, although only minimally
        return new AssignmentWrapper(new ComponentPropertyModel<T>(expression).wrapOnAssignment(component));
    }

    private class AssignmentWrapper extends TransformingModel<T, R>implements IWrapModel<R> {

        private static final long serialVersionUID = 1L;

        public AssignmentWrapper(IModel<T> model) {
            super(model, transformer);
        }

        @Override
        public IModel<?> getWrappedModel() {
            return ComponentPropertyTransformingModel.this;
        }

        @Override
        public void detach() {
            super.detach();
            ComponentPropertyTransformingModel.this.detach();
        }
    }
}
