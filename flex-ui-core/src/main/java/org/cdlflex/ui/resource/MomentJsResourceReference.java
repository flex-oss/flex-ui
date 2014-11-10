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
 * <a href="http://momentjs.com/">Moment.js</a> resource reference.
 */
public class MomentJsResourceReference extends ManagedJsResourceReference {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_VERSION = "2.8.3";

    private static final MomentJsResourceReference INSTANCE = new MomentJsResourceReference();

    public MomentJsResourceReference() {
        this(DEFAULT_VERSION);
    }

    public MomentJsResourceReference(String version) {
        super("moment", "moment.min.js", version);
    }

    public MomentJsResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    /**
     * Returns a singleton instance of this resource reference using the default version.
     * 
     * @return a resource reference instance
     */
    public static MomentJsResourceReference get() {
        return INSTANCE;
    }
}
