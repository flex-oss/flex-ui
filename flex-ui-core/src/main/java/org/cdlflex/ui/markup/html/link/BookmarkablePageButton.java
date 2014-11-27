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
package org.cdlflex.ui.markup.html.link;

import org.apache.wicket.Page;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.markup.css.Buttons.Size;
import org.cdlflex.ui.markup.css.Buttons.Type;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.cdlflex.ui.markup.html.Tag;

/**
 * Renders a stable button, with an optional icon, which can be cached in a web browser and used at a later time.
 *
 * @param <T> type of model object, if any
 */
public class BookmarkablePageButton<T> extends BookmarkablePageLink<T> {

    private static final long serialVersionUID = 1L;

    private IModel<IconType> iconType;
    private ButtonBehavior buttonBehavior;

    public <P extends Page> BookmarkablePageButton(String id, Class<P> pageClass) {
        this(id, pageClass, Type.DEFAULT);
    }

    public <P extends Page> BookmarkablePageButton(String id, Class<P> pageClass, PageParameters parameters) {
        this(id, pageClass, parameters, Type.DEFAULT);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of this component
     * @param pageClass The class of page to link to
     * @param type The type of the button, e.g. Success, Warn, Default, Menu...
     * @param <P> type of the page class
     */
    public <P extends Page> BookmarkablePageButton(final String componentId, final Class<P> pageClass, final Type type) {
        this(componentId, pageClass, new PageParameters(), type);
    }

    /**
     * Constructor.
     *
     * @param componentId The non-null id of this component
     * @param pageClass The class of page to link to
     * @param parameters The parameters to pass to the new page when the link is clicked
     * @param type The type of the button, e.g. Success, Warn, Default, Menu...
     * @param <P> type of the page class
     */
    public <P extends Page> BookmarkablePageButton(final String componentId, final Class<P> pageClass,
            final PageParameters parameters, final Type type) {
        super(componentId, pageClass, parameters);

        add(buttonBehavior = new ButtonBehavior(type, Size.MEDIUM));
    }

    /**
     * sets the size of the button according to the given {@link Size}.
     *
     * @param size the size of the button
     * @return reference to the current instance
     */
    public BookmarkablePageButton<T> setSize(Size size) {
        this.buttonBehavior.setSize(size);

        return this;
    }

    public BookmarkablePageButton<T> setType(Type type) {
        this.buttonBehavior.setType(type);

        return this;
    }

    public IModel<IconType> getIconType() {
        return iconType;
    }

    /**
     * Sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon
     * @return this for chaining
     */
    public BookmarkablePageButton<T> setIconType(IModel<IconType> iconType) {
        this.iconType = iconType;
        return this;
    }

    /**
     * Sets the button's icon which will be rendered in front of the label.
     *
     * @param iconType the new button icon
     * @return this for chaining
     */
    public BookmarkablePageButton<T> setIconType(IconType iconType) {
        if (this.iconType == null) {
            this.iconType = new Model<>();
        }
        this.iconType.setObject(iconType);
        return this;
    }

    /**
     * Returns false if either the model or the underlying model object of the icon type is null.
     * 
     * @return whether or not this button uses an icon type
     */
    public boolean hasIconType() {
        IModel<IconType> model = getIconType();
        return model != null && model.getObject() != null;
    }

    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        onBeforeComponentTagBody(markupStream, openTag);
        super.onComponentTagBody(markupStream, openTag);
    }

    /**
     * Called before the super-call to {@code onComponentTagBody}. Used to emit the icon and a spacer.
     * 
     * @param markupStream the markup stream
     * @param openTag the open tag
     */
    protected void onBeforeComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        if (hasIconType()) {
            Tag icon = new Tag("i").attr("class", getIconType().getObject().getCssClassName());
            getResponse().write(icon.toMarkup() + "&nbsp;");
        }
    }

}
