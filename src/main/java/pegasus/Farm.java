package pegasus;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by annafuller on 1/6/16.
 */
public class Farm implements CYOAObject {
    private static final String[] validCommands = {"harvest", "plant"};
    private final String name;
    private Map<String, Integer> cropField;

    public Farm(String name) {
        this.name = name;
        this.cropField = Maps.newHashMap();
    }

    @Override
    public String[] getValidCommands() {
        return validCommands;
    }

    @Override
    public String processCommand(String command) {
        switch(command) {
            case "harvest":
                return "It's harvest time!";
            case "plant":
                return "It's planting time!";
            default:
                return "I did not recognize that command, sorry.";
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return "farm";
    }
}
