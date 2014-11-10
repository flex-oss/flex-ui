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

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.behavior.ButtonBehavior;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.html.dialog.Dialog;
import org.cdlflex.ui.markup.html.dialog.DialogOpenLink;
import org.cdlflex.ui.model.Person;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * DialogsPage.
 */
public class DialogsPage extends ExamplePage {
    private static final long serialVersionUID = 1L;

    public DialogsPage() {
        Dialog<Person> dialog = new PersonDialog("dialog");

        dialog.addButton(new AbstractLink(Dialog.BUTTON_MARKUP_ID) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onInitialize() {
                super.onInitialize();
                add(new ButtonBehavior(Buttons.Type.PRIMARY));
            }
        }.setBody(Model.of("Save changes")));

        dialog.addCloseButton(Model.of("Close"));

        dialog.setTitle(Model.of("Person"));

        add(dialog);
        add(new DialogOpenLink("open-button", dialog));
    }

    private class PersonDialog extends Dialog<Person> {
        private static final long serialVersionUID = 1L;

        private PersonDialog(String id) {
            super(id);
        }

        private PersonDialog(String id, IModel<Person> model) {
            super(id, model);
        }
    }
}
