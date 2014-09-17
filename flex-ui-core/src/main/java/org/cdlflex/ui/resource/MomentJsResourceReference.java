package org.cdlflex.ui.resource;

/**
 * <a href="http://momentjs.com/">Moment.js</a> resource reference.
 */
public class MomentJsResourceReference extends ManagedJsResourceReference {

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
