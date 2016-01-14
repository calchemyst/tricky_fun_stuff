package project_euler;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.math.BigInteger.*;

/**
 * Created by annafuller on 12/5/15.
 */
public class EulerUtils {

    private EulerUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Great sieve of Erastosthenes!
     */
    public static boolean[] findPrimes(int n) {
        boolean[] numbers = new boolean[n];
        Arrays.fill(numbers, true);
        for (int i = 2; i < Math.sqrt(n) ; i++) {
            if (numbers[i]) {
                for (int j = i*i; j < n; j += i) {
                    numbers[j] = false;
                }
            }
        }
        return numbers;
    }


    public static boolean[] findLargePrimes(int from, int n) {
        boolean[] numbers = new boolean[n-from];
        Arrays.fill(numbers, true);
        for (int i = from; i < Math.sqrt(n) ; i++) {
            if (numbers[i]) {
                for (int j = i*i; j < n; j += i) {
                    numbers[j] = false;
                }
            }
        }
        return numbers;
    }

    public static boolean checkPalindromeNumeric(int number) {
        List<Integer> numbers = new ArrayList<>();
        int i = 10;
        while (number > 0) {
            int num = number % i;
            numbers.add(num);
            number = (number - num)/10;
        }
        int numberSize = numbers.size();
        for (int k = 0; k < numberSize -1; k++) {
            int num1 = numbers.get(k);
            int num2 = numbers.get(numberSize -k -1);
            if (num1 != num2) {
                return false;
            }
        }
        return true;
    }

    public static int[] getDigits(int number, int numDigits, int radix) {
        List<Integer> numbers = new ArrayList<>();
        int i = radix;
        while (number > 0) {
            int num = number % i;
            numbers.add(num);
            number = (number - num)/radix;
        }
        int[] digits = new int[numDigits];
        for (int j = 0; j < numDigits ; j++ ) {
            digits[j] = numbers.size() - j;
        }
        return digits;
    }

}
