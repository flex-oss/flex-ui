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
package org.cdlflex.ui.markup.html.repeater.data.table;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.util.io.IClusterable;

/**
 * Callback for row Items in a table, such that they can be manipulated from outside the FlexTable.
 * 
 * @param <T> Model object type
 */
public interface IRowCallback<T> extends IClusterable {

    /**
     * Call function.
     * 
     * @param rowItem the row item being called
     */
    void call(Item<T> rowItem);
}
