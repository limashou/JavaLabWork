package TaskSix;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №1
 * The task 6
 * A utility class for performing exponentiation and multiplication operations on BigIntegers.
 */
public class bigIntengerPow {
    /**
     * Computes the power of a BigInteger raised to the specified exponent.
     * @param bigInteger the base BigInteger
     * @param p          the exponent
     * @return the BigInteger raised to the power of p
     */
    public static BigInteger pow(BigInteger bigInteger, int p) {
        return bigInteger.pow(p);
    }
    /**
     * Computes the result of multiplying a BigInteger by itself p times.
     * @param bigInteger the BigInteger to be multiplied
     * @param p          the number of times to multiply
     * @return the result of multiplying bigInteger by itself p times
     */
    public static BigInteger mult(BigInteger bigInteger, int p) {
        BigInteger result = bigInteger;
        for (int i = 1; i < p; i++) {
            result = result.multiply(bigInteger);
        }
        return result;
    }
    /**
     * The main method of the BigIntengerPow class.
     * Generates a random BigInteger and performs exponentiation and multiplication operations on it.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger(100, new Random());
        int p = 2;
        System.out.println("Generated number:\n" + bigInteger);
        System.out.println("Exponent:\n" + p);
        System.out.println("Result using pow():\n" + pow(bigInteger, p));
        System.out.println("Result using mult():\n" + mult(bigInteger, p));
    }
}
