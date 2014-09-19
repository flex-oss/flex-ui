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
 * Resource reference for the main bootstrap theme css.
 */
public class BootstrapThemeResourceReference extends ManagedCssResourceReference {

    public static final String DEFAULT_VERSION = "3.2.0";

    private static final BootstrapThemeResourceReference INSTANCE = new BootstrapThemeResourceReference();

    public BootstrapThemeResourceReference() {
        this(DEFAULT_VERSION);
    }

    public BootstrapThemeResourceReference(String version) {
        super("bootstrap", "css/bootstrap-theme.min.js", version);
    }

    public BootstrapThemeResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    /**
     * Returns a singleton instance of this resource reference using the default version.
     *
     * @return a resource reference instance
     */
    public static BootstrapThemeResourceReference get() {
        return INSTANCE;
    }
}
