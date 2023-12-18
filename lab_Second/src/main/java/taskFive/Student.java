package taskFive;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 5
 * Student's class
 */
public class Student {
    private Long id;
    private String name;
    private int age;
    public Student(String line) {
        String[] splited = line.split(":");
        this.id = Long.valueOf(splited[0]);
        this.name = splited[1];
        this.age = Integer.parseInt(splited[2]);
    }
    public Student(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Student() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "\nName: " + name +
                "; age: " + age;
    }
    public String toTextFile(){
        return id + ":" + name + ":" + age;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
