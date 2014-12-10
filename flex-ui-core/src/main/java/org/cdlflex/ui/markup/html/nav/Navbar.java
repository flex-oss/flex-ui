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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.util.Collections;

/**
 * A Bootstrap Navbar that renders {@link INavbarComponent} providers as navbar items.
 */
public class Navbar extends Panel {

    private static final long serialVersionUID = 1L;

    private List<INavbarComponent<? extends Component>> components;

    public Navbar(String id) {
        this(id, null);
    }

    public Navbar(String id, IModel<?> model) {
        this(id, model, new ArrayList<INavbarComponent<? extends Component>>());
    }

    public Navbar(String id, IModel<?> model, List<INavbarComponent<? extends Component>> components) {
        super(id, model);
        this.components = components;
    }

    /**
     * Uses the given INavbarComponent to create a component within the bar.
     * 
     * @param component the component factory
     * @param <T> the component type
     * @return this for chaining
     */
    public <T extends Component> Navbar add(INavbarComponent<T> component) {
        this.components.add(component);
        return this;
    }

    /**
     * Uses the given INavbarComponent to create a component within the bar.
     * 
     * @param components the component factories
     * @return this for chaining
     */
    public Navbar add(Collection<? extends INavbarComponent<? extends Component>> components) {
        this.components.addAll(components);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(newContainer("navbar-container"));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        tag.put("role", "navigation");
        CssClassNameAppender.append(tag, "navbar", "navbar-default");
    }

    /**
     * Creates the outermost navbar container wrapper within the <code>&lt;nav&gt;</code> component.
     * 
     * @param id component id
     * @return a component
     */
    protected Component newContainer(String id) {
        WebMarkupContainer container = new WebMarkupContainer(id);

        container.add(newNavbarHeader("navbar-header"));
        container.add(newNavLeft("nav-left"));
        container.add(newNavRight("nav-right"));

        return container;
    }

    /**
     * Creates the navbar header that contains the brand link.
     *
     * @param id component id
     * @return a component
     */
    protected Component newNavbarHeader(String id) {
        WebMarkupContainer header = new WebMarkupContainer(id);

        Component brand = newBrandLink("brand-link");
        if (brand == null) {
            brand = new WebMarkupContainer("brand-link").setVisible(false);
        }
        header.add(brand);

        return header;
    }

    /**
     * Creates the brand link of the navbar header. May return null, causing nothing to be rendered.
     *
     * @param id component id
     * @return a component
     */
    protected Component newBrandLink(String id) {
        return null;
    }

    /**
     * Creates the container that contains the navigation items on the left side.
     *
     * @param id component id
     * @return a component
     */
    protected Component newNavLeft(String id) {
        return newNav(id, Collections.filter(components, new PositionFilter(Position.LEFT)));
    }

    /**
     * Creates the container that contains the navigation items on the right side.
     *
     * @param id component id
     * @return a component
     */
    protected Component newNavRight(String id) {
        return newNav(id, Collections.filter(components, new PositionFilter(Position.RIGHT)));
    }

    private Component newNav(String id, List<INavbarComponent<? extends Component>> components) {
        return new ListView<INavbarComponent<? extends Component>>(id, components) {

            private static final long serialVersionUID = 1L;

            @Override
            protected ListItem<INavbarComponent<? extends Component>> newItem(int index,
                IModel<INavbarComponent<? extends Component>> itemModel) {
                return super.newItem(index, itemModel);
            }

            @Override
            protected void populateItem(ListItem<INavbarComponent<? extends Component>> item) {
                INavbarComponent<? extends Component> navbarComponent = item.getModelObject();

                item.add(newNavElement("nav-component-container", navbarComponent));
                navbarComponent.onAfterPopulateItem(item);
            }
        };
    }

    private <T extends Component> Component newNavElement(String id, INavbarComponent<T> navbarComponent) {
        T component = navbarComponent.create("nav-component");
        return wrapComponent(id, navbarComponent, component);
    }

    private <T extends Component> Component wrapComponent(String id, INavbarComponent<T> navbarComponent, T component) {
        WebMarkupContainer container;

        if (navbarComponent instanceof NavbarDropDown) {
            container = new Fragment(id, "nav-transparent", this);
        } else if (navbarComponent instanceof NavbarLink) {
            container = new Fragment(id, "nav-link", this);
        } else {
            container = new Fragment(id, "nav-transparent", this);
        }

        return container.add(component);
    }

    /**
     * Filters a list of {@link INavbarComponent} by their position, i.e. the return value of
     * {@link org.cdlflex.ui.markup.html.nav.INavbarComponent#getPosition()}.
     */
    protected static class PositionFilter implements Collections.Predicate<INavbarComponent<? extends Component>> {

        private final Position position;

        public PositionFilter(Position position) {
            this.position = position;
        }

        @Override
        public Boolean call(INavbarComponent<? extends Component> object) {
            return position.equals(object.getPosition());
        }
    }
}
