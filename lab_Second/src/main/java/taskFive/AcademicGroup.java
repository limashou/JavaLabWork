package taskFive;

import java.util.Arrays;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 5
 * Class of the academic group
 */
public class AcademicGroup {
    private Long id;
    private String group_name;
    private Student[] students;
    public AcademicGroup() {}
    public AcademicGroup(Long id, String group_name, Student[] students) {
        this.id = id;
        this.group_name = group_name;
        this.students = students;
    }
    public void fromLine(String line){
        String[] splite = line.split(":");
        this.id = Long.valueOf(splite[0]);
        this.group_name = splite[1];
    }
    public String getGroup_name() {
        return group_name;
    }
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
    public Student[] getStudents() {
        return students;
    }
    public void setStudents(Student[] students) {
        this.students = students;
    }
    @Override
    public String toString() {
        return "Students of the academic group " + group_name + ":\n" +
                Arrays.toString(students);
    }
    public String toTextFile(){
        return id + ":" + group_name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
