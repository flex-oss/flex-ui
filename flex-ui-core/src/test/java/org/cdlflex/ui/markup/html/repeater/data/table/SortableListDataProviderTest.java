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
package org.cdlflex.ui.markup.html.repeater.data.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;

public class SortableListDataProviderTest {

    SortableListDataProvider<Person, String> provider;
    List<Person> data;

    @Before
    public void setUp() throws Exception {
        data =
            new ArrayList<>(Arrays.asList(new Person("A", "2001-01-01"), new Person("B", "2003-03-03"), new Person(
                    "C", "2002-02-02"), new Person("D", "2004-04-04")));

        provider = new SortableListDataProvider<>(data);
    }

    @Test
    public void iterator_afterSettingSortState_returnsElementsInCorrectOrder() throws Exception {
        provider.getSortState().setPropertySortOrder("name", SortOrder.DESCENDING);

        Iterator<? extends Person> iterator = provider.iterator(0, 4);

        assertEquals("D", iterator.next().getName());
        assertEquals("C", iterator.next().getName());
        assertEquals("B", iterator.next().getName());
        assertEquals("A", iterator.next().getName());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_afterSettingSortState_ascending_returnsElementsInCorrectOrder() throws Exception {
        provider.getSortState().setPropertySortOrder("birthday", SortOrder.ASCENDING);
        Iterator<? extends Person> iterator = provider.iterator(0, 4);

        assertEquals("A", iterator.next().getName());
        assertEquals("C", iterator.next().getName());
        assertEquals("B", iterator.next().getName());
        assertEquals("D", iterator.next().getName());

        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_withOffsetAndLimit_returnsCorrectSublist() throws Exception {
        Iterator<? extends Person> iterator = provider.iterator(1, 2);

        assertEquals("B", iterator.next().getName());
        assertEquals("C", iterator.next().getName());

        assertFalse(iterator.hasNext());
    }
}
