package taskOne;

import java.util.Arrays;
/** A class that represents a curriculum with sorting.<br>
 * Child class of CurriculumArray.
 * Overrides sorting algorithms. */
public class CurriculumWithSorting extends CurriculumWithArray {
    /** Constructor of CurriculumSorting
     * @param name name of exhibition
     * @param lectureSurname lector surname
     * @param lectures array of Lecture class objects     */
    public CurriculumWithSorting(String name, String lectureSurname, Lecture[] lectures) {
        super(name,lectureSurname,lectures);
    }
    /** Function which sorts topic
     * @return sorted array topic by descending  */
    @Override
    public Lecture[] sortByCommentLength() {
        Lecture[] lectures = getLecture();
        Arrays.sort(lectures, new CompareByComment());
        return lectures;
    }
    /** Main function which calls test for the class
     * @param args command line arguments  */
    public static void main(String[] args) {
        Lecture[] Lababi = new Lecture[] {
                new Lecture("09.04" , 24,"First step in Java."),
                new Lecture("09.28", 19,"Work with files."),
                new Lecture("11.03" , 20,"Arrays and list."),
        };
        new CurriculumWithSorting("Lababi","Java",Lababi).test();
    }
}
