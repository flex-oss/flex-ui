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
package org.cdlflex.ui.util.compare;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

public class DefaultModelPropertyValueComparatorTest {
    @Test
    public void compare_behavesCorrectly() throws Exception {
        Comparator<TestModel> comparator = new DefaultModelPropertyValueComparator<>("name");

        TestModel a = new TestModel("a");
        TestModel b = new TestModel("b");

        assertEquals(0, comparator.compare(a, new TestModel("a")));
        assertEquals(-1, comparator.compare(a, b));
        assertEquals(1, comparator.compare(b, a));
    }

    @Test
    public void compare_nestedProperty_behavesCorrectly() throws Exception {
        Comparator<TestModel> comparator = new DefaultModelPropertyValueComparator<>("nested.number");

        TestModel a0 = new TestModel("a", new NestedTestModel(0));
        TestModel a1 = new TestModel("a", new NestedTestModel(1));

        assertEquals(0, comparator.compare(a0, new TestModel("b", new NestedTestModel(0))));
        assertEquals(-1, comparator.compare(a0, a1));
        assertEquals(1, comparator.compare(a1, a0));
    }

    @Test
    public void compare_nonComparableProperty_fallsBackToStringComparison() throws Exception {
        // nested model has a toString method
        Comparator<TestModel> comparator = new DefaultModelPropertyValueComparator<>("nested");

        TestModel a0 = new TestModel("a", new NestedTestModel(0));
        TestModel a1 = new TestModel("a", new NestedTestModel(1));

        assertEquals(0, comparator.compare(a0, new TestModel("b", new NestedTestModel(0))));
        assertEquals(-1, comparator.compare(a0, a1));
        assertEquals(1, comparator.compare(a1, a0));
    }

}
