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
package org.cdlflex.ui.markup.css.icon;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class IconTypeTest {

    @BeforeClass
    public static void loadClasses() throws Exception {
        assertNotNull(GlyphIconType.HOME);
        assertNotNull(FontAwesomeIconType.HOME);
    }

    @Test
    public void forName_withFullKey_returnsCorrectIconType() throws Exception {
        assertSame(GlyphIconType.HOME, IconType.forName("GlyphIconType.home"));
        assertSame(FontAwesomeIconType.HOME, IconType.forName("FontAwesomeIconType.home"));
    }

    @Test
    public void forName_nonExistingKey_returnsNull() throws Exception {
        assertNull(IconType.forName("some-icon-that-does-not-exist"));
    }

    @Test
    public void forName_ambiguousPartialKey_returnsEither() throws Exception {
        IconType type = IconType.forName("home");

        assertTrue("Unexpected IconType " + type + " returned", type == GlyphIconType.HOME
            || type == FontAwesomeIconType.HOME); // it's not deterministic in general
    }

    @Test
    public void forName_partialKey_returnsCorrectIconType() throws Exception {
        assertSame(FontAwesomeIconType.YOUTUBE_PLAY, IconType.forName("youtube-play"));
    }
}
