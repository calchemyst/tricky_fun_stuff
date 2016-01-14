package pegasus;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by annafuller on 1/6/16.
 */
public class Farm implements Location {
    private static final String[] validCommands = {"harvest", "plant"};
    private final String name;
    private Map<String, Integer> cropField;

    public Farm(String name) {
        this.name = name;
        this.cropField = Maps.newHashMap();
    }

}
