package pegasus;

import java.util.Set;

/**
 * Created by annafuller on 1/13/16.
 */
public interface Location {

    Set<String> commands();

    String name();

    String type();

    String handleCommmand(String input);
}
