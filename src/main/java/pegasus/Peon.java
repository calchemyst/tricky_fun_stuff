package pegasus;

import com.google.common.base.Strings;

/**
 * Created by annafuller on 1/6/16.
 */
public class Peon {
    private final String name;
    private String currLocation;
    private int health;
    private int money;

    public Peon(String name, int health, int money, String currLocation) {
        this.name = name;
        this.currLocation = currLocation;
        this.health = health;
        this.money = money;
    }

    public String health() {
        return "Your peon constition is at " + health + " points.";
    }

    public String location() {
        return currLocation;
    }

    public void kill(String name) {

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

    public String getName() {
        return name;
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
        return "Valid peon commands are eat, health, sleep, go, location";
    }

}

