package taskFour;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 4
 * A class for sorting by increasing length and outputting strings to another file,
 * that contain the letter "a".
 */
public class TextFile {
    public static void solve_task(String input, String output){
        try {
            Stream<String> stream = Files.lines(Paths.get(input));
            System.out.println("Data read from a file: " + input);
            stream = stream.sorted(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.length() - o2.length();
                }
            });
            stream = stream.sorted((o1, o2) -> o1.length() - o2.length());
            stream = stream.filter(str -> str.contains("a"));
            Files.write(Paths.get(output), (Iterable<String>)stream::iterator);
            System.out.println("The data is written to the file: " + output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
