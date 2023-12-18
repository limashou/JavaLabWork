package taskThree;

import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        int number = 8;
        int[] result = FindDivisors.findDivisors(number);
        if (result != null){
            System.out.println("Divisors of a number:" + number + ":\n" + Arrays.toString(result));
        }
        else
            System.err.println("The number " + number + " is not positive!");
    }
}
