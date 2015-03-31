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
package org.cdlflex.ui.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.resource.ResourceReference;
import org.cdlflex.ui.util.ResourceReferences;

/**
 * Behavior that adds all added ResourceReferences as HeaderItems to the IHeaderResponse. It distinguishes between
 * JavaScriptResourceReference (added as JavaScriptHeaderItem) and CssResourceReference (added as
 * CssReferenceHeaderItem).
 *
 * <p>
 * <strong>Example</strong><br>
 * In a Component this can be used to add references to the Header when the page renders
 * </p>
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
        return ResourceReferences.asHeaderItems(getResourceReferences());
    }

    /**
     * Returns the ResourceReferences used as dependencies.
     * 
     * @return a list of ResourceReferences
     */
    public List<ResourceReference> getResourceReferences() {
        return references;
    }

}
