package TaskTwo;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import static TaskTwo.ObservableListDouble.changeNumbers;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №3
 * The task 2
 * Class for test
 */
public class Test {
    /**
     * The main method of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        ObservableList<Double> numbers = FXCollections.observableArrayList();
        numbers.addListener((ListChangeListener<? super Double>) c -> System.out.println(numbers));
        numbers.addAll(-1.1, 1.2, 2.3, 3.0, -6.3, 5.2, 1.1);
        changeNumbers(numbers);
        numbers.clear();
    }
}
