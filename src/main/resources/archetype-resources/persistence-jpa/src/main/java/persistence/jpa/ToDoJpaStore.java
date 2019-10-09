#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.persistence.jpa;

import ${package}.model.ToDoItem;
import ${package}.todo.ToDoItemsStore;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface ToDoJpaStore extends ToDoItemsStore, Repository<ToDoItem, String> {

}
