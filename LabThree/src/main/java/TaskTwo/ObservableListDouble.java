package TaskTwo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №3
 * The task 2
 * Class of the ObservableList
 */
public class ObservableListDouble {
    /**
     * Reorders the numbers in the given ObservableList by moving negative numbers to the end of the list.
     * @param numbers The ObservableList of numbers to be reordered.
     */
    public static void changeNumbers(ObservableList<Double> numbers) {
        for (int i = numbers.size() -1, j = 0; j < numbers.size(); j++) {
            if (numbers.get(i) < 0) {
                numbers.add(numbers.get(i));
                numbers.remove(i);
            } else {
                i--;
            }
        }
    }
}
