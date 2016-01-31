package pegasus;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by annafuller on 1/6/16.
 */
public class Peon {
    private final String name;
    private Set<String> validCommands;
    private Map<String, Integer> pouch;
    private String currLocation;
    private int health;
    private int money;

    public Peon(String name, int health, int money, String currLocation) {
        this.name = name;
        this.currLocation = currLocation;
        this.pouch = Maps.newHashMap();
        this.validCommands = Sets.newHashSet("sleep", "location", "eat", "pouch", "help");
        this.health = health;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String handleCommand(String input) {
        if (input.equals("sleep")) {
            return sleep();
        } else if (input.equals("location")) {
            return location();
        } else if (input.equals("west")) {
            return go("west");
        } else if (input.equals("east")) {
            return go("east");
        } else if (input.equals("south")) {
            return go("south");
        } else if (input.equals("north")) {
            return go("north");
        } else if (input.equals("eat")) {
            return eat();
        } else if (input.equals("pouch")) {
            return pouch();
        } else if (input.equals("sleep")) {
            return sleep();
        } else if (input.equals("help")) {
            return help();
        } else {
            return PeonUtils.getLocationFromName(currLocation).handleCommmand(input);
        }
    }




    public String pouch() {
        StringBuilder sb = new StringBuilder();
        sb.append("You have stuff: ");
        for (String s : pouch.keySet()) {
            sb.append(s);
            sb.append(", ");
        }
        return sb.toString();
    }

    public int health() {
        return health;
    }

    public int fight() {
        Random rn = new Random();
        return rn.nextInt(10);
    }

    public void takeDamage(int damage) {
        health = health - damage;
    }

    public String location() {
        return currLocation;
    }

    public String go(String direction) {
        Location location = PeonUtils.getLocationFromName(currLocation);
        String newLocation = PeonUtils.move(direction, location.name());
        if (!Strings.isNullOrEmpty(newLocation)) {
            validCommands.removeAll(location.commands());
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
        validCommands.addAll(PeonUtils.getLocationFromName(currLocation).commands());
        for (String s : validCommands) {
            sb.append(s + ", ");
        }
        return sb.toString();
    }

}

