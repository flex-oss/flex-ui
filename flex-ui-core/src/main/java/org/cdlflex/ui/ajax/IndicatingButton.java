package org.cdlflex.ui.ajax;

import static org.rauschig.wicketjs.jquery.JQuery.$;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.IModel;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.ajax.JsAjaxLink;

/**
 * A link that uses bootstrap's <code>button</code> JavaScript to load a default loading text. You can override the
 * resource key <code>indicator.loading-text</code> to overwrite the default loading text used.
 * 
 * @param <T> Model object type
 */
public abstract class IndicatingButton<T> extends JsAjaxLink<T> {

    private static final long serialVersionUID = 1L;

    private IModel<String> loadingDisplayModel;

    public IndicatingButton(String id) {
        this(id, null);
    }

    public IndicatingButton(String id, IModel<T> model) {
        this(id, model, null);
    }

    public IndicatingButton(String id, IModel<T> model, IModel<String> loadingDisplayModel) {
        super(id, model);
        this.loadingDisplayModel = loadingDisplayModel;
    }

    /**
     * Sets the model used for showing the loading text.
     * 
     * @param model a string model
     * @return this for chaining
     */
    public IndicatingButton setLoadingDisplayModel(IModel<String> model) {
        this.loadingDisplayModel = model;
        return this;
    }

    /**
     * Returns the model used for showing the loading text.
     * 
     * @return a string model
     */
    public IModel<String> getLoadingDisplayModel() {
        return loadingDisplayModel;
    }

    @Override
    public IJavaScript onBefore() {
        return $(this).call("button", "loading");
    }

    @Override
    public IJavaScript onComplete() {
        return $(this).call("button", "reset");
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        String loadingText = getLoadingText();

        if (loadingText != null) {
            tag.put("data-loading-text", loadingText);
        }
    }

    /**
     * Loads the model object from the loadingDisplayModel.
     * 
     * @return a string to show as loading text
     */
    private String getLoadingText() {
        IModel<String> model = getLoadingDisplayModel();
        if (model == null) {
            return null;
        }
        return model.getObject();
    }
}
