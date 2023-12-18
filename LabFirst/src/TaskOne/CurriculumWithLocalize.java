package TaskOne;

import java.text.Collator;
import java.time.Period;
import java.util.*;
import java.util.ResourceBundle;

/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №1
 * The task is individual
 * Option 20.
 * A class that represents a localized curriculum with an array list of lectures.
 */
public class CurriculumWithLocalize extends CurriculumWithArrayList {

    /**
     * Constructs a new CurriculumWithLocalize object with the specified name, lecture surname,
     * and a list of lectures with additional data.
     * @param name             the name of the curriculum
     * @param lectureSurname   the surname of the lecture
     * @param lectureWithData  the list of lectures with additional data
     */
    public CurriculumWithLocalize(String name, String lectureSurname, List<LectureWithData> lectureWithData) {
        super(name, lectureSurname, null);
        setLectures(lectureWithData.toArray(new LectureWithData[0]));
    }

    /**
     * Constructs a new empty CurriculumWithLocalize object.
     */
    public CurriculumWithLocalize() {
    }

    /**
     * Overrides the toString method to provide a localized string representation of the curriculum.
     * @return a string representation of the curriculum
     */
    @Override
    public String toString() {
        ResourceBundle bundle = ResourceBundle.getBundle("curriculum", Locale.getDefault());
        StringBuilder result = new StringBuilder(bundle.getString("name")
                + ", " + getLectureSurname());
        for (int i = 0; i < getLecture().length; i++) {
            result.append("\n").append(getLecture(i));
        }
        return result + "";
    }

    /**
     * Overrides the sortByTopic method to sort the lectures by topic in a localized manner.
     */
    @Override
    public void sortByTopic() {
        LectureWithData[] arr = getLectureWithData();
        Arrays.sort(arr, new Comparator<LectureWithData>() {
            Collator collator = Collator.getInstance(Locale.getDefault());

            @Override
            public int compare(LectureWithData c1, LectureWithData c2) {
                return collator.compare(c1.getTopic(), c2.getTopic());
            }
        });
        setLectures(arr);
    }
    /**
     * Calculates the time intervals between lectures in the curriculum and returns the minimal period.
     * @return the minimal period between events
     */
    public Period timeIntervalsBetweenEvents() {
        LectureWithData[] lectureWithData = getLectureWithData();
        Arrays.sort(lectureWithData, Comparator.comparing(LectureWithData::getTimeOfEditing));

        Period minimalPeriod = Period.between(lectureWithData[0].getTimeOfEditing().toLocalDate(),
                lectureWithData[1].getTimeOfEditing().toLocalDate());
        Period tempPeriod;

        for (int i = 0; i < lectureWithData.length; i++) {
            for (int j = i + 1; j < lectureWithData.length; j++) {
                tempPeriod = Period.between(lectureWithData[i].getTimeOfEditing().toLocalDate(),
                        lectureWithData[j].getTimeOfEditing().toLocalDate());
                if (tempPeriod.getYears() <= minimalPeriod.getYears()
                        && tempPeriod.getMonths() <= minimalPeriod.getMonths()
                        && tempPeriod.getDays() <= minimalPeriod.getDays()) {
                    minimalPeriod = tempPeriod;
                }
            }
        }
        return minimalPeriod;
    }
    /**
     * Returns an array of lectures with additional data.
     * @return an array of lectures with additional data
     */
    private LectureWithData[] getLectureWithData() {
        return Arrays.copyOf(getLecture(), getLecture().length, LectureWithData[].class);
    }
    /**
     * Overrides the test method to demonstrate the usage of the CurriculumWithLocalize class.
     * It performs various operations on the curriculum and prints the results in a localized manner.
     */
    @Override
    public void test() {
        ResourceBundle bundle = ResourceBundle.getBundle("curriculum", Locale.getDefault());
        System.out.println(bundle.getString("findLowestAmountOfStudent") + ": " +
                findLowestAmountOfStudent(getLecture()) + "\n" +
                bundle.getString("findWord") + " " + bundle.getString("word") + ": " +
                getLecturesWithSpecificWord(getLecture(), bundle.getString("word")) + "\n" +
                bundle.getString("lastLetter") + ": " + lastLetter() + "." + "\n" +
                bundle.getString("dateInterval") + "\t" + timeIntervalsBetweenEvents().getMonths() + "\n");
        timeIntervalsBetweenEvents();
        SortByCommentLength();
        System.out.println("\n" + bundle.getString("SortByCommentLength"));
        System.out.println(this);

        sortByTopic();
        System.out.println("\n" + bundle.getString("sortByTopic"));
        System.out.println(this);
    }
    /**
     * Creates a new CurriculumWithLocalize object with pre-defined values.
     * @return the created CurriculumWithLocalize object
     */
    public AbstractCurriculum create() {
        ResourceBundle bundle = ResourceBundle.getBundle("curriculum", Locale.getDefault());
        setName(bundle.getString("name"));
        setLectureSurname(bundle.getString("LectureSurname"));
        List<LectureWithData> lecture = new ArrayList<>();
        lecture.add(new LectureWithData("2008-10-21 10:22:59", 24, "topic1"));
        lecture.add(new LectureWithData("2008-11-19 11:12:00", 19, "topic2"));
        lecture.add(new LectureWithData("2008-12-21 16:00:59", 20, "topic3"));
        lecture.add(new LectureWithData("2008-07-21 10:22:59", 17, "topic4"));
        this.setLectures(lecture.toArray(new LectureWithData[0]));
        return this;
    }
    /**
     * The main method of the CurriculumWithLocalize class, which demonstrates the usage of the class.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en"));
        new CurriculumWithLocalize().create().test();
        Locale.setDefault(new Locale("uk"));
        new CurriculumWithLocalize().create().test();
    }
}

