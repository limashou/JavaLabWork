package TaskFive;

/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №1
 * The task 5
 * A class for processing a string and splitting it into substrings based on numeric digits.
 */
public class Arrayline {
    /**
     * The main method of the Arrayline class.
     * Processes a string and splits it into substrings based on numeric digits.
     * If the length of the input string is greater than 19, the substrings are printed.
     * Otherwise, a message indicating that the line is less than 20 characters is printed.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String line = "ddfdfd6fdfd4fd3fgfg5";
        String[] substrings = line.split("\\d");
        if (line.length() > 19) {
            for (String substring : substrings) {
                System.out.println(substring);
            }
        } else {
            System.out.println("Line length is less than 20");
        }
    }
}

