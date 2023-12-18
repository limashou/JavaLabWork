package taskFive;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * Task 5.
 * Class for creating files and reading data about the student and academic group from files
 */
public class SerialDeserialFuncs {
    public static void xStreamSerialXML(AcademicGroup academicGroup, String file){
        XStream xStream = new XStream();
        xStream.alias("academicGroup", AcademicGroup.class);
        String xml = xStream.toXML(academicGroup);
        try (FileWriter fw = new FileWriter(file); PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void xStreamDeserialXML(String file){
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("academicGroup", AcademicGroup.class);
        AcademicGroup academicGroup = (AcademicGroup) xStream.fromXML(new File(file));
        System.out.println("The AcademicGroup object is read from a file " + file + ":\n" + academicGroup);
    }
    public static void xStreamSerialJson(AcademicGroup academicGroup, String file){
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("AcademicGroup", AcademicGroup.class);
        String xml = xStream.toXML(academicGroup);
        try (FileWriter fw = new FileWriter(file);
             PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void xStreamDeserialJson(String file){
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("AcademicGroup", AcademicGroup.class);
        AcademicGroup academicGroup = (AcademicGroup) xStream.fromXML(new File(file));
        System.out.println("The AcademicGroup object is read from a file " + file + ":\n" + academicGroup);
    }
    public static void streamApiDeserial(String file){
        try (Stream<String> strings = Files.lines(Path.of(file))) {
            AcademicGroup academicGroup = new AcademicGroup();
            Iterator<String> iterator = strings.iterator();
            List<Student> students = new ArrayList<>();
            if (iterator.hasNext())
                academicGroup.fromLine(iterator.next());
            while (iterator.hasNext()) {
                students.add(new Student(iterator.next()));
            }
            academicGroup.setStudents(students.toArray(Student[]::new));
            System.out.println("The AcademicGroup object is read from a file " + file + ":\n" + academicGroup);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void streamApiSerial(AcademicGroup academicGroup, String file){
        Stream<String> stream = Arrays.stream(academicGroup.getStudents()).map(Student::toTextFile);
        stream = Stream.concat(Stream.of(academicGroup.toTextFile()), stream);
        try {
            Files.write(Path.of(file), stream.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
