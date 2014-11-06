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
package org.cdlflex.ui.markup.html.button;

import org.apache.wicket.Component;

/**
 * An interface that allows the implementations to decide whether they are the active item in a collection of items
 * (like buttons, links, pills, ...).
 */
public interface IActivatable {

    /**
     * Decides whether the current instance ({@code this}) is the active item.
     *
     * @param item the current instance as a Component
     * @return {@code true} if the item is the current active one
     */
    boolean isActive(Component item);
}
