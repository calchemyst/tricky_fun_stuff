import java.util.*;

/**
 * Created by annafuller on 12/6/15.
 */
public class TriangleNumber {
    private static boolean [] primes = EulerUtils.findPrimes(1000);

    public static void main(String[] args) {
        int numFactors = 1;
        TriangleGenerator tg = new TriangleGenerator();
        long start = System.currentTimeMillis();
        int triangle = 1;
        while (numFactors < 1025) {
            triangle = tg.next();
            numFactors = countDivisors(triangle);
        }
        System.out.println(triangle);
        long delta = System.currentTimeMillis() - start;
        System.out.println(delta);
    }


    private static int countDivisors(int n) {
        int divisors = 1;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i] && (n % i == 0)) {
                int temp_n = n;
                int div_count = 0;
                while (temp_n % i == 0) {
                    temp_n = temp_n / i;
                    div_count++;
                }
                divisors = divisors * (div_count + 1);
            }
        }
        return divisors;
    }
}
