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

/**
 * Abstract INavbarComponent implementation that uses {@link Position#LEFT} as default value for position.
 * 
 * @param <T> The concrete component type
 */
public abstract class AbstractNavbarComponent<T extends Component> implements INavbarComponent<T> {

    private Position position;

    public AbstractNavbarComponent() {
        this(Position.LEFT);
    }

    public AbstractNavbarComponent(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void onAfterPopulateItem(ListItem<INavbarComponent<? extends Component>> item) {
        // hook
    }
}
