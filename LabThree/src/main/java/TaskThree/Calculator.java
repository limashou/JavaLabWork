package TaskThree;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №3
 * The task 3
 * Class of the calculator
 */
public class Calculator extends Application {
    private TextField input1;
    private TextField input2;
    private TextField result;
    private ToggleGroup operationGroup;

    /**
     * The main method launches the JavaFX application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        input1 = new TextField();
        input2 = new TextField();
        operationGroup = new ToggleGroup();
        RadioButton addBtn = new RadioButton("+");
        addBtn.setToggleGroup(operationGroup);
        RadioButton subtractBtn = new RadioButton("-");
        subtractBtn.setToggleGroup(operationGroup);
        RadioButton multiplyBtn = new RadioButton("*");
        multiplyBtn.setToggleGroup(operationGroup);
        RadioButton divideBtn = new RadioButton("/");
        divideBtn.setToggleGroup(operationGroup);
        Button calculateBtn = new Button("Calculate");
        calculateBtn.setOnAction(event -> calculate());
        HBox operationBox = new HBox(10, addBtn, subtractBtn, multiplyBtn, divideBtn);
        operationBox.setAlignment(Pos.CENTER);
        result = new TextField();
        result.setEditable(false);
        VBox root = new VBox(10, input1, input2, operationBox, calculateBtn, result);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }
    /**
     * Performs the arithmetic calculation based on the selected operation and updates the result field.
     */
    private void calculate() {
        String text1 = input1.getText();
        String text2 = input2.getText();
        if (!isValidNumber(text1) || !isValidNumber(text2)) {
            showError("Invalid input. Please enter valid numbers.");
            return;
        }
        double number1 = Double.parseDouble(text1);
        double number2 = Double.parseDouble(text2);
        RadioButton selectedOperation = (RadioButton) operationGroup.getSelectedToggle();
        String operation = selectedOperation.getText();
        double resultValue = 0.0;
        switch (operation) {
            case "+":
                resultValue = number1 + number2;
                break;
            case "-":
                resultValue = number1 - number2;
                break;
            case "*":
                resultValue = number1 * number2;
                break;
            case "/":
                resultValue = number1 / number2;
                break;
        }
        result.setText(String.valueOf(resultValue));
    }
    /**
     * Checks if the input string is a valid number.
     * @param input The input string to check.
     * @return True if the input is a valid number, false otherwise.
     */
    private boolean isValidNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Displays an error message in an alert dialog.
     * @param message The error message to display.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
