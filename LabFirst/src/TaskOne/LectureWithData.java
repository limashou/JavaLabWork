package TaskOne;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №1
 * The task is individual
 * Option 20.
 * A class that represents a lecture with additional data, including the time of editing.
 */
public class LectureWithData extends Lecture {
    private ZonedDateTime timeOfEditing;
    /**
     * Constructs a new LectureWithData object with default values.
     */
    public LectureWithData() {
    }
    /**
     * Constructs a new LectureWithData object with the specified date, number of students, and topic.
     * @param date     the date of the lecture in the format "yyyy-MM-dd HH:mm:ss"
     * @param students the number of students in the lecture
     * @param topic    the topic of the lecture
     */
    public LectureWithData(String date, int students, String topic) {
        super(date, students, topic);
        parseDate(date);
    }
    /**
     * Gets the time of editing for this lecture.
     * @return the time of editing as a ZonedDateTime object
     */
    public ZonedDateTime getTimeOfEditing() {
        return timeOfEditing;
    }
    /**
     * Sets the time of editing for this lecture.
     * @param timeOfEditing the time of editing as a string in the format "yyyy-MM-dd HH:mm:ss"
     */
    public void setTimeOfEditing(String timeOfEditing) {
        parseDate(timeOfEditing);
    }
    /**
     * Overrides the getTopic method to retrieve the localized topic from a resource bundle.
     * @return the localized topic
     */
    @Override
    public String getTopic() {
        ResourceBundle bundle = ResourceBundle.getBundle("curriculum", Locale.getDefault());
        return bundle.getString(super.getTopic());
    }
    /**
     * Parses the given date string and sets the time of editing as a ZonedDateTime object.
     * @param date the date string in the format "yyyy-MM-dd HH:mm:ss"
     */
    private void parseDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(date, dateTimeFormatter);
        timeOfEditing = ZonedDateTime.of(ldt, ZoneId.systemDefault());
    }
    /**
     * Overrides the toString method to provide a localized string representation of the lecture.
     * @return a string representation of the lecture
     */
    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle("curriculum", Locale.getDefault());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(Locale.getDefault());

        return String.format(Locale.getDefault(),
                bundle.getString("students") + ": %d, "
                        + bundle.getString("topic") + ": %s, "
                        + bundle.getString("date") + ": %s ",
                getStudents(), getTopic(), getTimeOfEditing().format(dateFormatter));
    }
    /**
     * A method for testing the LectureWithData class by setting sample values and printing the lecture information.
     * This method is protected and can only be accessed within the class or its subclasses.
     */
    protected void testLecture() {
        setTimeOfEditing("2008-10-21 10:22:59");
        setStudents(32);
        setTopic("topic");
        System.out.println(this);
    }
    /**
     * The main method of the LectureWithData class, which demonstrates the usage of the class.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        new LectureWithData().testLecture();
        Locale.setDefault(new Locale("uk"));
        new LectureWithData().testLecture();
    }
}

