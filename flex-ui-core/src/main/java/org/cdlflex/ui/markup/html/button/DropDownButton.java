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

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.IMarkupSourcingStrategy;
import org.apache.wicket.markup.html.panel.PanelMarkupSourcingStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.behavior.CssClassNameAppender;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.cdlflex.ui.markup.html.basic.Icon;

/**
 * A Bootstrap DropDownButton. Adapted from de.agilecoders wicket-bootstrap.
 */
public abstract class DropDownButton extends AbstractLink implements IActivatable {
    private static final long serialVersionUID = 1L;

    private final IModel<Buttons.Size> buttonSize = Model.of(Buttons.Size.MEDIUM);
    private final IModel<Buttons.Type> buttonType = Model.of(Buttons.Type.DEFAULT);
    private final IModel<Boolean> dropUp = Model.of(false);
    private final ButtonList buttonListView;
    private final WebMarkupContainer baseButton;
    private final Icon icon;

    /**
     * Construct.
     *
     * @param id The markup id
     * @param model The label of the main button
     */
    public DropDownButton(final String id, final IModel<?> model) {
        this(id, model, Model.of((IconType) null));
    }

    /**
     * Construct.
     *
     * @param markupId The markup id
     * @param model The label of the main button
     * @param iconTypeModel the type of the icon
     */
    public DropDownButton(final String markupId, final IModel<?> model, final IModel<IconType> iconTypeModel) {
        super(markupId, model);

        baseButton = newButton("btn", model, iconTypeModel);
        add(baseButton);
        WebMarkupContainer dropdownMenu = new WebMarkupContainer("dropdown-menu") {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("aria-labelledby", DropDownButton.this.getMarkupId());
            }
        };
        add(dropdownMenu);
        buttonListView = newButtonList("buttons");
        dropdownMenu.add(buttonListView);

        this.icon = newButtonIcon("icon", iconTypeModel);

        addIconToBaseButton(icon);
        addButtonBehavior(buttonType, buttonSize);
    }

    /**
     * Returns the base css class name of button container element.
     * 
     * @return base css class name of button container element
     */
    protected String createCssClassName() {
        return "dropdown";
    }

    /**
     * adds an icon to the base button.
     *
     * @param iconElement The icon to add
     */
    protected void addIconToBaseButton(final Icon iconElement) {
        baseButton.add(iconElement);
    }

    /**
     * adds a {@link org.apache.wicket.behavior.Behavior} to the base button.
     *
     * @param behavior to add
     * @return this instance for chaining
     */
    public final DropDownButton addToButton(final Behavior behavior) {
        baseButton.add(behavior);
        return this;
    }

    /**
     * sets the icon of the base button element.
     *
     * @param iconType The {@link IconType} of the icon
     * @return this element instance
     */
    public final DropDownButton setIconType(final IconType iconType) {
        icon.setIconType(iconType);
        return this;
    }

    /**
     * creates a new button instance.
     *
     * @param markupId The component's id
     * @param labelModel The label text as model
     * @param iconTypeModel The icon type as model
     * @return a new button component
     */
    protected WebMarkupContainer newButton(final String markupId, final IModel<?> labelModel,
        final IModel<IconType> iconTypeModel) {
        final WebMarkupContainer button = new WebMarkupContainer(markupId);

        button.setOutputMarkupId(true);
        button.add(newButtonLabel("label", labelModel));

        return button;
    }

    /**
     * creates a new icon component with given {@link IconType}.
     *
     * @param markupId The component' id
     * @param labelModel The label text as model
     * @return new label component
     */
    protected Component newButtonLabel(final String markupId, final IModel<?> labelModel) {
        final Label label = new Label(markupId, labelModel);
        label.setRenderBodyOnly(true);

        return label;
    }

    /**
     * creates a new icon component with given {@link IconType}.
     *
     * @param markupId The component' id
     * @param iconTypeModel The icon type as model
     * @return new icon component
     */
    protected Icon newButtonIcon(final String markupId, final IModel<IconType> iconTypeModel) {
        return new Icon(markupId, iconTypeModel);
    }

    /**
     * adds the button behavior to the base button of this dropdown component.
     *
     * @param typeModel mandatory parameter
     * @param sizeModel mandatory parameter
     */
    protected void addButtonBehavior(final IModel<Buttons.Type> typeModel, final IModel<Buttons.Size> sizeModel) {
        baseButton.add(new ButtonBehavior(typeModel, sizeModel));
    }

    /**
     * creates a list of sub menu buttons which will be shown if sub menu will be opened.
     *
     * @param buttonMarkupId the markup id that all sub menu buttons must be use.
     * @return list of sub menu buttons
     */
    protected abstract List<AbstractLink> newSubMenuButtons(final String buttonMarkupId);

    /**
     * creates a new {@link ButtonList} that contains all buttons from {@link #newButtonList(String)}
     *
     * @param markupId the markup id of {@link ButtonList}
     * @return new {@link ButtonList} instance
     */
    private ButtonList newButtonList(final String markupId) {
        final ButtonList buttonList = new ButtonList(markupId, newSubMenuButtons(ButtonList.getButtonMarkupId()));
        buttonList.setRenderBodyOnly(true);

        return buttonList;
    }

    /**
     * whether to use default dropdown behavior (default value is false which means default behavior) or to open the
     * dropdown menu at the top of dropdown button, also the button icon will be rotated.
     *
     * @param isDropUp true, to use a 180deg rotated button and open menu on top
     * @return this instance for chaining
     */
    public DropDownButton setDropUp(final boolean isDropUp) {
        this.dropUp.setObject(isDropUp);
        return this;
    }

    /**
     * sets the size of the button.
     *
     * @param size mandatory parameter
     * @return this instance for chaining
     */
    public DropDownButton setSize(final Buttons.Size size) {
        this.buttonSize.setObject(size);

        return this;
    }

    /**
     * sets the type of the button.
     *
     * @param type mandatory parameter
     * @return this instance for chaining
     */
    public DropDownButton setType(final Buttons.Type type) {
        this.buttonType.setObject(type);

        return this;
    }

    @Override
    protected final IMarkupSourcingStrategy newMarkupSourcingStrategy() {
        return new PanelMarkupSourcingStrategy(false);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);

        if (dropUp.getObject()) {
            CssClassNameAppender.append(tag, "dropup");
        }

        CssClassNameAppender.append(tag, createCssClassName());
    }

    @Override
    public boolean isActive(final Component item) {
        return buttonListView.hasActiveButton(item);
    }

}
