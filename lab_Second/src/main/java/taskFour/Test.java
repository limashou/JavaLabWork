package taskFour;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 4
 * A class for testing sorting by increasing length and outputting strings to another file,
 * that contain the letter "a".
 */
public class Test {
    private static final String INPUT_FILE = "src/main/resources/input.txt";
    private static final String OUTPUT_FILE = "output.txt";
    public static void main(String[] args){
        TextFile.solve_task(INPUT_FILE, OUTPUT_FILE);
    }
}
