package taskSix;

import taskFive.AcademicGroup;
import taskFive.Student;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 6.
 * Class for testing file creation and reading from json files
 * using the org.json library.
 */
public class Test {
    private static final String FILE_JSON = "AcademicGroupOrgJson.json";

    public static void main(String[] args){
        Student[] students = new Student[] {
                new Student(1L, "Evgen", 19),
                new Student(2L, "Ivan", 21),
                new Student(3L, "Lesha", 20)
        };
        AcademicGroup academicGroup = new AcademicGroup(1L, "KN-221a", students);
        TaskFiveWithOrgJson.orgJsonSerial(academicGroup, FILE_JSON);
        TaskFiveWithOrgJson.orgJsonDeserialization(FILE_JSON);
    }
}
