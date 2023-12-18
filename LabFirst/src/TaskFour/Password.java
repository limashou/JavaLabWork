package TaskFour;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №1
 * The task 4
 * A class for validating password strength.
 */
public class Password {
    /**
     * The main method of the Password class.
     * Validates passwords and prints whether they are correct or not.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] passwords = {"aaW122_fD", "ssssSS11", "sdsd*fD1", "aaW122-fD", "aaW122*fв", "#1111aa--D"};
        for (String password : passwords) {
            if (check(password)) {
                System.out.println(password + "\tCorrect password");
            } else {
                System.out.println(password + "\tWrong password");
            }
        }
    }
    /**
     * Checks if the given password meets the required criteria.
     * The password must have at least one uppercase letter, one lowercase letter,
     * one digit, and one special character from the set [-, _, *].
     * @param password the password to be checked
     * @return true if the password meets the criteria, false otherwise
     */
    private static boolean check(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\-\\_\\*])[A-Za-z\\d\\-\\_\\*]+$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

