package pegasus;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by annafuller on 1/13/16.
 */
public final class Tavern implements  Location {
    private final String name;
    private final Set<String> validCommands;
    private int numBeers;
    private int numWhiskeys;
    private final String type;

    public Tavern(String name) {
        this.name = name;
        this.numBeers = 100;
        this.numWhiskeys = 10;
        this.type = "tavern";
        this.validCommands = Sets.newHashSet("drink");
    }

    public String drinkBeer() {
        String firstPart = numBeers + " bottles of beer on the wall. ";
        numBeers--;
        String secondPart = "Pass one around. ";
        String thirdPart = numBeers + " bottles of beer on the wall";
        return firstPart + secondPart + thirdPart;

    }

    public String drinkWhiskey() {
        String firstPart = "This is top notch scotch. ";
        numWhiskeys--;
        String secondPart = "Only " + numWhiskeys + " left!";
        return firstPart + secondPart;
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
