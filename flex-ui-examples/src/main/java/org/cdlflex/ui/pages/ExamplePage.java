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
package org.cdlflex.ui.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.io.IClusterable;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.pages.examples.ExamplesHomePage;
import org.cdlflex.ui.pages.examples.FormComponentsPage;

/**
 * Base page for an example page.
 */
public class ExamplePage extends BasePage {

    private List<LinkItem> links = new ArrayList<LinkItem>() {
        private static final long serialVersionUID = 1L;

        {
            add(new LinkItem(ExamplesHomePage.class, Model.of("Overview")));
            add(new LinkItem(FormComponentsPage.class, Model.of("Form components")));
        }
    };

    public ExamplePage() {
        super();
    }

    public ExamplePage(IModel<?> model) {
        super(model);
    }

    public ExamplePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ListView<LinkItem>("nav-items", links) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<LinkItem> item) {
                BookmarkablePageLink<Page> link = item.getModelObject().newLink("link");

                item.add(link);

                if (link.linksTo(getPage())) {
                    item.add(new CssClassNameAppender("active"));
                }
            }
        });

    }

    private static final class LinkItem implements IClusterable {
        private static final long serialVersionUID = 1L;

        private Class<? extends Page> pageClass;
        private IModel<String> body;

        public LinkItem(Class<? extends Page> pageClass, IModel<String> body) {
            this.pageClass = pageClass;
            this.body = body;
        }

        public BookmarkablePageLink<Page> newLink(String id) {
            BookmarkablePageLink<Page> link = new BookmarkablePageLink<>(id, getPageClass());

            link.setBody(getBody());

            return link;
        }

        public Class<? extends Page> getPageClass() {
            return pageClass;
        }

        public void setPageClass(Class<? extends Page> pageClass) {
            this.pageClass = pageClass;
        }

        public IModel<String> getBody() {
            return body;
        }

        public void setBody(IModel<String> body) {
            this.body = body;
        }
    }
}
