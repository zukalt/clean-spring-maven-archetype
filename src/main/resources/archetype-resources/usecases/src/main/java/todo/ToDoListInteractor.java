#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.todo;

import ${package}.gen.ListGenerator;
import ${package}.model.ToDoItem;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class ToDoListInteractor {

    private ToDoItemsStore itemsStore;
    private ListGenerator listGenerator;


    public ToDoListInteractor(ToDoItemsStore itemsStore, ListGenerator listGenerator) {
        this.itemsStore = itemsStore;
        this.listGenerator = listGenerator;
    }

    public void addItem(String description) {
        String id = generateId(description);

        ToDoItem item = itemsStore.findById(id).orElse(new ToDoItem(id, description, false));
        item.setDone(false);
        itemsStore.save(item);
    }

    public List<ToDoItem> getAll() {
        return itemsStore.findAll();
    }

    public void delete(String itemId) {
        itemsStore.deleteById(itemId);
    }

    public void update(String id, String description, boolean done) {
        itemsStore.findById(id).ifPresent(item->{
            item.setDescription(description);
            item.setDone(done);
            itemsStore.save(item);
        });
    }

    public void generateSome() {
        listGenerator.findPopularToDoList().forEach(this::addItem);
    }

    private String generateId(String description) {
        return DigestUtils.sha1Hex(description);
    }
}
