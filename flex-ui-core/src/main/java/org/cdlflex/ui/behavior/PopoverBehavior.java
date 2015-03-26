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

import static org.rauschig.wicketjs.jquery.JQuery.$;

import java.io.Serializable;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.util.Models;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.behavior.JsBehavior;

/**
 * Implements <a href="http://getbootstrap.com/javascript/#popovers">bootstrap's popovers</a>.
 */
public class PopoverBehavior extends JsBehavior {
    private static final long serialVersionUID = 1L;

    private IModel<?> title;
    private IModel<?> body;
    private IModel<Placement> placement;

    private Trigger trigger;

    public PopoverBehavior(Serializable message) {
        this(Model.of(message));
    }

    public PopoverBehavior(Serializable message, Placement placement) {
        this(null, Model.of(message), Model.of(placement));
    }

    public PopoverBehavior(Serializable title, Serializable message) {
        this(Model.of(title), Model.of(message));
    }

    public PopoverBehavior(Serializable title, Serializable message, Placement placement) {
        this(Model.of(title), Model.of(message), Model.of(placement));
    }

    public PopoverBehavior(IModel<?> body) {
        this(null, body);
    }

    public PopoverBehavior(IModel<?> title, IModel<?> body) {
        this(title, body, Model.of(Placement.RIGHT));
    }

    public PopoverBehavior(IModel<?> title, IModel<?> body, IModel<Placement> placement) {
        this.title = title;
        this.body = body;
        this.placement = placement;
    }

    @Override
    public void onComponentTag(ComponentTag tag) {
        tag.put("data-toggle", "popover");
        tag.put("data-content", String.valueOf(body.getObject()));

        if (!Models.isNull(placement)) {
            tag.put("data-placement", placement.getObject().toString());
        }
        if (!Models.isNull(title)) {
            tag.put("title", title.getObject().toString());
        }

        Trigger t = getTrigger();
        if (t != null) {
            tag.put("data-trigger", t.toString());
        }
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public PopoverBehavior setTrigger(Trigger trigger) {
        this.trigger = trigger;
        return this;
    }

    @Override
    protected IJavaScript domReadyJs() {
        return $(this).call("popover");
    }

    public static enum Trigger {
        CLICK,
        HOVER,
        FOCUS,
        MANUAL;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public static enum Placement {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
