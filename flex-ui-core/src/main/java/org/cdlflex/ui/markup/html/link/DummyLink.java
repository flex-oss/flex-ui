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
package org.cdlflex.ui.markup.html.link;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;

/**
 * A link that renders only its body. This can be used to border some content that may or may not be wrapped by a link.
 *
 * <p/>
 * E.g. the markup
 * 
 * <pre>
 * &lt;a wicket:id="dummy-link"&gt;
 *   &lt;i class="glyphicon glyphicon-cog"&gt;&lt;/i&gt; A nice icon
 * &lt;/a&gt;
 * </pre>
 *
 * using the Wicket code
 *
 * <pre>
 * add(new DummyLink(&quot;dummy-link&quot;));
 * </pre>
 *
 * would render as
 * 
 * <pre>
 * &lt;i class="glyphicon glyphicon-cog"&gt;&lt;/i&gt; A nice icon
 * </pre>
 */
public class DummyLink extends AbstractLink {
    private static final long serialVersionUID = 1L;

    public DummyLink(String id) {
        super(id);
    }

    public DummyLink(String id, IModel<?> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setRenderBodyOnly(true);
    }
}
