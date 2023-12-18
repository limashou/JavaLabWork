package taskFive;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 5
 * Class for testing the creation of files and reading data about the student and academic group from files
 */
public class Test {
    private static final String FILE_JSON = "AcademicGroup.json";
    private static final String FILE_XML = "AcademicGroup.xml";
    private static final String FILE_TXT = "AcademicGroup.txt";

    public static void main(String[] args){
        Student[] students = new Student[] {
                new Student(1L, "Evgen", 19),
                new Student(2L, "Ivan", 21),
                new Student(3L, "Lesha", 20)
        };
        AcademicGroup academicGroup = new AcademicGroup(1L, "KN-221a", students);
        SerialDeserialFuncs.xStreamSerialXML(academicGroup, FILE_XML);
        SerialDeserialFuncs.xStreamDeserialXML(FILE_XML);
        SerialDeserialFuncs.xStreamSerialJson(academicGroup, FILE_JSON);
        SerialDeserialFuncs.xStreamDeserialJson(FILE_JSON);
        SerialDeserialFuncs.streamApiSerial(academicGroup, FILE_TXT);
        SerialDeserialFuncs.streamApiDeserial(FILE_TXT);
    }
}
