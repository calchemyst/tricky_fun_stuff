/**
 * Created by annafuller on 12/5/15.
 */
public class SpecialPythagoreanTriplet {

    public static void main(String[] args) {


        for (int i = 1; i <= 998; i++) {
            for (int j = 1; j <= 998; j++) {
                for (int k = 1; k <= 998; k++) {
                    if (i*i + j*j == k*k) {
                        if (i +j +k == 1000) {
                            System.out.println(i + " " + j + " " + k);
                            System.out.println("found it");
                            System.out.println(i*j*k);
                            break;
                        }
                    }
                }
            }
        }


    }
}
