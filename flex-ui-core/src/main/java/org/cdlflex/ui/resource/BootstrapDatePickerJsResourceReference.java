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
import org.cdlflex.ui.util.ResourceReferences;

/**
 * BootstrapDatePickerJsResourceReference.
 */
public class BootstrapDatePickerJsResourceReference extends ManagedJsResourceReference {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_VERSION = "3.1.3";

    private static final BootstrapDatePickerJsResourceReference INSTANCE =
        new BootstrapDatePickerJsResourceReference();

    public BootstrapDatePickerJsResourceReference() {
        this(DEFAULT_VERSION);
    }

    public BootstrapDatePickerJsResourceReference(String version) {
        super("bootstrap-datetimepicker", "js/bootstrap-datetimepicker.min.js", version);
    }

    public BootstrapDatePickerJsResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    /**
     * Returns a singleton instance of this resource.
     * 
     * @return singleton instance of this resource
     */
    public static BootstrapDatePickerJsResourceReference get() {
        return INSTANCE;
    }

    @Override
    public Iterable<? extends HeaderItem> getDependencies() {
        return ResourceReferences.join(super.getDependencies(), MomentJsResourceReference.get());
    }
}
