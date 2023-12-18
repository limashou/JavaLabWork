package taskSix;

import taskFive.AcademicGroup;
import taskFive.Student;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 6.
 * Class for creating files and reading from json files
 * using the org.json library.
 */
public class TaskFiveWithOrgJson {
    public static void orgJsonSerial(AcademicGroup academicGroup, String file_name){
        JSONArray students = new JSONArray(academicGroup.getStudents());
        JSONObject academicGroupObject = new JSONObject();
        academicGroupObject
                .put("group_name", academicGroup.getGroup_name())
                .put("students", students);
        try (FileWriter file = new FileWriter(file_name)) {
            file.write(academicGroupObject.toString(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void orgJsonDeserialization(String file){
        try {
            JSONObject object = new JSONObject(new String(Files.readAllBytes(Paths.get(file))));
            AcademicGroup academicGroup = new AcademicGroup();
            academicGroup.setGroup_name(object.getString("group_name"));
            JSONArray students = object.getJSONArray("students");
            Student[] studentList = new Student[students.length()];
            for (int i = 0; i < students.length(); i++) {
                JSONObject student = students.getJSONObject(i);
                Long id = student.getLong("id");
                int age = student.getInt("age");
                String name = student.getString("name");
                Student someStudent = new Student(id, name, age);
                studentList[i] = someStudent;
            }
            academicGroup.setStudents(studentList);
            System.out.println("The AcademicGroup object is read from a file " + file + ":\n" + academicGroup);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
