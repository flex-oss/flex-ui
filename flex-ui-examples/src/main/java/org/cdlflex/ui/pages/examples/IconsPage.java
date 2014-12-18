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
package org.cdlflex.ui.pages.examples;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.cdlflex.ui.behavior.FrontendDependencyBehavior;
import org.cdlflex.ui.markup.css.icon.FontAwesomeIconType;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.cdlflex.ui.markup.html.basic.Icon;
import org.cdlflex.ui.pages.ExamplePage;
import org.cdlflex.ui.resource.FontAwesomeCssResourceReference;
import org.cdlflex.ui.util.Collections;
import org.cdlflex.ui.util.Collections.Callback;
import org.cdlflex.ui.util.Collections.NotNullFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IconsPage.
 */
public class IconsPage extends ExamplePage {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(IconsPage.class);

    public IconsPage() {
        add(new ListView<IconType>("glyph-icons", getGlyphIcons()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<IconType> item) {
                item.add(new Label("class", item.getModelObject().getCssClassName()));
                item.add(new Icon("icon", item.getModel()));
            }
        });

        add(new FrontendDependencyBehavior(FontAwesomeCssResourceReference.get()));
        add(new ListView<IconType>("font-awesome-icons", getFontAwesomeIcons()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<IconType> item) {
                item.add(new Label("class", item.getModelObject().getCssClassName()));
                item.add(new Icon("icon", item.getModel()));
            }
        });
    }

    private List<IconType> getGlyphIcons() {
        return getIconTypes(GlyphIconType.class);
    }

    private List<IconType> getFontAwesomeIcons() {
        return getIconTypes(FontAwesomeIconType.class);
    }

    private List<IconType> getIconTypes(Class<? extends IconType> iconTypeClass) {
        Callback<Field, IconType> factory = new Callback<Field, IconType>() {
            @Override
            public IconType call(Field field) {
                if (IconType.class.isAssignableFrom(field.getType())) {
                    try {
                        return (IconType) field.get(null);
                    } catch (IllegalAccessException e) {
                        LOG.warn("Could not add field {}", field, e);
                    }
                }
                return null;
            }
        };

        List<IconType> iconTypes = Collections.map(Arrays.asList(iconTypeClass.getDeclaredFields()), factory);
        Collections.retain(iconTypes, new NotNullFilter<IconType>());
        return iconTypes;
    }
}
