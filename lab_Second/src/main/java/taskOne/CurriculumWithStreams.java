package taskOne;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №2
 * The task is individual
 * Option 20.
 * Class of the first entity using StreamAPI tools and serialization/deserialization to files
 */
public class CurriculumWithStreams extends CurriculumWithArrayList {
    private static final String FILE_XML = "CurriculumWithStreams.xml";
    private static final String FILE_JSON = "CurriculumWithStreams.json";
    private static final String FILE_TXT = "CurriculumWithStreams.txt";
    private static final Logger logger = LogManager.getLogger(CurriculumWithStreams.class);
    /**
     * Class constructor
     * @param name name of the curriculum
     * @param lectureSunarme teacher's surname
     * @param lectures lectures
     */
    public CurriculumWithStreams(String name, String lectureSunarme, List<Lecture> lectures) {
        super(name, lectureSunarme, lectures);
    }
    /**
     * Constructor without parameters
     */
    public CurriculumWithStreams() {super();}
    /**
     * The function that finds the topic with the least number of student
     * lectures object Lecture array
     * @return day with lowest num of visitors
     */
    public String findLowestAmountOfStudent() {
        Lecture[] lectures = getLecture();
        int minStudents = Arrays.stream(lectures).mapToInt(Lecture::getStudents).min().orElse(0);
       return Arrays.stream(lectures).filter(lecture -> lecture.getStudents() == minStudents).findFirst().
               orElse(null).getTopic();
    }
    /**
     * A function that displays a list of comments with a specific word in the console
     * @param lectures object Lecture array
     * @param word word to find
     * */
    @Override
    public String findWord(Lecture[] lectures, String word) {
        List<String> matchingTopics = Arrays.stream(lectures)
                .filter(lecture -> lecture.containsSpecificWord(word))
                .map(Lecture::toString)
                .collect(Collectors.toList());
        return String.join("\n", matchingTopics);
    }
    /**
     * Outputs last letter of lector's surname
     */
    @Override
    public char lastLetter() {
        return this.getLectureSurname().chars().mapToObj(ch -> (char) ch).reduce((first, second) -> second).orElse(' ');
    }
    /**
     * Function which sorts topic
     * @return sorted array topic by descending
     * */
    @Override
    public Lecture[] sortByCommentLength() {
        Lecture[] lectures = getLecture();
        return Arrays.stream(lectures).sorted(Comparator.comparingInt(Lecture::calculateWordsCount)).toArray(Lecture[]::new);
    }
    /**
     *  Sorts an array of days objects by topic alphabetically<br>
     * By inclusion sorting method
     * lectures object Lecture array
     * */
    @Override
    public void sortByTopic() {
        Lecture[] lectures = getLecture();
        List<Lecture> sortedLectures = Arrays.stream(lectures).sorted(Comparator.comparing(Lecture::getTopic)).collect(Collectors.toList());
        sortedLectures.forEach(System.out::println);
    }
    /**
     * Overridden toString method
     * @return an object as text
     */
    @Override
    public String toString() {
        return "\nName: " + getName() +
                "\nLast name of the teacher: " + getLectureSurname() +
                "\n" + Arrays.stream(getLectureArr())
                .map(Lecture::toString)
                .collect(Collectors.joining("\n"));

    }
    /**
     * function for testing classes and implementing the main task
     */
    @Override
    public void test() {
        System.out.println(this);
        String word = "Java";
        System.out.println("The main task:");
        Lecture[] lectures = getLecture();
        String result = findWord(lectures, word);
        System.out.println("=====================\nList of topics with the word " + word + " in the title:");
        System.out.println(result);
        System.out.println("=====================\nLecture with a minimum number of students:" + findLowestAmountOfStudent(getLecture()));
        System.out.println("=====================\nLast letter in the teacher's last name:" + lastLetter());
        System.out.println("=====================\nSorting:");
        System.out.println("=====================\nSorting by alphabet:");
        sortByTopic();
        System.out.println("=====================\nSort by descending comment length:");
        System.out.println(Arrays.stream(sortByCommentLength())
                .map(Lecture::toString)
                .collect(Collectors.joining("\n")));
        System.out.println("=====================\nWrite and read from files:");
        writeToTxt(FILE_TXT);
        readFromTxt(FILE_TXT);
        xStreamSerialXML(FILE_XML);
        xStreamDeserialXML(FILE_XML);
        xStreamSerialJson(FILE_JSON);
        xStreamDeserialJson(FILE_JSON);
    }
    /**
     * serialization to XML file
     * @param file file name
     */
    public void xStreamSerialXML(String file) {
        logger.info("Serializing to XML");
        XStream xStream = new XStream();
        xStream.alias("CurriculumWithStreams", Lecture.class);
        String xml = xStream.toXML(this);
        try (FileWriter fw = new FileWriter(file); PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * deserialization from xml file
     * @param file file name
     */
    public void xStreamDeserialXML(String file) {
        logger.info("Deserializing from XML");
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("CurriculumWithStreams", Lecture.class);
        CurriculumWithStreams curriculumWithStreams = (CurriculumWithStreams) xStream.fromXML(new File(file));
        setName(curriculumWithStreams.getName());
        setLectureSurname(curriculumWithStreams.getLectureSurname());
        System.out.println("The CurriculumWithStreams object is read from the file " + file + ":\n" + this);
    }
    /**
     * serialization to Json file
     * @param file file name
     */
    public void xStreamSerialJson(String file) {
        logger.info("Serializing to JSON");
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("CurriculumWithStreams", Lecture.class);
        String xml = xStream.toXML(this);
        try (FileWriter fw = new FileWriter(file);
             PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * deserialization from Json file
     * @param file file name
     */
    public void xStreamDeserialJson(String file) {
        logger.info("Deserializing from JSON");
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.alias("CurriculumWithStreams", Lecture.class);
        CurriculumWithStreams curriculumWithStreams = (CurriculumWithStreams) xStream.fromXML(new File(file));
        setName(curriculumWithStreams.getName());
        setLectureSurname(curriculumWithStreams.getLectureSurname());
        System.out.println("The CurriculumWithStreams object is read from the file " + file + ":\n" + this);
    }
    /**
     * serialization to text file
     * @param file file name
     */
    public void readFromTxt(String file) {
        logger.info("Read from text file");
        try (Stream<String> strings = Files.lines(Path.of(file))) {
            Iterator<String> iterator = strings.iterator();
            List<Lecture> lectures = new ArrayList<>();
            if (iterator.hasNext())
                setName(iterator.next());
            if (iterator.hasNext())
                setLectureSurname(iterator.next());
            while (iterator.hasNext()) {
                String topic = iterator.next();
                int students = Integer.parseInt(iterator.next());
                String date = iterator.next();
                lectures.add(new Lecture(date,students,topic));
            }
            setLectures(lectures);
            System.out.println("The CurriculumWithStreams object is read from the file " + file + ":\n" + this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * deserialization from text file
     * @param file file name
     */
    public void writeToTxt(String file) {
        logger.info("Write to text file");
        Stream<String> stream = Arrays.stream(getLectureArr())
                .map(lecture -> lecture.getData() + "\n" + lecture.getStudents() + "\n" + lecture.getTopic());
        stream = Stream.concat(Stream.of(getName(),getLectureSurname()), stream);
        try {
            Files.write(Path.of(file), stream.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * function for testing the class
     * @param args are not used
     */
    public static void main(String[] args) {
        logger.info("Program started");
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new Lecture("09.04" , 24,"First step in Java."));
        lectures.add(new Lecture("09.28", 19,"Work with files."));
        lectures.add(new Lecture("11.03" , 20,"Arrays and list."));
        lectures.add(new Lecture("09.23" ,17,"Simplex method."));
        CurriculumWithStreams curriculumWithStreams = new CurriculumWithStreams("Math","Olyxah", lectures);
        curriculumWithStreams.test();
        logger.info("Program finished");
    }
}