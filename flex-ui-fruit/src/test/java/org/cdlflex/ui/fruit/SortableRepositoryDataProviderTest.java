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
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.cdlflex.fruit.OrderBy;
import org.cdlflex.fruit.Repository;
import org.cdlflex.fruit.SortOrder;
import org.cdlflex.fruit.SortSpecification;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

public class SortableRepositoryDataProviderTest {
    SortableRepositoryDataProvider<TestModel> provider;

    @Spy
    Repository<TestModel> mockRepository;

    TestModel model1 = new TestModel(1L);
    TestModel model2 = new TestModel(2L);
    TestModel model3 = new TestModel(3L);

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        mockRepository = mock(Repository.class);
        when(mockRepository.count()).thenReturn(3L);

        when(mockRepository.get(1L)).thenReturn(model1);
        when(mockRepository.get(2L)).thenReturn(model2);
        when(mockRepository.get(3L)).thenReturn(model3);

        when(mockRepository.getAll()).thenReturn(list(model1, model2, model3));

        when(mockRepository.getPage(2, 0)).thenReturn(list(model1, model2));
        when(mockRepository.getPage(4, 0)).thenReturn(list(model1, model2, model3));

        when(mockRepository.getPage(2, 1)).thenReturn(list(model2, model3));
        when(mockRepository.getPage(4, 1)).thenReturn(list(model2, model3));

        provider = new SortableRepositoryDataProvider<TestModel>() {
            @Override
            public Repository<TestModel> getRepository() {
                return mockRepository;
            }

            private static final long serialVersionUID = 1L;
        };
    }

    @Test
    public void iterator_withSort_callsCorrectRepositoryMethod() throws Exception {
        provider.setSort(new SortParam<>("id", true));
        provider.iterator(0, 3);
        verify(mockRepository, times(0)).getPage(anyInt(), anyInt());
        verify(mockRepository, times(1)).getPage(any(OrderBy.class), eq(3), eq(0));
    }

    @Test
    public void iterator_withoutSort_callsCorrectRepositoryMethod() throws Exception {
        provider.iterator(0, 3);
        verify(mockRepository, times(1)).getPage(3, 0);
        verify(mockRepository, times(0)).getPage(any(OrderBy.class), anyInt(), anyInt());
    }

    @Test
    public void iterator_countAboveRepositorySize_withoutSort_returnsCorrectPage() throws Exception {
        Iterator<? extends TestModel> iterator = provider.iterator(0L, 4L);

        assertSame(model1, iterator.next());
        assertSame(model2, iterator.next());
        assertSame(model3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_countBelowRepositorySize_withoutSort_returnsCorrectPage() throws Exception {
        Iterator<? extends TestModel> iterator = provider.iterator(0L, 2L);

        assertSame(model1, iterator.next());
        assertSame(model2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_countAboveRepositorySize_withOffset_withoutSort_returnsCorrectPage() throws Exception {
        Iterator<? extends TestModel> iterator = provider.iterator(1L, 4L);

        assertSame(model2, iterator.next());
        assertSame(model3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_countBelowRepositorySize_withOffset_withoutSort_returnsCorrectPage() throws Exception {
        Iterator<? extends TestModel> iterator = provider.iterator(1L, 2L);

        assertSame(model2, iterator.next());
        assertSame(model3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void size_returnsRepositoryCountResult() throws Exception {
        assertEquals(3L, provider.size());
    }

    @Test
    public void getOrderBy_ascending_createsOrderByInstanceCorrectly() throws Exception {
        OrderBy order = provider.getOrderBy(new SortParam<>("id", true));

        List<SortSpecification> sorts = new ArrayList<>(order.getSort());
        assertEquals(1, sorts.size());
        assertEquals("id", sorts.get(0).getKey());
        assertEquals(SortOrder.ASC, sorts.get(0).getSortOrder());
    }

    @Test
    public void getOrderBy_descending_createsOrderByInstanceCorrectly() throws Exception {
        OrderBy order = provider.getOrderBy(new SortParam<>("id", false));

        List<SortSpecification> sorts = new ArrayList<>(order.getSort());
        assertEquals(1, sorts.size());
        assertEquals("id", sorts.get(0).getKey());
        assertEquals(SortOrder.DESC, sorts.get(0).getSortOrder());
    }

    @Test
    public void model_createsLoadableDetachableModelCorrectly() throws Exception {
        IModel<TestModel> model = provider.model(model1);

        assertTrue("Model should be a LoadableDetachableModel", model instanceof LoadableDetachableModel);
        assertSame(model1, model.getObject());
        verify(mockRepository, times(1)).get(1L); // repository was called to load the model object by its id
    }

    @Test
    public void model_withNullId_createsDefaultModel() throws Exception {
        TestModel newTestModel = new TestModel();

        IModel<TestModel> model = provider.model(newTestModel);
        assertFalse("Model should not be a LoadableDetachableModel", model instanceof LoadableDetachableModel);
        assertSame(newTestModel, model.getObject());
    }

    @Test
    public void getOrderBy_onNullSortParam_returnsNull() throws Exception {
        assertNull(provider.getOrderBy(null));
    }

    private List<TestModel> list(TestModel... models) {
        return new ArrayList<>(Arrays.asList(models));
    }
}
