package org.cdlflex.ui.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.cdlflex.ui.behavior.FrontendDependencyBehavior;
import org.cdlflex.ui.resource.BootstrapCssResourceReference;
import org.cdlflex.ui.resource.BootstrapJsResourceReference;
import org.cdlflex.ui.resource.BootstrapThemeResourceReference;

/**
 * Base page containing the general bootstrap layout.
 */
public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;

    public BasePage() {
        super();
    }

    public BasePage(IModel<?> model) {
        super(model);
    }

    public BasePage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new FrontendDependencyBehavior(BootstrapCssResourceReference.get(), BootstrapJsResourceReference.get(),
                BootstrapThemeResourceReference.get(), new CssResourceReference(BasePage.class, "style.css")));
    }
}
