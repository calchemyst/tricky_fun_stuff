import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by annafuller on 12/5/15.
 */
public class SummationPrimes {

    public static void main(String[] args) {
        int n = 2000000;
        long sum = 0;
        boolean[] primes = findPrimes(n);
        for (int i = 2; i < n; i++) {
            if (primes[i] == true) {
                sum += i;
            }
        }
        System.out.println(sum);

    }



    private static boolean[] findPrimes(int n) {
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
}
