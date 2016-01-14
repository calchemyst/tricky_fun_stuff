package pegasus;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * Created by annafuller on 1/6/16.
 */
public class Peon {
    private final String name;
    private Set<String> validCommands;
    private String currLocation;
    private int health;
    private int money;

    public Peon(String name, int health, int money, String currLocation) {
        this.name = name;
        this.currLocation = currLocation;
        this.validCommands = Sets.newHashSet("west", "east", "north",
                "south", "sleep", "location", "health", "eat");
        this.health = health;
        this.money = money;
    }

    public String health() {
        return "Your peon constition is at " + health + " points.";
    }

    public String location() {
        return currLocation;
    }

    public String go(String direction) {
        String newLocation = PeonUtils.move(direction, currLocation);
        if (!Strings.isNullOrEmpty(newLocation)) {
            currLocation = newLocation;
            return "Couldn't have gotten there faster. You are now in " + currLocation;
        } else {
            return "You can't go that way, silly peon!";
        }
    }

    public String eat() {
        health++;
        return "Healthy food! Yum yum!";
    }

    public String sleep() {
        health++;
        return "Sleep is good. You feel rested.";
    }

    public String help() {
        StringBuilder sb = new StringBuilder();
        sb.append("Valid peon commands are: ");
        for (String s : validCommands) {
            sb.append(s + " ");
        }
        return sb.toString();
    }

}

