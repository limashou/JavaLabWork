package TaskOne.old;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CurriculumWithSortedSet extends AbstractCurriculum {
    private String name;
    private String lectureSurname;
    private SortedSet<Lecture> lectures;
    /** Constructor of CurriculumWithSortedSet
     * @param name name of exhibition
     * @param lectureSurname lector surname
     * @param lectures array of Lecture class objects     */
    public CurriculumWithSortedSet(String name, String lectureSurname, SortedSet<Lecture> lectures) {
        super(name, lectureSurname);
        this.lectures = lectures;
    }
    /**
     * Overrode function getName() of Curriculum abstract class
     * @return name
     * */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Overrode function getLecture() of Curriculum abstract class
     * @return Lecture array
     * */
    @Override
    public Lecture[] getLecture() {
        return (Lecture[]) this.lectures.toArray(new Lecture[0]);
    }
    /**
     * Overrode function getLecture() of Curriculum abstract class
     * @return index
     * */
    public Lecture[] getLecture(int i) {
        return new Lecture[] {((Lecture[]) this.lectures.toArray(new Lecture[0]))[i]};
    }
    /**
     * Overrode function setName() of Curriculum abstract class
     * set name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Overrode function setLectureSurname() of Curriculum abstract class
     * set teacher's surname
     * */
    @Override
    public void setLectureSurname(String lectureSurname) {
        this.lectureSurname = lectureSurname;
    }
    /**
     * Overrode function getLectureSurname() of Curriculum abstract class
     * @return  teacher's surname
     * */
    @Override
    public String getLectureSurname() {
        return lectureSurname;
    }
    /**
     * Function which sorts topic
     * @return sorted array topic by descending
     * */
    public Lecture[] sortByCommentLength() {
        SortedSet<Lecture> sorted = new TreeSet<>(Comparator.comparing(Lecture::getTopic));
        sorted.addAll(this.lectures);
        return lectures.toArray(new Lecture[0]);
    }
    /**
     * Sorts an array of days objects by topic alphabetically<br>
     * By inclusion sorting method
     * lectures object Lecture array
     */
    public void sortByTopic() {
        SortedSet<Lecture> sortedLecture = new TreeSet<>(Comparator.comparing(Lecture::getTopic));
        sortedLecture.addAll(lectures);
        lectures = sortedLecture;
    }
    /**
     * function for testing the class
     * @param args are not used
     */
    public static void main(String[] args) {
        System.out.println("Using SortedSet");
        SortedSet<Lecture> lectures = new TreeSet<>();
        lectures.add(new Lecture("09.04" , 24,"First step in Java."));
        lectures.add(new Lecture("09.28", 19,"Work with files."));
        lectures.add(new Lecture("11.03" , 20,"Arrays and list."));
        lectures.add(new Lecture("09.23" ,17,"Simplex method."));
        new CurriculumWithSortedSet("Java","Lababi",lectures).test();
    }
}

