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
package org.cdlflex.ui.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Utility class for ResourceReference related operations (e.g. convert ResourceReferences into HeaderItems)
 */
public final class ResourceReferences {

    private ResourceReferences() {
        // utility class
    }

    /**
     * Converts the given ResourceReferences to HeaderItems and joins them with the given Iterable of header items. This
     * is useful for the {@code org.apache.wicket.request.resource.ResourceReference#getDependencies()} method.
     * 
     * @param dependencies the result of {@code super.getDependencies()}
     * @param references the references to add as new dependencies
     * @return a new set of header items
     */
    public static Iterable<? extends HeaderItem> join(Iterable<? extends HeaderItem> dependencies,
        ResourceReference... references) {
        Set<HeaderItem> joined = new HashSet<>();

        joined.addAll(Collections.asList(dependencies));
        joined.addAll(ResourceReferences.asHeaderItems(references));

        return joined;
    }

    /**
     * Converts the given ResourceReference items in the list to HeaderItems.
     *
     * @param referenceList the ResourceReferences to convert
     * @return a list of HeaderItems
     */
    public static List<HeaderItem> asHeaderItems(List<ResourceReference> referenceList) {
        return Collections.map(referenceList, new Collections.Callback<ResourceReference, HeaderItem>() {
            @Override
            public HeaderItem call(ResourceReference object) {
                return asHeaderItem(object);
            }
        });
    }

    /**
     * Converts the given ResourceReference items in the list to HeaderItems.
     *
     * @param referenceList the ResourceReferences to convert
     * @return a list of HeaderItems
     */
    public static List<HeaderItem> asHeaderItems(ResourceReference... referenceList) {
        return asHeaderItems(Arrays.asList(referenceList));
    }

    /**
     * Converts the given ResourceReference to an appropriate HeaderItem.
     *
     * @param reference the ResourceReferences to convert
     * @return a HeaderItem
     */
    public static HeaderItem asHeaderItem(ResourceReference reference) {
        if (reference instanceof JavaScriptResourceReference) {
            return asHeaderItem((JavaScriptResourceReference) reference);
        } else if (reference instanceof CssResourceReference) {
            return asHeaderItem((CssResourceReference) reference);
        } else {
            throw new IllegalArgumentException("Can not convert resource reference " + reference);
        }
    }

    /**
     * Returns the given CssResourceReference as CssReferenceHeaderItem.
     *
     * @param reference the reference to convert
     * @return a header item representing the reference
     */
    public static HeaderItem asHeaderItem(CssResourceReference reference) {
        return CssReferenceHeaderItem.forReference(reference);
    }

    /**
     * Returns the given JavaScriptResourceReference as JavaScriptHeaderItem.
     *
     * @param reference the reference to convert
     * @return a header item representing the reference
     */
    public static HeaderItem asHeaderItem(JavaScriptResourceReference reference) {
        return JavaScriptHeaderItem.forReference(reference);
    }
}
