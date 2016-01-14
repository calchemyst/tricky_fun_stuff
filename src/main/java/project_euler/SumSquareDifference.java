package project_euler;

/**
 * Created by annafuller on 12/4/15.
 */
public class SumSquareDifference {

    public static void main(String[] args) {
        int squareSum = squareSum(100);
        int sumSquares = sumSquares(100);
        System.out.println(squareSum);
        System.out.println(sumSquares);
        System.out.println(squareSum - sumSquares);
    }


    private static int squareSum(int number) {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += i;
        }
        return sum*sum;
    }


    private static int sumSquares(int number) {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += i*i;
        }
        return sum;
    }
}
