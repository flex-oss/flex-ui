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
package org.cdlflex.ui.markup.html.dialog;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.css.ICssClassNameProvider;
import org.cdlflex.ui.markup.html.button.ButtonList;
import org.cdlflex.ui.model.CssClassNameProvidingModel;

/**
 * A configurable, generic, modal dialog.
 * 
 * @param <T> The model object type.
 */
public abstract class Dialog<T> extends GenericPanel<T> {

    private static final long serialVersionUID = 1L;

    /**
     * Markup id to be used for footer buttons
     */
    public static final String BUTTON_MARKUP_ID = ButtonList.getButtonMarkupId();

    private IModel<?> title;
    private IModel<Size> size;
    private IModel<Boolean> fade;

    /**
     * Contains buttons shown in footer.
     */
    private List<AbstractLink> buttons;

    /* Child components */
    private WebMarkupContainer container;
    private Component titleLabel;
    private WebMarkupContainer body;
    private WebMarkupContainer footer;

    public Dialog(String id) {
        this(id, null);
    }

    public Dialog(String id, IModel<T> model) {
        this(id, new Model<>(), model);
    }

    public Dialog(String id, IModel<?> title, IModel<T> model) {
        super(id, model);
        this.title = title;
        this.size = new Model<>(Size.NORMAL);
        this.buttons = new ArrayList<>();
        this.fade = new Model<>(Boolean.TRUE);
    }

    public IModel<?> getTitle() {
        return title;
    }

    /**
     * Sets the display model for the header label.
     * 
     * @param title the header display model
     * @return this for chaining
     */
    public Dialog<T> setTitle(IModel<?> title) {
        this.title = title;
        if (titleLabel != null) {
            titleLabel.setDefaultModel(title);
        }
        return this;
    }

    public IModel<Size> getSize() {
        return size;
    }

    /**
     * Sets the dialog size.
     * 
     * @param size the size
     * @return this for chaining
     */
    public Dialog<T> setSize(IModel<Size> size) {
        this.size = size;
        return this;
    }

    /**
     * Sets the dialog size.
     * 
     * @param size the size
     * @return this for chaining
     */
    public Dialog<T> setSize(Size size) {
        getSize().setObject(size);
        return this;
    }

    public IModel<Boolean> getFade() {
        return fade;
    }

    /**
     * Sets whether or not to use the modal fade animation.
     *
     * @param fade flag
     * @return this for chaining
     */
    public Dialog<T> setFade(IModel<Boolean> fade) {
        this.fade = fade;
        return this;
    }

    /**
     * Sets whether or not to use the modal fade animation.
     *
     * @param fade flag
     * @return this for chaining
     */
    public Dialog<T> setFade(Boolean fade) {
        getFade().setObject(fade);
        return this;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        setOutputMarkupId(true);

        container = newContainer("modal-dialog");
        titleLabel = newTitleLabel("modal-title");
        body = newBody("modal-body");
        footer = newFooter("modal-footer");

        add(container);
        container.add(titleLabel);
        container.add(body);
        container.add(footer);

        add(new CssClassNameAppender("modal"));
        add(new CssClassNameAppender(new AbstractReadOnlyModel<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                Boolean flag = getFade().getObject();
                return (flag != null && flag) ? "fade" : "";
            }
        }));

        // adds actions provided by the overrideable createActions method
        List<AbstractLink> actions = createActions(BUTTON_MARKUP_ID);
        if (actions != null && !actions.isEmpty()) {
            for (AbstractLink action : actions) {
                addButton(action);
            }
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        if (Strings.isEmpty(titleLabel.getDefaultModelObjectAsString())) {
            // there must be at least on character inside the header to prevent layout problems.
            titleLabel.setDefaultModelObject("&nbsp;");
            titleLabel.setEscapeModelStrings(false);
        }

        footer.setVisible(buttons.size() > 0);
    }

    /**
     * Factory method for creating the outer modal container.
     *
     * @param id the component id
     * @return a new component
     */
    protected WebMarkupContainer newContainer(String id) {
        WebMarkupContainer modal = new TransparentWebMarkupContainer(id);
        modal.add(new CssClassNameAppender(new CssClassNameProvidingModel<>(size)));
        return modal;
    }

    /**
     * Factory method for creating the component showing the dialog title.
     *
     * @param id the component id
     * @return a new component
     */
    protected Component newTitleLabel(String id) {
        return new Label(id, getTitle());
    }

    /**
     * Factory method for creating the body container.
     *
     * @param id the component id
     * @return a new component
     */
    protected WebMarkupContainer newBody(String id) {
        return new TransparentWebMarkupContainer(id);
    }

    /**
     * Factory method for creating the footer container. By default, it adds a ButtonList to display a variable list of
     * buttons.
     *
     * @param id the component id
     * @return a new component
     */
    protected WebMarkupContainer newFooter(String id) {
        WebMarkupContainer footer = new WebMarkupContainer(id);

        footer.add(new ButtonList("buttons", buttons));

        return footer;
    }

    /**
     * Returns the dialog container component.
     *
     * @return a component
     */
    protected WebMarkupContainer getContainer() {
        return container;
    }

    /**
     * Returns the body component.
     *
     * @return a component
     */
    protected WebMarkupContainer getBody() {
        return body;
    }

    /**
     * Returns the footer component.
     *
     * @return a component
     */
    protected WebMarkupContainer getFooter() {
        return footer;
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        modifyContainerTag(tag);
    }

    /**
     * Adds the attributes required by bootstrap for activating the modal behavior to the panel tag.
     * 
     * @param tag Tag to modify
     */
    protected void modifyContainerTag(ComponentTag tag) {
        tag.put("tabindex", "-1");
        tag.put("role", "dialog");
        tag.put("aria-hidden", "true");
        tag.put("aria-labelledby", titleLabel.getMarkupId());
    }

    /**
     * Adds the given link to the footer panel. The component is required to use the {@link #BUTTON_MARKUP_ID} constant
     * as component id.
     * 
     * @param button the link to add
     * @return this for chaining
     * @throws java.lang.IllegalArgumentException if the component does not use the correct component id
     */
    public Dialog<T> addButton(AbstractLink button) throws IllegalArgumentException {
        if (!BUTTON_MARKUP_ID.equals(button.getId())) {
            throw new IllegalArgumentException(String.format("Invalid button component id. Must be '%s', was '%s'.",
                    BUTTON_MARKUP_ID, button.getId()));
        }

        buttons.add(button);
        return this;
    }

    /**
     * Adds the given link to the footer panel to the given position. The component is required to use the
     * {@link #BUTTON_MARKUP_ID} constant as component id.
     *
     * @param button the link to add
     * @param position index at which the specified element is to be inserted
     * @return this for chaining
     * @throws java.lang.IllegalArgumentException if the component does not use the correct component id
     */
    public Dialog<T> addButton(int position, AbstractLink button) throws IllegalArgumentException {
        if (!BUTTON_MARKUP_ID.equals(button.getId())) {
            throw new IllegalArgumentException(String.format("Invalid button component id. Must be '%s', was '%s'.",
                    BUTTON_MARKUP_ID, button.getId()));
        }

        buttons.add(position, button);
        return this;
    }

    /**
     * Adds the given link to the footer panel at the front of the list. The component is required to use the
     * {@link #BUTTON_MARKUP_ID} constant as component id.
     *
     * @param button the link to add
     * @return this for chaining
     * @throws java.lang.IllegalArgumentException if the component does not use the correct component id
     */
    public Dialog<T> prependButton(AbstractLink button) throws IllegalArgumentException {
        return addButton(0, button);
    }

    /**
     * Adds a DialogCloseLink with specific label.
     * 
     * @param label The label of close button
     * @return this instance
     */
    @SuppressWarnings("unchecked")
    public Dialog<T> addCloseButton(final IModel<String> label) {
        AbstractLink button = newCloseLink(BUTTON_MARKUP_ID, label);

        return (button == null) ? this : addButton(button);
    }

    /**
     * Prepends a DialogCloseLink with specific label.
     * 
     * @param label The label of close button
     * @return this instance
     */
    @SuppressWarnings("unchecked")
    public Dialog<T> prependCloseButton(final IModel<String> label) {
        AbstractLink button = newCloseLink(BUTTON_MARKUP_ID, label);

        return (button == null) ? this : prependButton(button);
    }

    /**
     * Creates a new link that opens this dialog.
     * 
     * @param id the component id
     * @return a new DialogOpenLink that opens this dialog
     */
    public DialogOpenLink createOpenLink(String id) {
        return new DialogOpenLink(id, this);
    }

    /**
     * Factory called in {@code #addCloseButton(IModel)} to create a new link that closes the dialog.
     * 
     * @param id the component id
     * @param label the body label
     * @return a new component
     */
    public AbstractLink newCloseLink(String id, IModel<String> label) {
        return new DialogCloseLink(id).setBody(label);
    }

    /**
     * Hook to create new action links.
     * 
     * @param id the button id
     * @return a list of actions
     */
    protected List<AbstractLink> createActions(String id) {
        return null;
    }

    /**
     * Dialog size.
     */
    public static enum Size implements ICssClassNameProvider {
        NORMAL(""),
        LARGE("modal-lg"),
        SMALL("modal-sm");

        private final String cssClassName;

        private Size(String cssClassName) {
            this.cssClassName = cssClassName;
        }

        @Override
        public String getCssClassName() {
            return cssClassName;
        }
    }

}
