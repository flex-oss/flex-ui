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
package org.cdlflex.ui.markup.html.repeater.data.table.flex;

import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;

/**
 * PageNavigation.
 */
public class PagingNavigator extends org.apache.wicket.markup.html.navigation.paging.PagingNavigator {

    private static final long serialVersionUID = 1L;

    public PagingNavigator(String id, IPageable pageable) {
        super(id, pageable);
    }

    public PagingNavigator(String id, IPageable pageable, IPagingLabelProvider labelProvider) {
        super(id, pageable, labelProvider);
    }

    @Override
    protected PagingNavigation newNavigation(String id, IPageable pageable, IPagingLabelProvider labelProvider) {
        return new FlexPagingNavigation(id, pageable, labelProvider);
    }
}
