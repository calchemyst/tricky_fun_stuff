package pegasus;

/**
 * Created by annafuller on 1/13/16.
 */
public final class Tavern {
    private final String name;
    private int numBeers;
    private int numWhiskeys;

    public Tavern(String name) {
        this.name = name;
        this.numBeers = 100;
        this.numWhiskeys = 10;
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
}
