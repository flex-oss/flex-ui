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

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.junit.Test;

public class ResourceReferencesTest {
    @Test
    public void asHeaderItem_withCssResourceReferenceAsResourceReference_returnsCorrectHeaderItemType() {
        ResourceReference ref = new CssResourceReference(ResourceReferencesTest.class, "css");

        HeaderItem headerItem = ResourceReferences.asHeaderItem(ref);
        assertThat(headerItem, instanceOf(CssReferenceHeaderItem.class));
    }

    @Test
    public void asHeaderItem_withCssResourceReference_returnsCorrectHeaderItemType() {
        CssResourceReference ref = new CssResourceReference(ResourceReferencesTest.class, "css");

        HeaderItem headerItem = ResourceReferences.asHeaderItem(ref);
        assertThat(headerItem, instanceOf(CssReferenceHeaderItem.class));
    }

    @Test
    public void asHeaderItem_withJavaScriptResourceReference_returnsCorrectHeaderItemType() {
        JavaScriptResourceReference ref = new JavaScriptResourceReference(ResourceReferencesTest.class, "js");

        HeaderItem headerItem = ResourceReferences.asHeaderItem(ref);
        assertThat(headerItem, instanceOf(JavaScriptReferenceHeaderItem.class));
    }

    @Test
    public void asHeaderItem_withJavaScriptResourceReferenceAsResourceReference_returnsCorrectHeaderItemType() {
        ResourceReference ref = new JavaScriptResourceReference(ResourceReferencesTest.class, "js");

        HeaderItem headerItem = ResourceReferences.asHeaderItem(ref);
        assertThat(headerItem, instanceOf(JavaScriptReferenceHeaderItem.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void asHeaderItem_withUnknownHeaderItemType_throwsException() throws Exception {
        ResourceReferences.asHeaderItem(new ResourceReference("unknown") {
            private static final long serialVersionUID = 1L;

            @Override
            public IResource getResource() {
                return null;
            }
        });
    }

    @Test
    public void asHeaderItems_withMixedResourceReferenceTypes_returnCorrectHeaderItemTypes() throws Exception {
        ResourceReference jsref = new JavaScriptResourceReference(ResourceReferencesTest.class, "js");
        ResourceReference ssref = new CssResourceReference(ResourceReferencesTest.class, "css");

        List<HeaderItem> headerItems = ResourceReferences.asHeaderItems(jsref, ssref);

        assertEquals(2, headerItems.size());
        assertThat(headerItems.get(0), instanceOf(JavaScriptReferenceHeaderItem.class));
        assertThat(headerItems.get(1), instanceOf(CssReferenceHeaderItem.class));
    }

    @Test
    public void join_joinsHeaderItemsCorrectly() throws Exception {
        HeaderItem headerItem1 =
            ResourceReferences.asHeaderItem(new JavaScriptResourceReference(ResourceReferencesTest.class, "js"));

        List<HeaderItem> list = new ArrayList<>();
        list.add(headerItem1);

        Iterable<? extends HeaderItem> joined =
            ResourceReferences.join(list, new CssResourceReference(ResourceReferencesTest.class, "css"));
        List<? extends HeaderItem> headerItems = Collections.asList(joined);

        assertEquals(2, headerItems.size());
        assertTrue(headerItems.contains(headerItem1));
    }
}
