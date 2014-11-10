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
 * BootstrapDatePickerCssResourceReference.
 */
public class BootstrapDatePickerCssResourceReference extends ManagedCssResourceReference {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_VERSION = "3.1.3";

    private static final BootstrapDatePickerCssResourceReference INSTANCE =
        new BootstrapDatePickerCssResourceReference();

    public BootstrapDatePickerCssResourceReference() {
        this(DEFAULT_VERSION);
    }

    public BootstrapDatePickerCssResourceReference(String version) {
        super("bootstrap-datetimepicker", "css/bootstrap-datetimepicker.min.css", version);
    }

    public BootstrapDatePickerCssResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    /**
     * Returns a singleton instance of this resource.
     * 
     * @return singleton instance of this resource
     */
    public static BootstrapDatePickerCssResourceReference get() {
        return INSTANCE;
    }
}
