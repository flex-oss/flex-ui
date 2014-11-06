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
package org.cdlflex.ui.markup.css;

/**
 * Static class that provides utilities and typesafe parameters for boostrap buttons.
 */
public final class Buttons {

    private Buttons() {
        // utility class
    }

    public static final String CSS_CLASS = "btn";

    /**
     * Defines all possible sizes of a button element.
     */
    public static enum Size implements ICssClassNameProvider {
        MINI("btn-xs"),
        SMALL("btn-sm"),
        MEDIUM(""), // default button size doesn't need any css class
        LARGE("btn-lg");

        private final String cssClassName;

        /**
         * Construct.
         *
         * @param cssClassName the css class name of this size
         */
        private Size(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        @Override
        public String getCssClassName() {
            return cssClassName;
        }
    }

    /**
     * Defines all possible button types.
     */
    public static enum Type implements ICssClassNameProvider {
        NONE(""), // no specific css class
        DEFAULT("btn-default"), // Standard gray button with gradient
        MENU(""), // Menu button which has no default css class name
        PRIMARY("btn-primary"), // Provides extra visual weight and identifies the primary action in a set of buttons
        INFO("btn-info"), // Used as an alternate to the default styles
        SUCCESS("btn-success"), // Indicates a successful or positive action
        WARNING("btn-warning"), // Indicates caution should be taken with this action
        DANGER("btn-danger"), // Indicates a dangerous or potentially negative action
        LINK("btn-link"); // De-emphasize a button by making it look like a link while maintaining button behavior

        private final String cssClassName;

        /**
         * Construct.
         *
         * @param cssClassName the css class name of button type
         */
        private Type(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        @Override
        public String getCssClassName() {
            return cssClassName;
        }

    }

}
