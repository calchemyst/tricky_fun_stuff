package project_euler;

/**
 * Created by annafuller on 12/7/15.
 */
public class TriangleGenerator {
    int index;
    long currVal;

    public TriangleGenerator() {
        index = 0;
        currVal = 0;
    }

    public long next() {
        index++;
        currVal += index;
        return currVal;
    }
}
