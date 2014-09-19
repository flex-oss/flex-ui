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

import java.util.Locale;

import org.apache.wicket.request.resource.CssResourceReference;

/**
 * A CssResourceReference that provides convenience constructors for resources within the scope of this class. The
 * structure in which the resource resides should follow the convention:
 *
 * <pre>
 *   path
 *   `- version
 *      `- file
 * </pre>
 *
 * E.g.
 *
 * <pre>
 *     bootstrap
 *     `- 3.2.0
 *        `- css/bootstrap.min.css
 * </pre>
 *
 * This allows for parameterized versions.
 */
public class ManagedCssResourceReference extends CssResourceReference {

    public ManagedCssResourceReference(String name) {
        this(ManagedCssResourceReference.class, name);
    }

    public ManagedCssResourceReference(String path, String file, String version) {
        this(String.format("%s/%s/%s", path, version, file));
    }

    public ManagedCssResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    public ManagedCssResourceReference(Class<?> scope, String name, Locale locale, String style, String variation) {
        super(scope, name, locale, style, variation);
    }
}
