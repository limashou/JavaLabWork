package TaskOne;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Maksym POLIATSKY KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * The task is individual
 * Option 20.
 * Class of the first entity - curriculum
 */
public abstract class AbstractCurriculum  {

    protected AbstractCurriculum() {
    }

    public abstract String getName();
    public abstract Lecture[] getLecture();
    /**
     * The function that finds the topic with the least number of student
     * @param lectures object Lecture array
     * @return day with lowest num of visitors
     */
    public String findLowestAmountOfStudent(Lecture[] lectures) {
        int lec = 0;
        int low = lectures[0].getStudents();
        for (int i = 1; i < lectures.length; i++) {
            if (lectures[i].getStudents() < low) {
                low = lectures[i].getStudents();
                lec = i;

            }
        }
        return lectures[lec].getTopic();
    }
    /** A function that displays a list of comments with a specific word in the console
     * @param lectures object Lecture array
     * @param word word to find    */
    public ArrayList<Lecture> getLecturesWithSpecificWord(Lecture[] lectures, String word) {
        ArrayList<Lecture> find = new ArrayList<>();
        Pattern pattern = Pattern.compile(word);
        Matcher matcher;
        for (Lecture lecture : lectures) {
            matcher = pattern.matcher(lecture.getTopic());
            if (matcher.find()) {
                find.add(lecture);
            }
        }
        return find;
    }
    /** Sorts an array of days objects by topic alphabetically<br>
     * By inclusion sorting method
     * lectures object Lecture array
     * @return sorted array of objects (or null if the search did not return results)     */
    public void sortByTopic() {
        Lecture[] lectures = getLecture();
        for (int i = 1; i < lectures.length; i++) {
            int j = i - 1;
            Lecture temp = lectures[i];
            while (j >= 0 && temp.getTopic().compareTo(lectures[j].getTopic()) <= 0) {
                lectures[j + 1] = lectures[j];
                lectures[j] = temp;
                --j;
            }
        }
    }
    /** Function which sorts topic
     * @return sorted array topic by descending  */
    public void SortByCommentLength() {
        Lecture[] lectures = getLecture();
        for (int i = 0; i < lectures.length - 1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < lectures.length; j++)
                if (lectures[j].calculateWordsCount() < lectures[min_idx].calculateWordsCount())
                    min_idx = j;

            Lecture temp = lectures[min_idx];
            lectures[min_idx] = lectures[i];
            lectures[i] = temp;
        }
    }
    /** Outputs last letter of lector's surname  */
    public char lastLetter() {
        char last = ' ';
        for (int i = 0; i < this.getLectureSurname().length() ; i++) {
            if(i == this.getLectureSurname().length() - 1){
                char c = this.getLectureSurname().charAt(i);
                last = c;
            }
        }
        return last;
    }
    /** Prints information about curriculum and unsorted array lecture
     * @param lectures object Lecture array     */
    public void printCurriculum(Lecture[] lectures){
        System.out.println(this);
        System.out.println("Lecture:");
        if (lectures != null){
            printLecture(lectures);
        }
    }
    /** Prints all information of the object lectures to the console
     * @param lectures object Lecture array    */
    public void printLecture(Lecture[] lectures) {
        for (int i = 0; i < lectures.length; i++) {
            System.out.println( '\"'+ lectures[i].getDate() +'\"' + "Number of students: " + lectures[i].getStudents() + "\t" + '\"' + lectures[i].getTopic() + '\"');
        }
    }
    /** Overrode function toString()
     * @return Converts objects parameters into string format sentences and returns it.     */
    @Override
    public String toString() {
        return "Name of curriculum = \"" + getName() + '\"' + ", Lecture surname = \"" + getLectureSurname() + '\"' + '.';
    }
    /** Function to access the lector's surname
     * @return lector's surname    */
    public abstract void setName(String name);
    public abstract void setLectureSurname(String lectureSurname);
    public AbstractCurriculum(String name, String lectureSurname){
        setName(name);
        setLectureSurname(lectureSurname);
    }
    public abstract String getLectureSurname();
    public void test() {
        String word = "Java";
        printCurriculum(getLecture());
        System.out.println("\nLecture with the least number of students: " + findLowestAmountOfStudent(getLecture()));
        System.out.println("List of lectures with a word <" + word + ">:");
        System.out.println(getLecturesWithSpecificWord(getLecture(), word));
        System.out.println("Last letter of lecture surname: " + lastLetter());
        System.out.println("\nSorted topic by number of word: ");
        SortByCommentLength();
        printLecture(getLecture());
        System.out.println("\nSorted topic by alphabet: ");
        sortByTopic();
        printLecture(getLecture());
    }
}
