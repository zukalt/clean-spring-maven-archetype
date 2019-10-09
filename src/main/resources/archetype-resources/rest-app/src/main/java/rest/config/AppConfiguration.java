#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.config;

import ${package}.gen.FixturesListGenerator;
import ${package}.gen.ListGenerator;
import ${package}.todo.ToDoItemsStore;
import ${package}.todo.ToDoListInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    ToDoListInteractor newToDoListInteractor(ListGenerator listGenerator, ToDoItemsStore todoStore) {
        return new ToDoListInteractor(todoStore, listGenerator);
    }

    @Bean
    ListGenerator fixtureBasedListGenerator() {
        return new FixturesListGenerator();
    }
}
