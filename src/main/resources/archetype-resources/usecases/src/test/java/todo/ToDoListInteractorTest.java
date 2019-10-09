#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.todo;

import ${package}.gen.ListGenerator;
import ${package}.model.ToDoItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoListInteractorTest {

    @InjectMocks
    ToDoListInteractor todoList;

    @Mock
    ListGenerator listGenerator;


    @Spy
    ToDoItemsStore itemsStore = new ToDoItemsStore() {
        Map<String,ToDoItem> itemMap = new HashMap<>();
        @Override
        public Optional<ToDoItem> findById(String id) {
            return Optional.ofNullable(itemMap.get(id));
        }

        @Override
        public void save(ToDoItem item) {
            itemMap.put(item.getId(), item);
        }

        @Override
        public List<ToDoItem> findAll() {
            return new ArrayList<>(itemMap.values());
        }

        @Override
        public void deleteById(String id) {
            itemMap.remove(id) ;
        }
    };

    @Before
    public void before() {

    }

    @Test
    public void crudTest() {


        assertTrue("Should be empty at start", todoList.getAll().isEmpty());
        todoList.addItem("item1");

        verify(itemsStore, times(1)).save(any(ToDoItem.class));

        assertEquals(1, todoList.getAll().size());
        ToDoItem item = todoList.getAll().get(0);
        assertNotNull(item.getId());
        assertEquals("item1", item.getDescription());
        assertFalse(item.isDone());

        todoList.delete(item.getId());

        assertTrue("Should be empty after deleting single item", todoList.getAll().isEmpty());
    }

    @Test
    public void testGeneration() {
        String[] items = new String[] {"Test", "Test Again"};
        when(listGenerator.findPopularToDoList()).thenReturn(Arrays.asList(items));

        todoList.generateSome();

        List<ToDoItem> stored = todoList.getAll();
        assertEquals(items.length, stored.size());
        for (int i = 0; i < items.length; i++) {
            assertEquals(items[i], stored.get(i).getDescription());
            assertNotNull(stored.get(i).getId());
            assertFalse(stored.get(i).isDone());
        }

    }
}