#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.gen;

import lombok.extern.apachecommons.CommonsLog;

import java.util.List;
import java.util.Random;
import static java.util.Arrays.asList;

@CommonsLog
public class FixturesListGenerator implements ListGenerator {

    private List<List<String>> ALWAYS_WORTH_DOING_LISTS = asList(
            asList("Don't worry", "Be Happy"),
            asList("Go and See", "See and Conquer", "Celebrate")
    );

    @Override
    public List<String> findPopularToDoList() {
        if (log.isDebugEnabled()) {
            log.debug("Generating simple fixture based todo list");
        }
        return ALWAYS_WORTH_DOING_LISTS.get(randomIndex());
    }

    private int randomIndex() {
        return new Random().nextInt(ALWAYS_WORTH_DOING_LISTS.size());
    }
}
