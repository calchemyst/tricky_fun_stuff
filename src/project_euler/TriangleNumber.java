package project_euler;

/**
 * Created by annafuller on 12/6/15.
 */
public class TriangleNumber {
    private static boolean [] primes = EulerUtils.findPrimes(2000);

    public static void main(String[] args) {
        int numFactors = 1;
        TriangleGenerator tg = new TriangleGenerator();
        long start = System.currentTimeMillis();
        long triangle = 1;
        while (numFactors < 4000) {
            triangle = tg.next();
            numFactors = countDivisors(triangle);
        }
        System.out.println("number: " + triangle);
        System.out.println("factors: " + numFactors);
        long delta = System.currentTimeMillis() - start;
        System.out.println(delta);
    }


    private static int countDivisors(long n) {
        int divisors = 1;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i] && (n % i == 0)) {
                long temp_n = n;
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
