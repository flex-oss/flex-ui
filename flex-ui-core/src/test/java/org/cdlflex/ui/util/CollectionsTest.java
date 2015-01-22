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
package org.cdlflex.ui.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cdlflex.ui.util.Collections.Callback;
import org.cdlflex.ui.util.Collections.CheckedCallback;
import org.cdlflex.ui.util.Collections.CheckedVisitor;
import org.cdlflex.ui.util.Collections.NotNullFilter;
import org.cdlflex.ui.util.Collections.Predicate;
import org.cdlflex.ui.util.Collections.TerminatingCheckedVisitor;
import org.cdlflex.ui.util.Collections.VisitorAction;
import org.junit.Test;

public class CollectionsTest {
    @Test
    public void emptyList_returnsImmutableEmptyList() throws Exception {
        List<Object> empty = Collections.emptyList();
        assertTrue(empty.isEmpty());
        try {
            empty.add("Foo");
            fail("Was able to add to emptyList");
        } catch (UnsupportedOperationException e) {
            // pass
        }
    }

    @Test
    public void emptyMap_returnsImmutableEmptyMap() throws Exception {
        Map<Object, Object> empty = Collections.emptyMap();
        assertTrue(empty.isEmpty());
        try {
            empty.put("Foo", "Bar");
            fail("Was able to put into emptyMap");
        } catch (UnsupportedOperationException e) {
            // pass
        }
    }

    @Test
    public void isEmpty_onNullObject_returnsTrue() throws Exception {
        assertTrue(Collections.isEmpty(null));
    }

    @Test
    public void isEmpty_onEmptyList_returnsTrue() throws Exception {
        assertTrue(Collections.isEmpty(new ArrayList<>()));
    }

    @Test
    public void isEmpty_onNonEmptyList_returnsFalse() throws Exception {
        assertFalse(Collections.isEmpty(new ArrayList<>(Arrays.asList("Foo"))));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void walk_checkedVisitor_callsVisitorOnEveryElement() throws Exception {
        List<String> list = Arrays.asList("Foo", "Bar");
        final List<String> verify = new ArrayList<>();
        Collections.walk(list, new CheckedVisitor<String, Exception>() {
            @Override
            public void visit(String object) throws Exception {
                verify.add(object);
            }
        });
        assertEquals(2, verify.size());
        assertTrue(verify.containsAll(list));
    }

    @Test
    public void walk_terminatingVisitor_terminatesCorrectly() throws Exception {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4);
        final List<Integer> verify = new ArrayList<>();
        Collections.walk(list, new TerminatingCheckedVisitor<Integer, Exception>() {
            @Override
            public VisitorAction call(Integer object) throws Exception {
                verify.add(object);
                return (object >= 2) ? VisitorAction.BREAK : VisitorAction.CONTINUE;
            }
        });
        assertEquals(3, verify.size());
        assertEquals(0, (int) verify.get(0));
        assertEquals(1, (int) verify.get(1));
        assertEquals(2, (int) verify.get(2));
    }

    @Test
    public void map_callsMethodOfCallbackCorrectly() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<String> verify = Collections.map(list, new Callback<Integer, String>() {
            @Override
            public String call(Integer object) {
                return String.valueOf(object);
            }
        });
        assertEquals(3, verify.size());
        assertEquals("1", verify.get(0));
        assertEquals("2", verify.get(1));
        assertEquals("3", verify.get(2));
    }

    @Test
    public void map_checkedCallback_callsMethodOfCallbackCorrectly() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<String> verify = Collections.map(list, new CheckedCallback<Integer, String, Exception>() {
            @Override
            public String call(Integer object) throws Exception {
                return String.valueOf(object);
            }
        });
        assertEquals(3, verify.size());
        assertEquals("1", verify.get(0));
        assertEquals("2", verify.get(1));
        assertEquals("3", verify.get(2));
    }

    @Test
    public void map_checkedCallback_throwsExceptionCorrectly() throws Exception {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<String> verify = null;
        try {
            verify = Collections.map(list, new CheckedCallback<Integer, String, Exception>() {
                @Override
                public String call(Integer object) throws Exception {
                    if (object > 2) {
                        throw new Exception("expected");
                    }
                    return String.valueOf(object);
                }
            });
            fail("Exception not thrown");
        } catch (Exception e) {
            assertEquals("Did not catch correct exception", "expected", e.getMessage());
        }
        assertNull(verify);
    }

    @Test
    public void filter_behavesCorrectly() throws Exception {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> verify = Collections.filter(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object % 2 == 0;
            }
        });
        assertEquals(3, verify.size());
        assertEquals(0, (int) verify.get(0));
        assertEquals(2, (int) verify.get(1));
        assertEquals(4, (int) verify.get(2));
    }

    @Test
    public void retain_modifiesListCorrectly() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        Collections.retain(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object % 2 == 0;
            }
        });

        assertEquals(3, list.size());
        assertEquals(0, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(4, (int) list.get(2));
    }

    @Test
    public void removes_modifiesListCorrectly() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        Collections.remove(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object % 2 != 0;
            }
        });

        assertEquals(3, list.size());
        assertEquals(0, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(4, (int) list.get(2));
    }

    @Test
    public void count_returnsCorrectCount() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        int count = Collections.count(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object % 2 == 0;
            }
        });

        assertEquals(3, count);
    }

    @Test
    public void first_returnsCorrectObject() throws Exception {
        Integer three = 3;
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, three, 4));

        Integer first = Collections.first(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object > 2;
            }
        });

        assertSame(three, first);
    }

    @Test
    public void all_onSatisifedPredicate_returnsTrue() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        assertTrue(Collections.all(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object < 5;
            }
        }));
    }

    @Test
    public void all_onUnsatisifedPredicate_returnsFalse() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        assertFalse(Collections.all(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object % 2 == 0;
            }
        }));
    }

    @Test
    public void any_onSatisifedPredicate_returnsTrue() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        assertTrue(Collections.any(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object % 2 == 0;
            }
        }));
    }

    @Test
    public void any_onUnsatisifedPredicate_returnsFalse() throws Exception {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));

        assertFalse(Collections.any(list, new Predicate<Integer>() {
            @Override
            public Boolean call(Integer object) {
                return object > 4;
            }
        }));
    }

    @Test
    public void asList_convertsSetCorrectly() throws Exception {
        Set<Integer> set = new LinkedHashSet<>(Arrays.asList(0, 1, 2));
        List<Integer> verify = Collections.asList(set);

        assertEquals(3, verify.size());
        assertEquals(0, (int) verify.get(0));
        assertEquals(1, (int) verify.get(1));
        assertEquals(2, (int) verify.get(2));
    }

    @Test
    public void notNullFilter_behavesCorrectly() throws Exception {
        NotNullFilter<String> filter = new NotNullFilter<>();
        assertTrue(filter.call(""));
        assertTrue(filter.call("Foo"));
        assertFalse(filter.call(null));
    }
}
