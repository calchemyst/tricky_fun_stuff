package project_euler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by annafuller on 12/3/15.
 */
public class LargestPrimeFactor {
    public static void main(String[] args) {
        long number = 600851475143L;
        long curr = 3;
        long largest = 0;
        Set<Long> allFactors = new HashSet<>(2);
        while (curr < Math.sqrt(number)) {
            if (number % curr == 0) {
                if (checkPrime(curr, allFactors)) {
                    allFactors.add(curr);
                    largest = curr;
                }
            }
            curr += 2;
        }
        System.out.println(largest);
    }

    private static boolean checkPrime(long n, Set<Long> primes) {
        for (long l : primes) {
            if (n % l == 0) {
                return false;
            }
        }
        return true;
    }

}
