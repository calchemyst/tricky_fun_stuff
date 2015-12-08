/**
 * Created by annafuller on 12/7/15.
 */
public class TriangleGenerator {
    int index;
    int currVal;

    public TriangleGenerator() {
        index = 0;
        currVal = 0;
    }

    public int next() {
        index++;
        currVal += index;
        return currVal;
    }
}
