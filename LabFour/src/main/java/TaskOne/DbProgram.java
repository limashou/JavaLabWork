package TaskOne;
import TaskOne.old.Lecture;

import java.util.ArrayList;
import java.util.List;

import static TaskOne.DbUtils.*;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №4
 * The task is individual
 * Option 20.
 * The main class
 */
public class DbProgram{
    /**
     * Demonstration of the program operation.
     * Data is imported from a JSON file, sorted, added, and deleted.
     * The result is exported to another JSON file
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Curriculums curriculums = createCurriculum();
        exportToJSON(curriculums, "Curriculum.json");
        curriculums = importFromJSON("Curriculum.json");
        createConnection();
        if (createDatabase()) {
            addAll(curriculums);
            showAll();
            System.out.println("\nFind word \"Java\":");
            findWord("Java");
            System.out.println("\nLast letter:");
            System.out.println(getCurriculumLastLetter("Java"));
            System.out.println("\nMinimal count of students");
            System.out.println(getCurriculumMinimalCountOfStudents("Java"));
            System.out.println("\nSort by alphabet:");
            showSortedByAlphabet("Java");
            System.out.println("\nSort by count:");
            showSortedByCount("Java");
            System.out.println("\nAdd Lecture:");
            addLecture("Java", new LectureForDb("Lylaki bab", 90, "2021.09.01"));
            showAll();
            System.out.println("\nRemove:");
            removeLecture("Java", "Lylaki bab");
            showAll();
            System.out.println("\nAdd:");
            addCurriculum(new DbCurriculum("Libybax", "Keramich "));
            addLecture("Java", new LectureForDb("Local date", 80, "07.12"));
            showAll();
            exportToJSON("CurriculumFromDB.json");
            closeConnection();
        }
    }
    /**
     * Creating a Curriculum object and filling it with data to demonstrate the program
     * @return an object that contains the necessary data to demonstrate the program
     */
    static Curriculums createCurriculum() {
        DbCurriculum curriculum = new DbCurriculum();
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new LectureForDb("First step in Java." , 24,"2021.09.04"));
        lectures.add(new LectureForDb("Work with files.", 19,"2021.09.28"));
        lectures.add(new LectureForDb("Arrays and list." , 20,"2021.11.03"));
        lectures.add(new LectureForDb("Simplex method." ,17,"2021.09.23"));
        curriculum.setLectureSurname("Vladislavich");
        curriculum.setName("Java");
        curriculum.setLectures(lectures);
        Curriculums curriculums = new Curriculums();
        curriculums.getList().add(curriculum);
        return curriculums;
    }
}
