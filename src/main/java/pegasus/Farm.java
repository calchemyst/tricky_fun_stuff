package pegasus;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * Created by annafuller on 1/6/16.
 */
public class Farm implements Location {
    private final Set<String> validCommands;
    private final String name;
    private final String type;

    public Farm(String name) {
        this.name = name;
        this.validCommands = Sets.newHashSet("harvest", "plant");
        this.type = "farm";
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
}
