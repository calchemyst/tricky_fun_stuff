package project_euler;

/**
 * Created by annafuller on 12/3/15.
 */
public class EvenFibonacci {

    public static void main(String[] args) {
        int sum = 2;
        int i = 1;
        int j = 2;
        int next = i + j;
        while (next <= 4000000) {
            if (next % 2 == 0) {
                sum += next;
            }
            i = j;
            j = next;
            next = i +j;

        }
        System.out.println(sum);
    }
}
