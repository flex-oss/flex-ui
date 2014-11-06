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

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.cdlflex.ui.markup.css.icon.GlyphIconType;
import org.cdlflex.ui.markup.css.icon.IconType;
import org.cdlflex.ui.markup.html.basic.Icon;
import org.cdlflex.ui.pages.ExamplePage;

/**
 * IconsPage.
 */
public class IconsPage extends ExamplePage {

    private static final long serialVersionUID = 1L;

    public IconsPage() {
        add(new ListView<IconType>("icons", getIconTypes()) {

            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(ListItem<IconType> item) {
                item.add(new Label("class", item.getModelObject().getCssClassName()));
                item.add(new Icon("icon", item.getModel()));
            }
        });
    }

    // CHECKSTYLE:OFF
    private List<IconType> getIconTypes() {
        // CHECKSTYLE:ON
        List<IconType> list = new ArrayList<>();

        list.add(GlyphIconType.ADJUST);
        list.add(GlyphIconType.ALIGNCENTER);
        list.add(GlyphIconType.ALIGNJUSTIFY);
        list.add(GlyphIconType.ALIGNLEFT);
        list.add(GlyphIconType.ALIGNRIGHT);
        list.add(GlyphIconType.ARROWDOWN);
        list.add(GlyphIconType.ARROWLEFT);
        list.add(GlyphIconType.ARROWRIGHT);
        list.add(GlyphIconType.ARROWUP);
        list.add(GlyphIconType.ASTERISK);
        list.add(GlyphIconType.BACKWARD);
        list.add(GlyphIconType.BANCIRCLE);
        list.add(GlyphIconType.BARCODE);
        list.add(GlyphIconType.BELL);
        list.add(GlyphIconType.BOLD);
        list.add(GlyphIconType.BOOK);
        list.add(GlyphIconType.BOOKMARK);
        list.add(GlyphIconType.BRIEFCASE);
        list.add(GlyphIconType.BULLHORN);
        list.add(GlyphIconType.CALENDAR);
        list.add(GlyphIconType.CAMERA);
        list.add(GlyphIconType.CERTIFICATE);
        list.add(GlyphIconType.CHECK);
        list.add(GlyphIconType.CHEVRONDOWN);
        list.add(GlyphIconType.CHEVRONLEFT);
        list.add(GlyphIconType.CHEVRONRIGHT);
        list.add(GlyphIconType.CHEVRONUP);
        list.add(GlyphIconType.CIRCLEARROWDOWN);
        list.add(GlyphIconType.CIRCLEARROWLEFT);
        list.add(GlyphIconType.CIRCLEARROWRIGHT);
        list.add(GlyphIconType.CIRCLEARROWUP);
        list.add(GlyphIconType.CLOUD);
        list.add(GlyphIconType.CLOUDDOWNLOAD);
        list.add(GlyphIconType.CLOUDUPLOAD);
        list.add(GlyphIconType.COG);
        list.add(GlyphIconType.COLLAPSEDOWN);
        list.add(GlyphIconType.COLLAPSEUP);
        list.add(GlyphIconType.COMMENT);
        list.add(GlyphIconType.COMPRESSED);
        list.add(GlyphIconType.COPYRIGHTMARK);
        list.add(GlyphIconType.CREDITCARD);
        list.add(GlyphIconType.CUTLERY);
        list.add(GlyphIconType.DASHBOARD);
        list.add(GlyphIconType.DOWNLOAD);
        list.add(GlyphIconType.DOWNLOADALT);
        list.add(GlyphIconType.EARPHONE);
        list.add(GlyphIconType.EDIT);
        list.add(GlyphIconType.EJECT);
        list.add(GlyphIconType.ENVELOPE);
        list.add(GlyphIconType.EURO);
        list.add(GlyphIconType.EXCLAMATIONSIGN);
        list.add(GlyphIconType.EXPAND);
        list.add(GlyphIconType.EXPORT);
        list.add(GlyphIconType.EYECLOSE);
        list.add(GlyphIconType.EYEOPEN);
        list.add(GlyphIconType.FACETIMEVIDEO);
        list.add(GlyphIconType.FASTBACKWARD);
        list.add(GlyphIconType.FASTFORWARD);
        list.add(GlyphIconType.FILE);
        list.add(GlyphIconType.FILM);
        list.add(GlyphIconType.FILTER);
        list.add(GlyphIconType.FIRE);
        list.add(GlyphIconType.FLAG);
        list.add(GlyphIconType.FLASH);
        list.add(GlyphIconType.FLOPPYDISK);
        list.add(GlyphIconType.FLOPPYOPEN);
        list.add(GlyphIconType.FLOPPYREMOVE);
        list.add(GlyphIconType.FLOPPYSAVE);
        list.add(GlyphIconType.FLOPPYSAVED);
        list.add(GlyphIconType.FOLDERCLOSE);
        list.add(GlyphIconType.FOLDEROPEN);
        list.add(GlyphIconType.FONT);
        list.add(GlyphIconType.FORWARD);
        list.add(GlyphIconType.FULLSCREEN);
        list.add(GlyphIconType.GBP);
        list.add(GlyphIconType.GIFT);
        list.add(GlyphIconType.GLASS);
        list.add(GlyphIconType.GLOBE);
        list.add(GlyphIconType.HANDDOWN);
        list.add(GlyphIconType.HANDLEFT);
        list.add(GlyphIconType.HANDRIGHT);
        list.add(GlyphIconType.HANDUP);
        list.add(GlyphIconType.HDVIDEO);
        list.add(GlyphIconType.HDD);
        list.add(GlyphIconType.HEADER);
        list.add(GlyphIconType.HEADPHONES);
        list.add(GlyphIconType.HEART);
        list.add(GlyphIconType.HEARTEMPTY);
        list.add(GlyphIconType.HOME);
        list.add(GlyphIconType.IMPORT);
        list.add(GlyphIconType.INBOX);
        list.add(GlyphIconType.INDENTLEFT);
        list.add(GlyphIconType.INDENTRIGHT);
        list.add(GlyphIconType.INFOSIGN);
        list.add(GlyphIconType.ITALIC);
        list.add(GlyphIconType.LEAF);
        list.add(GlyphIconType.LINK);
        list.add(GlyphIconType.LIST);
        list.add(GlyphIconType.LISTALT);
        list.add(GlyphIconType.LOCK);
        list.add(GlyphIconType.LOGIN);
        list.add(GlyphIconType.LOGOUT);
        list.add(GlyphIconType.MAGNET);
        list.add(GlyphIconType.MAPMARKER);
        list.add(GlyphIconType.MINUS);
        list.add(GlyphIconType.MINUSSIGN);
        list.add(GlyphIconType.MOVE);
        list.add(GlyphIconType.MUSIC);
        list.add(GlyphIconType.NEWWINDOW);
        list.add(GlyphIconType.OFF);
        list.add(GlyphIconType.OK);
        list.add(GlyphIconType.OKCIRCLE);
        list.add(GlyphIconType.OKSIGN);
        list.add(GlyphIconType.OPEN);
        list.add(GlyphIconType.PAPERCLIP);
        list.add(GlyphIconType.PAUSE);
        list.add(GlyphIconType.PENCIL);
        list.add(GlyphIconType.PHONE);
        list.add(GlyphIconType.PHONEALT);
        list.add(GlyphIconType.PICTURE);
        list.add(GlyphIconType.PLANE);
        list.add(GlyphIconType.PLAY);
        list.add(GlyphIconType.PLAYCIRCLE);
        list.add(GlyphIconType.PLUS);
        list.add(GlyphIconType.PLUSSIGN);
        list.add(GlyphIconType.PRINT);
        list.add(GlyphIconType.PUSHPIN);
        list.add(GlyphIconType.QRCODE);
        list.add(GlyphIconType.QUESTIONSIGN);
        list.add(GlyphIconType.RANDOM);
        list.add(GlyphIconType.RECORD);
        list.add(GlyphIconType.REFRESH);
        list.add(GlyphIconType.REGISTRATIONMARK);
        list.add(GlyphIconType.REMOVE);
        list.add(GlyphIconType.REMOVECIRCLE);
        list.add(GlyphIconType.REMOVESIGN);
        list.add(GlyphIconType.REPEAT);
        list.add(GlyphIconType.RESIZEFULL);
        list.add(GlyphIconType.RESIZEHORIZONTAL);
        list.add(GlyphIconType.RESIZESMALL);
        list.add(GlyphIconType.RESIZEVERTICAL);
        list.add(GlyphIconType.RETWEET);
        list.add(GlyphIconType.ROAD);
        list.add(GlyphIconType.SAVE);
        list.add(GlyphIconType.SAVED);
        list.add(GlyphIconType.SCREENSHOT);
        list.add(GlyphIconType.SDVIDEO);
        list.add(GlyphIconType.SEARCH);
        list.add(GlyphIconType.SEND);
        list.add(GlyphIconType.SHARE);
        list.add(GlyphIconType.SHAREALT);
        list.add(GlyphIconType.SHOPPINGCART);
        list.add(GlyphIconType.SIGNAL);
        list.add(GlyphIconType.SORT);
        list.add(GlyphIconType.SORTBYALPHABET);
        list.add(GlyphIconType.SORTBYALPHABETALT);
        list.add(GlyphIconType.SORTBYATTRIBUTES);
        list.add(GlyphIconType.SORTBYATTRIBUTESALT);
        list.add(GlyphIconType.SORTBYORDER);
        list.add(GlyphIconType.SORTBYORDERALT);
        list.add(GlyphIconType.SOUND);
        list.add(GlyphIconType.SOUNDDOLBY);
        list.add(GlyphIconType.SOUNDSTEREO);
        list.add(GlyphIconType.STAR);
        list.add(GlyphIconType.STAREMPTY);
        list.add(GlyphIconType.STATS);
        list.add(GlyphIconType.STEPBACKWARD);
        list.add(GlyphIconType.STEPFORWARD);
        list.add(GlyphIconType.STOP);
        list.add(GlyphIconType.SUBTITLES);
        list.add(GlyphIconType.TAG);
        list.add(GlyphIconType.TAGS);
        list.add(GlyphIconType.TASKS);
        list.add(GlyphIconType.TEXTHEIGHT);
        list.add(GlyphIconType.TEXTWIDTH);
        list.add(GlyphIconType.TH);
        list.add(GlyphIconType.THLARGE);
        list.add(GlyphIconType.THLIST);
        list.add(GlyphIconType.THUMBSDOWN);
        list.add(GlyphIconType.THUMBSUP);
        list.add(GlyphIconType.TIME);
        list.add(GlyphIconType.TINT);
        list.add(GlyphIconType.TOWER);
        list.add(GlyphIconType.TRANSFER);
        list.add(GlyphIconType.TRASH);
        list.add(GlyphIconType.TREECONIFER);
        list.add(GlyphIconType.TREEDECIDUOUS);
        list.add(GlyphIconType.UNCHECKED);
        list.add(GlyphIconType.UPLOAD);
        list.add(GlyphIconType.USD);
        list.add(GlyphIconType.USER);
        list.add(GlyphIconType.VOLUMEDOWN);
        list.add(GlyphIconType.VOLUMEOFF);
        list.add(GlyphIconType.VOLUMEUP);
        list.add(GlyphIconType.WARNINGSIGN);
        list.add(GlyphIconType.WRENCH);
        list.add(GlyphIconType.ZOOMIN);
        list.add(GlyphIconType.ZOOMOUT);

        return list;
    }
}
