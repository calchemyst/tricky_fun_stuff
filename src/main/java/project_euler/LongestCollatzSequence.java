package project_euler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by annafuller on 12/13/15.
 */
public class LongestCollatzSequence {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int longestChainLength = 0;
        int bestStartingNum = 1;
        int currNum = 13;
        while (currNum <= 1000000) {
            List<Long> currChain = makeChain(currNum);
            if (currChain.size() > longestChainLength) {
                bestStartingNum = currNum;
                longestChainLength = currChain.size();
            }
            currNum++;

        }
        System.out.println(bestStartingNum);
        long finish = System.currentTimeMillis();
        System.out.println(finish-start);
    }

    private static List<Long> makeChain(long startingNum) {
        List<Long> chain = new ArrayList<>();
        chain.add(startingNum);
        long currNum = startingNum;
        while (currNum != 1) {
            if (currNum % 2 == 0) {
                currNum /= 2;
            } else {
                currNum = 3*currNum + 1;
            }
            chain.add(currNum);
        }
        return chain;
    }
}
