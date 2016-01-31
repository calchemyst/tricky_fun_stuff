package pegasus;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by annafuller on 1/13/16.
 */
public final class Dungeon implements Location {
    private final String name;
    private final String type;
    private final Set<String> validCommands;

    public Dungeon(String name) {
        this.name = name;
        this.type = "dungeon";
        this.validCommands  = Sets.newHashSet("despair");
    }

    @Override
    public Set<String> commands() {
        return validCommands;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String handleCommmand(String input) {
        if (input.equals("despair")) {
            return "Good, good. The end is near. Despair more.";
        } else {
            return "Why aren't you full of despair in dungeon, dammit?";
        }
    }
}
