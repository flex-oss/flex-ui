package org.cdlflex.ui.markup.html.repeater.data.table.flex;

import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.model.ReadOnlyModel;

/**
 * A custom navigation for a PageableListView that shows only five links to other pages of the PageableListView.
 */
public class FlexPagingNavigation extends PagingNavigation {

    public FlexPagingNavigation(String id, IPageable pageable, IPagingLabelProvider labelProvider) {
        super(id, pageable, labelProvider);

        setMargin(2);
        setViewSize(5);
    }

    @Override
    protected void populateItem(LoopItem loopItem) {
        super.populateItem(loopItem);

        // Get the index of page this link shall point to
        final long pageIndex = getStartIndex() + loopItem.getIndex();
        loopItem.add(new CssClassNameAppender(new ReadOnlyModel<>(() -> (pageIndex == pageable.getCurrentPage())
            ? "active" : "")));
    }
}
