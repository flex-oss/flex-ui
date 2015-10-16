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
package org.cdlflex.ui.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.cdlflex.ui.model.ReadOnlyModel;

/**
 * Implements <a href="http://getbootstrap.com/javascript/#collapse">bootstrap's collapse behavior</a> using
 * data-attributes. The CollapseBehavior is attached to the component that triggers the toggling, i.e. a button or link.
 */
public class CollapseBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    private Component target;
    private CssClassNameAppender targetBehavior;

    private boolean expanded;

    /**
     * Construct. The target is collapsed by default.
     *
     * @param target the target that should be toggled
     */
    public CollapseBehavior(Component target) {
        this(target, false);
    }

    /**
     * Construct.
     *
     * @param target the target that should be toggled
     * @param expanded flag whether the target is expanded by default
     */
    public CollapseBehavior(Component target, boolean expanded) {
        this.target = target;
        this.expanded = expanded;
        this.targetBehavior =
            new CssClassNameAppender(new ReadOnlyModel<>(() -> (isExpanded()) ? "collapse in" : "collapse"));
    }

    @Override
    public void onComponentTag(Component component, ComponentTag tag) {
        String targetId = getTarget().getMarkupId();

        tag.getAttributes().put("data-toggle", "collapse");
        tag.getAttributes().put("aria-expanded", isExpanded());
        tag.getAttributes().put("aria-controls", targetId);
        tag.getAttributes().put("data-target", "#" + targetId);

        if(!isExpanded()) {
            CssClassNameAppender.append(tag, "collapsed");
        }
    }

    public Component getTarget() {
        return target;
    }

    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Sets the expanded flag.
     * 
     * @param expanded the flag
     * @return this for chaining
     */
    public CollapseBehavior setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    @Override
    public void bind(Component component) {
        super.bind(component);
        target.add(targetBehavior);
    }

    @Override
    public void unbind(Component component) {
        target.remove(targetBehavior);
        target = null;
    }

}
