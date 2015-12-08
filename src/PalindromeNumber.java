import java.util.ArrayList;
import java.util.List;

/**
 * Created by annafuller on 12/3/15.
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        int largest = 100;
        for (int i = 100; i <= 999; i++) {
            for (int j = 100; j <= 999; j++) {
                int answer = i * j;
                if (checkPalindrome(answer)) {
                    if (answer > largest) {
                        largest = answer;
                    }
                }
            }
        }
        System.out.println(largest);
    }


    private static boolean checkPalindrome(int number) {
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


