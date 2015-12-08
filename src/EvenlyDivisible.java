/**
 * Created by annafuller on 12/3/15.
 */
public class EvenlyDivisible {

    public static void main(String [] args) {
        int [] test =  {2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int start = 40;
        int answer = 0;
        int size = numbers.length;
        int numDivisible = 0;
        boolean divisibleByAll = false;
        while (!divisibleByAll) {

            for (int i : numbers) {
                if (!(start % i == 0)) {
                    break;
                }
                numDivisible++;
            }
            if (numDivisible == size) {
                divisibleByAll = true;
            } else {
                numDivisible = 0;
            }
            answer = start;
            start ++;

        }
        System.out.println(answer);
    }


}
