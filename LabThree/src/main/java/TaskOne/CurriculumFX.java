package TaskOne;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №3
 * The task is individual
 * Option 20.
 * The main class for the CurriculumFX application.
 */
public class CurriculumFX extends Application {
    /**
     * The entry point for the application.
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("CurriculumForm.fxml"));
            Scene scene = new Scene(root, -1, -1);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * The main method of the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
