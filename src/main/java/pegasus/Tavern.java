package pegasus;

import com.google.common.collect.Sets;

import java.util.Random;
import java.util.Set;

/**
 * Created by annafuller on 1/13/16.
 */
public final class Tavern implements  Location {
    private final String name;
    private final Set<String> validCommands;
    private int numBeers;
    private int numWhiskeys;
    private int numGins;
    private int numTequilas;
    private final String type;

    public Tavern(String name) {
        this.name = name;
        this.numBeers = 100;
        this.numWhiskeys = 10;
        this.numGins = 4;
        this.numTequilas = 5;
        this.type = "tavern";
        this.validCommands = Sets.newHashSet("drink");
    }

    public String drinkBeer() {
        if (numBeers >= 1) {
            String firstPart = numBeers + " bottles of beer on the wall. ";
            numBeers--;
            String secondPart = "Pass one around. ";
            String thirdPart = numBeers + " bottles of beer on the wall";
            return firstPart + secondPart + thirdPart;
        } else {
            return "Out of liquid bread. How could you, filthy peon...";
        }
    }

    public String drinkTequila() {
        if (numTequilas >= 1) {
            String firstPart = "Hola hola! Tequila! Party! ";
            numTequilas--;
            String secondPart = "Only " + numTequilas + " left now!";
            return firstPart + secondPart;
        } else {
            return "Ouch, no more tequila. Rough night.";
        }
    }


    public String drinkGin() {
        if (numGins >= 1) {
            String firstPart = "Classy choice, gin. ";
            numGins--;
            String secondPart = "Only " + numGins+ " left now!";
            return firstPart + secondPart;
        } else {
            return "Better go pick juniper berries. There's no gin left.";
        }
    }

    public String drinkWhiskey() {
        if (numWhiskeys >= 1) {
            String firstPart = "This is top notch scotch. ";
            numWhiskeys--;
            String secondPart = "Only " + numWhiskeys + " left!";
            return firstPart + secondPart;
        } else {
            return "No Scotch left. Wait another twenty years.";
        }
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
        if (input.equals("drink")) {
            // roll dice for beer or whiskey
            Random rn = new Random();
            int choice = rn.nextInt() % 4;
            if (choice == 0) {
                return drinkBeer();
            } else if (choice == 1) {
                return drinkGin();
            } else if (choice == 2) {
                return drinkTequila();
            } else {
                return drinkWhiskey();
            }
        } else {
            return "You can't do that at a tavern...";
        }
    }
}
