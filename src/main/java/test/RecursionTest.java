package test;

/**
 * Created by Lukmanov.MN on 07.09.2018.
 */
public class RecursionTest {

    public static void main(String[] args) {
        int k = 5;
        int result = 1;
        while(k > 0) {
            result *= k--;
        }

        System.out.println(result);
    }

}
