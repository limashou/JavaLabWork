package taskThree;

import java.util.stream.IntStream;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 3
 * Class for testing the search for divisors of a number
 */
public class FindDivisors {
    public static int[] findDivisors(int number){
        if (number <= 0){
            return null;
        }
        IntStream result = IntStream.range(1, number+1).filter(i -> number % i == 0);
        return result.toArray();
    }
}
