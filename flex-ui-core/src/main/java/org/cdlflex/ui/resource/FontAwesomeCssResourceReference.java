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
 * FontAwesomeCssResourceReference.
 */
public class FontAwesomeCssResourceReference extends ManagedCssResourceReference {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_VERSION = "4.2.0";

    private static final FontAwesomeCssResourceReference INSTANCE = new FontAwesomeCssResourceReference();

    public FontAwesomeCssResourceReference() {
        this(DEFAULT_VERSION);
    }

    public FontAwesomeCssResourceReference(String version) {
        super("font-awesome", "css/font-awesome.min.css", version);
    }

    public FontAwesomeCssResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    /**
     * Returns a singleton instance of this resource reference using the default version.
     *
     * @return a resource reference instance
     */
    public static FontAwesomeCssResourceReference get() {
        return INSTANCE;
    }
}
