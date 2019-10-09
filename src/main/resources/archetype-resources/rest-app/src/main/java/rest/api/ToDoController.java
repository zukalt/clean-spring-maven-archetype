#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.api;

import ${package}.model.ToDoItem;
import ${package}.todo.ToDoListInteractor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/todos", produces = "application/json")
@Transactional
public class ToDoController {


    private ToDoListInteractor todoList ;

    public ToDoController(ToDoListInteractor todoList) {
        this.todoList = todoList;
    }


    static class NewToDoItemDto {
        public String description;
    }
    static class ToDoItemDto extends NewToDoItemDto {
        public boolean done;
    }


    @PostMapping("")
    public void add(@RequestBody NewToDoItemDto item) {
        todoList.addItem(item.description);
    }

    @GetMapping("")
    public List<ToDoItem> all() {
        return todoList.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody ToDoItemDto item) {
        todoList.update(id, item.description, item.done);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        todoList.delete(id);
    }

    @GetMapping("/random")
    public List<ToDoItem> generateRandom() {
        todoList.generateSome();
        return todoList.getAll();
    }
}
