#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.todo;

import ${package}.model.ToDoItem;

import java.util.List;
import java.util.Optional;

public interface ToDoItemsStore {

    Optional<ToDoItem> findById(String id);

    void save(ToDoItem item);

    List<ToDoItem> findAll();

    void deleteById(String id);
}
