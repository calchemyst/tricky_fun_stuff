package pegasus;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by annafuller on 1/6/16.
 */
public class PeonContext {
    private final String name;
    private final Set<String> crops;
    private int health;
    private boolean isReady;

    public PeonContext(String name) {
        this.name = name;
        this.crops = Sets.newHashSet();
        this.health = 20;
        this.isReady = false;
    }

    public void setReady(boolean value) {
        this.isReady = value;
    }

    public void harvestCrop(String name) {
        crops.add(name);
    }

    public void eat(String name) {
        crops.remove(name);
        health++;
    }

    public Set<String> getCrops() {
        return crops;
    }

    public String processCommand(String command) {
        switch(command) {
            case "yes":
                break;
            case "no":
                break;
            case "north":
                break;
            case "south":
                break;
            case "west":
                break;
            case "east":
                break;
            default:
                return "I did not recognize that command, sorry.";
        }
    }



}

