package TaskFour;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №3
 * The task 4
 * Class of the controller
 */
public class Dictionary {
    @FXML
    private TextField text_field_en;
    @FXML
    private TextField text_field_uk;
    @FXML
    private TextField text_field_find;
    @FXML
    private Button button_add;
    @FXML
    private Button button_remove;
    @FXML
    private Button button_find;
    @FXML
    private TableView<WordEntry> table_view;
    @FXML
    private TableColumn<WordEntry, String> column_en;
    @FXML
    private TableColumn<WordEntry, String> column_uk;

    private Map<String, String> dictionary;
    /**
     * Initializes the dictionary application.
     */
    @FXML
    private void initialize() {
        dictionary = new HashMap<>();
        column_en.setCellValueFactory(new PropertyValueFactory<>("english"));
        column_uk.setCellValueFactory(new PropertyValueFactory<>("ukrainian"));
        table_view.setItems(getWordEntries());
        button_add.setOnAction(event -> addWord());
        button_remove.setOnAction(event -> removeWord());
        button_find.setOnAction(event -> findWord());
    }
    private ObservableList<WordEntry> getWordEntries() {
        ObservableList<WordEntry> wordEntries = FXCollections.observableArrayList();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            wordEntries.add(new WordEntry(entry.getKey(), entry.getValue()));
        }
        return wordEntries;
    }
    /**
     * Displays an error message in a dialog.
     * @param message The error message to display.
     */
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    /**
     * Adds a new word to the dictionary.
     */
    @FXML
    private void addWord() {
        String englishWord = text_field_en.getText();
        String ukrainianWord = text_field_uk.getText();
        String englishRegex = "^[a-zA-Z]+$";
        String ukrainianRegex = "^[А-Яа-яЇїІіЄєҐґ]+$";

        if (englishWord.matches(englishRegex) && ukrainianWord.matches(ukrainianRegex)) {
            dictionary.put(englishWord, ukrainianWord);
            table_view.setItems(getWordEntries());
            text_field_en.clear();
            text_field_uk.clear();
        } else {
            if (!englishWord.matches(englishRegex)) {
                showError("An incorrect English word has been entered!");
            }
            if (!ukrainianWord.matches(ukrainianRegex)) {
                showError("An incorrect Ukrainian word has been entered!");
            }
        }
    }
    /**
     * Removes a word from the dictionary.
     */
    @FXML
    private void removeWord() {
        WordEntry selectedEntry = table_view.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            dictionary.remove(selectedEntry.getEnglish());
            dictionary.remove(selectedEntry.getUkrainian());
            table_view.setItems(getWordEntries());
        }
    }
    /**
     * Searches for a word in the dictionary.
     */
    @FXML
    private void findWord() {
        String searchWord = text_field_find.getText();
        if (!searchWord.isEmpty()) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(searchWord) || entry.getValue().equalsIgnoreCase(searchWord)) {
                    table_view.getSelectionModel().select(new WordEntry(entry.getKey(), entry.getValue()));
                    return;
                }
            }
            System.out.println(searchWord);
            table_view.getSelectionModel().clearSelection();
        }
    }
    /**
     * Represents an entry in the dictionary.
     */
    public static class WordEntry {
        private final String english;
        private final String ukrainian;

        public WordEntry(String english, String ukrainian) {
            this.english = english;
            this.ukrainian = ukrainian;
        }
        public String getEnglish() {
            return english;
        }
        public String getUkrainian() {
            return ukrainian;
        }
    }
}
