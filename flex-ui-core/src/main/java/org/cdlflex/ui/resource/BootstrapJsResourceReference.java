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

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.resource.JQueryResourceReference;
import org.cdlflex.ui.util.ResourceReferences;

/**
 * Resource reference for the main bootstrap java script.
 */
public class BootstrapJsResourceReference extends ManagedJsResourceReference {
    public static final String DEFAULT_VERSION = "3.2.0";

    private static final BootstrapJsResourceReference INSTANCE = new BootstrapJsResourceReference();

    public BootstrapJsResourceReference() {
        this(DEFAULT_VERSION);
    }

    public BootstrapJsResourceReference(String version) {
        super("bootstrap", "js/bootstrap.min.js", version);
    }

    public BootstrapJsResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    /**
     * Returns a singleton instance of this resource reference using the default version.
     *
     * @return a resource reference instance
     */
    public static BootstrapJsResourceReference get() {
        return INSTANCE;
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return ResourceReferences.join(super.getDependencies(), JQueryResourceReference.get());
    }
}
