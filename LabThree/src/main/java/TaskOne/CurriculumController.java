package TaskOne;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №3
 * The task is individual
 * Option 20.
 * Class of the controller
 */
public class CurriculumController implements Initializable {
    private CurriculumWithStreams curriculum = new CurriculumWithStreams();

    private ObservableList<Lecture> observableList;
    /**
     * Displays an information message using an Alert dialog.
     * @param message The message to be displayed.
     */
    public static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    /**
     * Displays an error message using an Alert dialog.
     * @param message The error message to be displayed.
     */
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
    /**
     * Creates and returns a FileChooser object for selecting files.
     * @param title The title of the FileChooser dialog.
     * @return The created FileChooser object.
     */
    public static FileChooser getFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("XML-файли (*.xml)", "*.xml"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Усі файли (*.*)", "*.*"));
        fileChooser.setTitle(title);
        return fileChooser;
    }
    @FXML
    private TextField text_field_name;
    @FXML
    private TextField text_field_surname;
    @FXML
    private TextField text_field_find_world;
    @FXML
    private TextArea text_area;
    @FXML
    private TableView<Lecture> table;
    @FXML
    private TableColumn<Lecture, String> column_date;
    @FXML
    private TableColumn<Lecture, String> column_topic;
    @FXML
    private TableColumn<Lecture, Integer> column_students;
    /**
     * Initializes the controller class.
     * @param location  The location used to resolve relative paths for the root object or null if the location is not known.
     * @param resources The resources used to localize the root object or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setPlaceholder(new Label(""));
    }
    /**
     * Event handler for the "New" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doNew(ActionEvent event) {
        curriculum = new CurriculumWithStreams();
        observableList = null;
        text_field_name.setText("");
        text_field_surname.setText("");
        text_field_find_world.setText("");
        text_area.setText("");
        table.setItems(null);
        table.setPlaceholder(new Label(""));
    }
    /**
     * Event handler for the "Open" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doOpen(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Відкрити XML-файл");
        File file;
        if ((file = fileChooser.showOpenDialog(null)) != null) {
            try {
                curriculum.xStreamDeserialXML(file.getCanonicalPath());
                System.out.println(curriculum);
                text_field_name.setText(curriculum.getName());
                text_field_surname.setText(curriculum.getLectureSurname() + "");
                text_area.setText("");
                table.setItems(null);
                updateTable();
            }
            catch (IOException e) {
                showError("File not found");
            }
            catch (Exception e) {
                showError("Incorrect file format");
            }
        }
    }
    /**
     * Event handler for the "Save" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doSave(ActionEvent event) {
        FileChooser fileChooser = getFileChooser("Зберегти XML-файл");
        File file;
        if ((file = fileChooser.showSaveDialog(null)) != null) {
            try {
                updateSourceData();
                curriculum.xStreamSerialXML(file.getCanonicalPath());
                showMessage("Results successfully saved");
            }
            catch (Exception e) {
                showError("Error writing to the file");
                e.printStackTrace();
            }
        }
    }
    /**
     * Event handler for the "Exit" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doExit(ActionEvent event) {
        Platform.exit();
    }
    /**
     * Event handler for the "Add" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doAdd(ActionEvent event) {
        curriculum.getLectures().add(new Lecture());
        updateTable();
    }
    /**
     * Event handler for the "Remove" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doRemove(ActionEvent event) {
        if (observableList == null) {
            return;
        }
        if (observableList.size() > 0) {
            observableList.remove(observableList.size() - 1);
        }
        if (observableList.size() <= 0) {
            observableList = null;
        }
    }
    /**
     * Event handler for the "Sort By Comment Length" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doSortByCommentLength(ActionEvent event) {
        updateSourceData();
        curriculum.sortByCommentLength();
        updateTable();
    }
    /**
     * Event handler for the "Sort By Alphabet" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void sortByAphabet(ActionEvent event) {
        updateSourceData();
        curriculum.sortByAlph();
        updateTable();
    }
    /**
     * Event handler for the "About" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About program");
        alert.setHeaderText("Curriculum");
        alert.setContentText("The program shows information about the academic course\n" + "Version 1\n" + "Author:Maksym POLIATSKYI");
        alert.showAndWait();
    }
    /**
     * Event handler for the name change event.
     * @param event The ActionEvent object.
     */
    @FXML
    private void nameChanged(ActionEvent event) {
        curriculum.setName(text_field_name.getText());
    }
    /**
     * Event handler for the lecture change event.
     * @param event The ActionEvent object.
     */
    @FXML
    private void lectureChanged(ActionEvent event) {
        try {
            String surname = text_field_surname.getText();
            curriculum.setLectureSurname(surname);
        }
        catch (NumberFormatException e) {
            text_field_surname.setText(curriculum.getLectureSurname() + "");
        }
    }
    /**
     * Event handler for the "Search By Word" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void doSearchByWord(ActionEvent event) {
        updateSourceData();
        text_area.setText("");
        String searchText = text_field_find_world.getText();
        for (Lecture lecture : curriculum.getLecture()) {
            if (lecture.containsSpecificWord(searchText)) {
                showResults(lecture);
            }
        }
    }
    /**
     * Event handler for the "Search Lowest Amount Of Students" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void searchLowestAmoundOfStudents(ActionEvent event) {
        updateSourceData();
        text_area.setText(curriculum.findLowestAmountOfStudent());
    }
    /**
     * Event handler for the "Last Letter" action.
     * @param event The ActionEvent object.
     */
    @FXML
    private void lactLetter(ActionEvent event) {
        updateSourceData();
        text_area.setText(String.valueOf(curriculum.lastLetter()));
    }
    /**
     * Displays the results of a lecture.
     * @param lecture The Lecture object to display results for.
     */
    private void showResults(Lecture lecture) {
        text_area.appendText("Date: " + lecture.getData() + " \n");
        text_area.appendText("Students:" + lecture.getStudents() + "\n");
        text_area.appendText("Topic:" + lecture.getTopic() + "\n");
        text_area.appendText("\n");
    }
    /**
     * Updates the source data with the values from the observable list.
     */
    private void updateSourceData() {
        curriculum.setLectures(new ArrayList<>());
        for (Lecture c : observableList) {
            curriculum.getLectures().add(new Lecture(c.getTopic(),c.getStudents(),c.getData()));
        }
    }
    /**
     * Updates the date value in the table column.
     * @param t The CellEditEvent object.
     */
    private void updateDate(TableColumn.CellEditEvent<Lecture, String> t) {
        Lecture c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setData(t.getNewValue());
    }
    /**
     * Updates the topic value in the table column.
     * @param t The CellEditEvent object.
     */
    private void updateTopic(TableColumn.CellEditEvent<Lecture, String> t) {
        Lecture c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setTopic(t.getNewValue());
    }
    /**
     * Updates the students value in the table column.
     * @param t The CellEditEvent object.
     */
    private void updateStudents(TableColumn.CellEditEvent<Lecture, Integer> t) {
        Lecture c = t.getTableView().getItems().get(t.getTablePosition().getRow());
        c.setStudents(t.getNewValue());
    }
    /**
     * Updates the table view with the lecture data.
     */
    private void updateTable() {
        ArrayList<Lecture> list = new ArrayList<>();
        observableList = FXCollections.observableList(list);
        for (int i = 0; i < curriculum.getLecture().length; i++) {
            list.add((Lecture) curriculum.getLecture(i));
        }
        table.setItems(observableList);
        column_date.setCellValueFactory(new PropertyValueFactory<>("data"));
        column_date.setCellFactory(
                TextFieldTableCell.forTableColumn());
        column_date.setOnEditCommit(t -> updateDate(t));
        column_topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        column_topic.setCellFactory(
                TextFieldTableCell.forTableColumn());
        column_topic.setOnEditCommit(t -> updateTopic(t));
        column_students.setCellValueFactory(new PropertyValueFactory<>("students"));
        column_students.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        column_students.setOnEditCommit(t -> updateStudents(t));
    }
}
