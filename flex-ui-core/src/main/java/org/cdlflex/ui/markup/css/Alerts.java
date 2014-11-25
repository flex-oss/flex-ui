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
 * Static class that provides utilities and typesafe parameters for boostrap alerts.
 */
public final class Alerts {

    private Alerts() {
        // utility class
    }

    public static final String CSS_CLASS = "alert";

    /**
     * The alert level.
     */
    public static enum Level implements ICssClassNameProvider {
        UNKNOWN(""),
        PRIMARY("alert-primary"),
        SUCCESS("alert-success"),
        INFO("alert-info"),
        WARNING("alert-warning"),
        DANGER("alert-danger");

        private final String cssClassName;

        private Level(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        @Override
        public String getCssClassName() {
            return cssClassName;
        }
    }
}
