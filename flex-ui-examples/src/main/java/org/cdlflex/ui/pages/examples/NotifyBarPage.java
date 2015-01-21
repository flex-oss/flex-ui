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
import java.util.List;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.Model;
import org.cdlflex.ui.markup.html.badge.notifybar.INavigatableNotifyBarComponent;
import org.cdlflex.ui.markup.html.badge.notifybar.INotifyBarComponent;
import org.cdlflex.ui.markup.html.badge.notifybar.INotifyBarComponentProvider;
import org.cdlflex.ui.markup.html.badge.notifybar.NotifyBar;
import org.rauschig.wicketjs.IJavaScript;
import org.rauschig.wicketjs.JsCall;
import org.rauschig.wicketjs.markup.html.JsLink;

/**
 * NotifyBarPage
 */
public class NotifyBarPage extends StripTagExamplePage {
    private static final long serialVersionUID = 1L;

    public NotifyBarPage() {
        add(new NotifyBar("notify-bar", new NotifyBarComponentProvider()));
    }

    private class NotifyBarComponentProvider implements INotifyBarComponentProvider {
        private static final long serialVersionUID = 1L;

        @Override
        public List<INotifyBarComponent> getComponents() {
            return Arrays.asList(new INotifyBarComponent() {
                private static final long serialVersionUID = 1L;

                @Override
                public int getPriority() {
                    return DEFAULT_PRIORITY;
                }

                @Override
                public boolean isVisible() {
                    return true;
                }

                @Override
                public String getLabel() {
                    return "Just 10";
                }

                @Override
                public Object getValue() {
                    return 10;
                }
            }, new INotifyBarComponent() {
                private static final long serialVersionUID = 1L;

                @Override
                public int getPriority() {
                    return 10;
                }

                @Override
                public boolean isVisible() {
                    return true;
                }

                @Override
                public String getLabel() {
                    return "Shows a model";
                }

                @Override
                public Object getValue() {
                    return Model.of(42);
                }
            }, new INotifyBarComponent() {
                private static final long serialVersionUID = 1L;

                @Override
                public int getPriority() {
                    return 100;
                }

                @Override
                public boolean isVisible() {
                    return false;
                }

                @Override
                public String getLabel() {
                    return "Invisible";
                }

                @Override
                public Object getValue() {
                    return Model.of(42);
                }
            }, new INavigatableNotifyBarComponent() {
                private static final long serialVersionUID = 1L;

                @Override
                public int getPriority() {
                    return 5;
                }

                @Override
                public boolean isVisible() {
                    return true;
                }

                @Override
                public AbstractLink createLink(String id) {
                    return new JsLink(id) {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public IJavaScript onClick() {
                            return new JsCall("alert", "I was clicked!");
                        }
                    };
                }

                @Override
                public String getLabel() {
                    return "With a link";
                }

                @Override
                public Object getValue() {
                    return "String value";
                }
            });
        }
    }

}
