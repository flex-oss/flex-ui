package org.cdlflex.ui.pages.examples;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.Form;
import org.cdlflex.ui.markup.html.form.ButtonMultipleChoice;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * FormComponentsPage.
 */
public class FormComponentsPage extends ExamplePage {

    public FormComponentsPage() {
        Form<Void> form = new Form<>("form");

        form.add(new ButtonMultipleChoice<>("button-multiple-choice", Arrays.asList("Choice A", "Choice B",
                "Choice C", "Choice D")));

        add(form);
    }

}
