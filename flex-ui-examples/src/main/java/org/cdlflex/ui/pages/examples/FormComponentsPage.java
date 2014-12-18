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

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.cdlflex.ui.markup.css.Buttons;
import org.cdlflex.ui.markup.html.form.ButtonMultipleChoice;
import org.cdlflex.ui.markup.html.form.ButtonRadioChoice;
import org.cdlflex.ui.markup.html.form.ClassChoiceRenderer;
import org.cdlflex.ui.markup.html.form.DateTimePicker;
import org.cdlflex.ui.markup.html.form.EnumDropDownChoice;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * FormComponentsPage.
 */
public class FormComponentsPage extends ExamplePage {

    private static final long serialVersionUID = 1L;

    public FormComponentsPage() {
        Form<Void> form = new Form<>("form");

        form.add(new DateTimePicker("date-time-picker", "y-MM-dd HH:mm"));
        form.add(new DateTimePicker("date-picker", "dd.MM.y").setPickTime(false));
        form.add(new DateTimePicker("time-picker", "HH:mm").setPickDate(false));

        form.add(new ButtonMultipleChoice<>("button-multiple-choice", Arrays.asList("Choice A", "Choice B",
                "Choice C", "Choice D")));

        form.add(new ButtonRadioChoice<>("button-radio-choice", Arrays.asList("Choice A", "Choice B", "Choice C",
                "Choice D")));

        form.add(new EnumDropDownChoice<>("enum-drop-down", Buttons.Type.class));
        form.add(new DropDownChoice<>("class-drop-down", Arrays.asList(ButtonMultipleChoice.class,
                ButtonRadioChoice.class, DropDownChoice.class), new ClassChoiceRenderer()));

        add(form);
    }

}
