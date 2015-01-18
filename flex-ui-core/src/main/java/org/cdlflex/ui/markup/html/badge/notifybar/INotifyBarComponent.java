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
package org.cdlflex.ui.markup.html.badge.notifybar;

import java.io.Serializable;

/**
 * INotifyBarComponent.
 */
public interface INotifyBarComponent extends Serializable {

    int DEFAULT_PRIORITY = 0;

    /**
     * The sorting priority within the bar itself. Higher means the component will be rendered further left.
     * 
     * @return A priority
     */
    int getPriority();

    /**
     * Whether or not this component is visible in the bar.
     *
     * @return true if it is visible
     */
    boolean isVisible();

    /**
     * Returns the localized label to display, or null if none should be shown (not recommended).
     * 
     * @return A localized string label
     */
    String getLabel();

    /**
     * Can return a serializable value that can be rendered as string, or a Wicket IModel.
     * 
     * @return a concrete value or a Wicket Model
     */
    Object getValue();
}
