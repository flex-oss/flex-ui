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
package org.cdlflex.ui.resource;

/**
 * JQueryUiWidgetFactoryResourceReference.
 */
public class JQueryUiWidgetFactoryResourceReference extends ManagedJsResourceReference {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_VERSION = "1.11.0";

    private static final JQueryUiWidgetFactoryResourceReference INSTANCE =
        new JQueryUiWidgetFactoryResourceReference();

    public JQueryUiWidgetFactoryResourceReference() {
        this(DEFAULT_VERSION);
    }

    public JQueryUiWidgetFactoryResourceReference(String version) {
        super("jquery-ui-widget-factory", "widget-factory.min.js", version);
    }

    public JQueryUiWidgetFactoryResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    /**
     * Returns a singleton instance of this resource reference using the default version.
     *
     * @return a resource reference instance
     */
    public static JQueryUiWidgetFactoryResourceReference get() {
        return INSTANCE;
    }
}
