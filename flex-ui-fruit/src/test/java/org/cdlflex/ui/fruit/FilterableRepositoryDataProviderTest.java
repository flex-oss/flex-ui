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
package org.cdlflex.ui.fruit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.Model;
import org.cdlflex.fruit.Filter;
import org.cdlflex.fruit.OrderBy;
import org.cdlflex.fruit.Repository;
import org.cdlflex.ui.fruit.model.FilterModel;
import org.cdlflex.ui.fruit.model.IFilterProvider;
import org.junit.Before;
import org.junit.Test;

public class FilterableRepositoryDataProviderTest {

    FilterableRepositoryDataProvider<TestModel> provider;

    Filter mockFilter;
    Repository<TestModel> mockRepository;

    TestModel model1 = new TestModel(1L);
    TestModel model2 = new TestModel(2L);
    TestModel model3 = new TestModel(3L);

    @Before
    public void setUp() throws Exception {
        mockFilter = new Filter(); // simulates a query that returns model1 and model3
        mockRepository = spy(new StubRepository());

        provider = new FilterableRepositoryDataProvider<TestModel>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Repository<TestModel> getRepository() {
                return mockRepository;
            }
        };
    }

    @Test
    public void getFilter_withNoFilterSet_returnsNull() throws Exception {
        assertNull(provider.getFilter());
    }

    @Test
    public void getFilter_withFilterSet_returnsCorrectFilter() throws Exception {
        provider.setFilterModel(Model.of(mockFilter));
        assertSame(mockFilter, provider.getFilter());
    }

    @Test
    public void getFilter_withFilterModelFromProvider_returnsCorrectFilter() throws Exception {
        provider.setFilterModel(new FilterModel(new IFilterProvider() {
            private static final long serialVersionUID = 1L;

            @Override
            public Filter getFilter() {
                return mockFilter;
            }
        }));

        assertSame(mockFilter, provider.getFilter());
    }

    @Test
    public void iterator_withoutFilter_returnsCorrectModels() throws Exception {
        Iterator<? extends TestModel> iterator = provider.iterator(0, 3);

        assertSame(model1, iterator.next());
        assertSame(model2, iterator.next());
        assertSame(model3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_withFilter_returnsCorrectModels() throws Exception {
        provider.setFilterModel(Model.of(mockFilter));

        Iterator<? extends TestModel> iterator = provider.iterator(0, 2);

        assertSame(model1, iterator.next());
        assertSame(model3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void size_withoutFilter_returnsUnfilteredRepositorySize() throws Exception {
        assertEquals(3L, provider.size());
    }

    @Test
    public void size_withFilter_returnsFilteredRepositorySize() throws Exception {
        provider.setFilterModel(Model.of(mockFilter));
        assertEquals(2L, provider.size());
    }

    private class StubRepository implements Repository<TestModel> {
        @Override
        public TestModel create() {
            return null;
        }

        @Override
        public long count() {
            return 3L;
        }

        @Override
        public long count(Filter filter) {
            if (filter == mockFilter) {
                return 2;
            } else {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public void save(TestModel entity) {

        }

        @Override
        public void save(Collection<TestModel> entity) {

        }

        @Override
        public void remove(TestModel entity) {

        }

        @Override
        public void remove(Collection<TestModel> entities) {

        }

        @Override
        public TestModel get(Object id) {
            if (id == 1) {
                return model1;
            } else if (id == 2) {
                return model2;
            } else if (id == 3) {
                return model3;
            } else {
                return null;
            }
        }

        @Override
        public List<TestModel> getAll() {
            return new ArrayList<>(Arrays.asList(model1, model2, model3));
        }

        @Override
        public List<TestModel> getAll(OrderBy order) {
            return getAll();
        }

        @Override
        public List<TestModel> getPage(int limit, int offset) {
            return getAll();
        }

        @Override
        public List<TestModel> getPage(OrderBy order, int limit, int offset) {
            return getPage(limit, offset);
        }

        @Override
        public List<TestModel> find(Filter filter) {
            return (filter == mockFilter) ? new ArrayList<>(Arrays.asList(model1, model3))
                : new ArrayList<TestModel>();
        }

        @Override
        public List<TestModel> findPage(Filter filter, OrderBy order, int limit, int offset) {
            if (filter == mockFilter) {
                return new ArrayList<>(Arrays.asList(model1, model3)).subList(offset, offset + limit);
            } else {
                return new ArrayList<>();
            }
        }
    }
}
