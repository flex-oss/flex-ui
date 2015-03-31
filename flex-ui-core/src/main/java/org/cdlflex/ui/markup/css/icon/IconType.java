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
package org.cdlflex.ui.markup.css.icon;

import java.util.HashMap;
import java.util.Map;

import org.cdlflex.ui.markup.css.CssClassNameProvider;

/**
 * Base class to references an icon and its css class for use in bootstrap components that can display icons from an
 * icon sprite.
 */
public abstract class IconType extends CssClassNameProvider {

    private static final long serialVersionUID = 1L;

    private static Map<String, IconType> registry = new HashMap<>();

    protected IconType(String cssClassName) {
        super(cssClassName);
    }

    protected IconType(String name, String cssClassName) {
        this(cssClassName);
        register(name, this);
    }

    /**
     * Attempts to get the IconType from the given name. The name can either be a full key description (e.g.
     * <code>GlyphIconType.home</code>), or a partial key (e.g. <code>home</code>), which might result in ambiguities.
     * 
     * @param name the name to search for
     * @return an IconType or null if none was found
     */
    public static IconType forName(String name) {
        if (registry.containsKey(name)) {
            return registry.get(name);
        }

        for (String key : registry.keySet()) {
            if (key.endsWith("." + name)) {
                return registry.get(key);
            }
        }

        return null;
    }

    /**
     * Registers the given IconType instance with the given partial name. The full key is generated from the IconType's
     * class's simple name plus the given partial name.
     * 
     * @param name the partial name of the icon type (e.g. "home")
     * @param type the instance to register
     */
    protected static void register(String name, IconType type) {
        String key = type.getClass().getSimpleName() + "." + name;
        registry.put(key, type);
    }

}
