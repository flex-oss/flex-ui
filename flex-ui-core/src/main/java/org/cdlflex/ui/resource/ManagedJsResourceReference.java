package org.cdlflex.ui.resource;

import java.util.Locale;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A JavaScriptResourceReference that provides convenience constructors for resources within the scope of this class.
 * The structure in which the resource resides should follow the convention:
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
 *     moment/2.8.3/moment.min.js
 * </pre>
 * 
 * This allows for parameterized versions.
 */
public class ManagedJsResourceReference extends JavaScriptResourceReference {

    public ManagedJsResourceReference(String name) {
        this(ManagedJsResourceReference.class, name);
    }

    public ManagedJsResourceReference(String path, String file, String version) {
        this(String.format("%s/%s/%s", path, version, file));
    }

    public ManagedJsResourceReference(Class<?> scope, String name) {
        super(scope, name);
    }

    public ManagedJsResourceReference(Class<?> scope, String name, Locale locale, String style, String variation) {
        super(scope, name, locale, style, variation);
    }
}
