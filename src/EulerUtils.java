import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
