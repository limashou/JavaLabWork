package TaskOne.old;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CurriculumWithArrayList extends AbstractCurriculum {
    private String name;
    private String lectureSurname;
    private List<Lecture> lectures;
    /** Constructor of CurriculumWithArrayList
     * @param name name of exhibition
     * @param lectureSunarme lector surname
     * @param lectures array of Lecture class objects     */
    public CurriculumWithArrayList(String name, String lectureSunarme, List<Lecture> lectures) {
        super(name, lectureSunarme);
        this.lectures = lectures;
    }
    /**
     * Constructor without parameters
     */
    public CurriculumWithArrayList() {
        super();
    }
    /**
     * Overrode function getLecture() of Curriculum abstract class
     * @return Lecture array
     * */
    public Lecture[] getLectureArr() {
        if(lectures == null) {
            lectures = new ArrayList<>();
        }
        return lectures.toArray(Lecture[]::new);
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
        if(lectures == null) {
            lectures = new ArrayList<>();
        }
        Lecture[] array = new Lecture[lectures.size()];
        return lectures.toArray(array);
    }
    /**
     * Overrode function getLecture() of Curriculum abstract class
     * @return index
     * */
    public Lecture getLecture(int i) {
        return lectures.get(i);
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
     * Overrode function setLecture() of Curriculum abstract class
     * */
    public void setLectures(List<Lecture> lectures){
        this.lectures = lectures;
    }
    /**
     * Overrode function getLectureSurname() of Curriculum abstract class
     * @return  teacher's surname
     * */
    @Override
    public String getLectureSurname() {
        if(lectureSurname == null) {
            return " ";
        }
        return lectureSurname;
    }
    /**
     * Function which sorts topic
     * @return sorted array topic by descending
     * */
    @Override
    public Lecture[] sortByCommentLength() {
        Collections.sort(lectures);
        return new Lecture[0];
    }
    /**
     * Sorts an array of days objects by topic alphabetically<br>
     * By inclusion sorting method
     * lectures object Lecture array
     */
    @Override
    public void sortByTopic() {
        lectures.sort(Comparator.comparing(Lecture::getTopic));
    }
    /**
     * function for testing the class
     * @param args are not used
     */
    public static void main(String[] args) {
        System.out.println("Using List");
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new Lecture("09.04" , 24,"First step in Java."));
        lectures.add(new Lecture("09.28", 19,"Work with files."));
        lectures.add(new Lecture("11.03" , 20,"Arrays and list."));
        lectures.add(new Lecture("09.23" ,17,"Simplex method."));
        new CurriculumWithArrayList("Java","Lababi",lectures).test();
    }

    public List<Lecture> getLectures() {
        return lectures;
    }
}
