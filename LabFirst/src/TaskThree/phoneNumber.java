package TaskThree;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №1
 * The task 3
 * A class for validating phone numbers and determining the phone operator.
 */
public class phoneNumber {
    /**
     * The main method of the PhoneNumber class.
     * Validates phone numbers and determines the phone operator.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] numbers = {"+380663400334", "+380673400334", "+380973400334", "+380693400334"};
        for (String number : numbers) {
            if (check(number)) {
                System.out.println(number + "\tNumber is Kyivstar");
            } else {
                System.out.println(number + "\tWrong phone operator");
            }
        }
    }
    /**
     * Checks if the given phone number is valid and belongs to the Kyivstar operator.
     * @param number the phone number to be checked
     * @return true if the number is valid and belongs to Kyivstar, false otherwise
     */
    private static boolean check(String number) {
        Pattern pattern = Pattern.compile("\\+?(380)?((39)?\\d{3}|(67)?\\d{3}|(68)?\\d{3}|(96)?\\d{3}|(97)?\\d{3}|(98)?\\d{3})\\d{2}\\d{2}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}

