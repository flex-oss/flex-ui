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

import static org.rauschig.wicketjs.jquery.JQuery.$;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.cdlflex.ui.ajax.markup.html.dialog.form.AjaxFormDialog;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.html.basic.DateLabel;
import org.cdlflex.ui.markup.html.dialog.Dialog;
import org.cdlflex.ui.markup.html.dialog.DialogOpenLink;
import org.cdlflex.ui.markup.html.dialog.form.FormDialog;
import org.cdlflex.ui.markup.html.form.DateTimePicker;
import org.cdlflex.ui.model.Person;
import org.cdlflex.ui.pages.ExamplePage;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.JsReturn;
import org.rauschig.wicketjs.ajax.JsAjaxLink;
import org.rauschig.wicketjs.markup.html.JsLink;

/**
 * DialogsPage.
 */
public class DialogsPage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    private IModel<Person> person = new Model<>(new Person(1, "Alan Turing", "1912-06-23"));

    public DialogsPage() {
        Dialog<Person> personDetailDialog = newPersonDetailDialog("person-detail-dialog");
        Dialog<Person> personEditDialog = newPersonEditDialog("person-edit-dialog");
        Dialog<Person> personAjaxEditDialog = newPersonAjaxEditDialog("person-ajax-edit-dialog");

        add(personDetailDialog);
        add(personEditDialog);
        add(personAjaxEditDialog);

        add(new DialogOpenLink("open-person-detail-dialog", personDetailDialog));
        add(new DialogOpenLink("open-person-edit-dialog", personEditDialog));
        add(new DialogOpenLink("open-person-ajax-edit-dialog", personAjaxEditDialog));
    }

    private Dialog<Person> newPersonDetailDialog(String id) {
        Dialog<Person> dialog = new PersonDetailDialog(id, person);

        dialog.addCloseButton(Model.of("Close"));
        dialog.setTitle(Model.of("Person"));

        return dialog;
    }

    private FormDialog<Person> newPersonEditDialog(String id) {
        return new PersonEditDialog(id, person);
    }

    private AjaxFormDialog<Person> newPersonAjaxEditDialog(String id) {
        return new PersonAjaxEditDialog(id, person);
    }

    private class PersonAjaxEditDialog extends AjaxFormDialog<Person> {
        private static final long serialVersionUID = 1L;

        private PersonAjaxEditDialog(String id, IModel<Person> model) {
            super(id, Model.of("Edit"), model);
        }

        @Override
        public IJavaScript precondition() {
            return new JsReturn(new JsCall("confirm", "Really submit?"));
        }

        @Override
        protected void onSubmit(AjaxRequestTarget target, Form<Person> form) {
            System.out.println("Ajax submit: " + getModelObject());

            // make sure other model dependent components get updated
            target.add(DialogsPage.this.get("person-detail-dialog"));
            target.add(DialogsPage.this.get("person-edit-dialog"));
        }

        @Override
        protected Form<Person> newForm(IModel<Person> model) {
            Form<Person> form = new Form<>("form", new CompoundPropertyModel<>(model));
            form.add(new TextField<>("name", String.class));
            form.add(new DateTimePicker("birthday", "y-MM-dd").setPickTime(false));
            return form;
        }
    }

    private class PersonEditDialog extends FormDialog<Person> {
        private static final long serialVersionUID = 1L;

        private PersonEditDialog(String markupId, IModel<Person> model) {
            super(markupId, Model.of("Edit"), model);
        }

        @Override
        protected Form<Person> newForm(IModel<Person> model) {
            Form<Person> form = new Form<>("form", new CompoundPropertyModel<>(model));
            form.add(new TextField<>("name", String.class));
            form.add(new DateTimePicker("birthday", "y-MM-dd").setPickTime(false));
            return form;
        }
    }

    private class PersonDetailDialog extends Dialog<Person> {
        private static final long serialVersionUID = 1L;

        private PersonDetailDialog(String id, IModel<Person> model) {
            super(id, model);
        }

        @Override
        protected void onInitialize() {
            super.onInitialize();

            add(new Label("id", new PropertyModel<>(getModel(), "id")).setVisible(false)
                    .setOutputMarkupPlaceholderTag(true).setOutputMarkupId(true));
            add(new Label("name", new PropertyModel<>(getModel(), "name")));
            add(new DateLabel("birthday", new SimpleDateFormat("y-MM-dd"), new PropertyModel<Date>(getModel(),
                    "birthday")));

            prependButton(new JsAjaxLink<Void>(Dialog.BUTTON_MARKUP_ID) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void onInitialize() {
                    super.onInitialize();
                    add(new ButtonBehavior(Buttons.Type.INFO));
                }

                @Override
                public void onClick(AjaxRequestTarget target) {
                    Component container = PersonDetailDialog.this.get("id");
                    container.setVisible(true);
                    target.add(container);
                }
            }.setBody(Model.of("Show ID (AJAX)")));

            prependButton(new JsLink(Dialog.BUTTON_MARKUP_ID) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void onInitialize() {
                    super.onInitialize();
                    add(new ButtonBehavior(Buttons.Type.LINK));
                }

                @Override
                public IJavaScript onClick() {
                    return $(PersonDetailDialog.this.get("id")).toggle();
                }
            }.setBody(Model.of("Toggle ID box (JS)")));
        }
    }
}
