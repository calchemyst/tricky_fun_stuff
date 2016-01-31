package pegasus;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by annafuller on 1/13/16.
 */
public final class Store implements Location {
    private final String type;
    private final String name;
    private final Set<String> validCommands;

    public Store(String name) {
        this.name = name;
        this.type = "store";
        this.validCommands = Sets.newHashSet("buy", "sell", "inventory");
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
        if (input.equals("buy")) {
            return "Store is out of everything";
        } else if (input.equals("sell")) {
            return "We don't need no stinking stuff.";
        } else if (input.equals("inventory")) {
            return "No inventory for you....";
        } else  {
            return "How indecent!";
        }
    }
}
