package org.cdlflex.ui.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Behavior that adds all added ResourceReferences as HeaderItems to the IHeaderResponse. It distinguishes between
 * JavaScriptResourceReference (added as JavaScriptHeaderItem) and CssResourceReference (added as
 * CssReferenceHeaderItem).
 * <p/>
 * <strong>Example</strong><br/>
 * In a Component this can be used to add references to the Header when the page renders
 * 
 * <pre>
 * getPage().add(new FrontendDependencyBehavior(new JavaScriptResourceReference(...));
 * </pre>
 */
public class FrontendDependencyBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    private List<ResourceReference> references;

    protected FrontendDependencyBehavior() {
        this(new ArrayList<ResourceReference>());
    }

    public FrontendDependencyBehavior(ResourceReference... references) {
        this(new ArrayList<>(Arrays.asList(references)));
    }

    public FrontendDependencyBehavior(List<ResourceReference> references) {
        this.references = references;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        for (HeaderItem item : getHeaderItems()) {
            response.render(item);
        }
    }

    /**
     * Returns the ResourceReferences used as dependencies as HeaderItems.
     * 
     * @return a list of HeaderItems
     */
    public List<HeaderItem> getHeaderItems() {
        return asHeaderItems(getResourceReferences());
    }

    /**
     * Returns the ResourceReferences used as dependencies.
     * 
     * @return a list of ResourceReferences
     */
    public List<ResourceReference> getResourceReferences() {
        return references;
    }

    /**
     * Converts the given ResourceReference items in the list to HeaderItems.
     * 
     * @param referenceList the ResourceReferences to convert
     * @return a list of HeaderItems
     */
    protected List<HeaderItem> asHeaderItems(List<ResourceReference> referenceList) {
        List<HeaderItem> items = new ArrayList<>();

        for (ResourceReference reference : referenceList) {
            if (reference instanceof JavaScriptResourceReference) {
                items.add(asHeaderItem((JavaScriptResourceReference) reference));
            } else if (reference instanceof CssResourceReference) {
                items.add(asHeaderItem((CssResourceReference) reference));
            }
        }

        return items;
    }

    /**
     * Returns the given CssResourceReference as CssReferenceHeaderItem.
     * 
     * @param reference the reference to convert
     * @return a header item representing the reference
     */
    protected HeaderItem asHeaderItem(CssResourceReference reference) {
        return CssReferenceHeaderItem.forReference(reference);
    }

    /**
     * Returns the given JavaScriptResourceReference as JavaScriptHeaderItem.
     * 
     * @param reference the reference to convert
     * @return a header item representing the reference
     */
    protected HeaderItem asHeaderItem(JavaScriptResourceReference reference) {
        return JavaScriptHeaderItem.forReference(reference);
    }

}
