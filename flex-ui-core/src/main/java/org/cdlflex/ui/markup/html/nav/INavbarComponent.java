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
package org.cdlflex.ui.markup.html.nav;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.util.io.IClusterable;
import org.cdlflex.ui.util.IComponentFactory;

/**
 * INavbarComponent provides components to be rendered and a position declaration for the position within the navbar.
 */
public interface INavbarComponent<T extends Component> extends IComponentFactory<T>, IClusterable {

    /**
     * Where to position the component
     * 
     * @return the component
     */
    Position getPosition();

    /**
     * Called after the ListItem was populated with the Component created by this {@link INavbarComponent}.
     * 
     * @param item the list item
     */
    void onAfterPopulateItem(ListItem<INavbarComponent<? extends Component>> item);
}
