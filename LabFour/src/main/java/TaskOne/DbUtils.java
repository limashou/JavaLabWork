package TaskOne;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №4
 * The task is individual
 * Option 20.
 * The class implements work with a database
 */
public class DbUtils {
    /**
     * List to determine the order in which census data is displayed
     */
    public enum Show {SORTED_ALPHABET, SORTED_BY_COUNT, UNSORTED}

    public static final String DROP_TABLES = "DROP TABLES IF EXISTS lecture, curriculum";
    public static final String DROP_DATABASE = "DROP DATABASE IF EXISTS curriculumDB";
    public static final String CREATE_DATABASE = "CREATE DATABASE curriculumDB";
    public static final String CREATE_TABLE_CURRICULUM = """
            CREATE TABLE curriculumDB.curriculums (
              CurriculumID INT NOT NULL AUTO_INCREMENT,
              Name VARCHAR(128) NULL,
              Lectures_Surname VARCHAR(128) NULL,
              PRIMARY KEY (CurriculumID));
            """;
    public static final String CREATE_TABLE_LECTURE = """
            CREATE TABLE curriculumDB.lecture (
              LectureID INT NOT NULL AUTO_INCREMENT,
              Topic VARCHAR(256) NULL,
              Students INT NULL,
              Date VARCHAR(256) NULL,
              CurriculumID INT NULL,
              PRIMARY KEY (LectureID),
              INDEX CurriculumID_idx (CurriculumID ASC) VISIBLE,
              CONSTRAINT CurriculumID
                FOREIGN KEY (CurriculumID)
                REFERENCES curriculumDB.curriculums (CurriculumID)
                ON DELETE NO ACTION
                ON UPDATE NO ACTION);
            """;
    private static final String INSERT_INTO_COUNTRIES = """
            INSERT INTO curriculumDB.curriculums (Name, Lectures_Surname) VALUES (?, ?);
            """;
    private static final String INSERT_INTO_CENSUSES = """
            INSERT INTO curriculumDB.lecture (Topic, Students, Date, CurriculumID) VALUES (?, ?, ?, ?);
            """;
    private static final String SELECT_BY_NAME = "SELECT * FROM curriculumDB.curriculums WHERE Name = ?";
    private static final String SELECT_ALL_CURRICULUM = "SELECT * FROM curriculumDB.curriculums";
    private static final String SELECT_FROM_LECTURE = "SELECT * FROM curriculumDB.lecture WHERE CurriculumID = ?";
    private static final String SELECT_FROM_LECTURE_BY_ASC =
            "SELECT *FROM curriculumDB.lecture WHERE CurriculumID=? ORDER BY Topic ASC ";
    private static final String SELECT_FROM_LECTURE_BY_COUNT = """
                 SELECT * FROM curriculumDB.lecture WHERE CurriculumID=? ORDER BY LENGTH(Topic) ASC
            """;
    private static final String SELECT_FROM_LECTURE_WHERE_WORD = """
                 SELECT c.LectureID, c.Topic, c.Students, c.Date, l.Name FROM curriculumDB.lecture c
                 INNER JOIN curriculumDB.curriculums l ON c.CurriculumID = l.CurriculumID WHERE c.Topic LIKE '%key_word%';
            """;
    private static final String DELETE_LECTURE_BY_TOPIC = "DELETE FROM curriculumDB.lecture WHERE CurriculumID=? AND Topic=?";
    private static final String GET_LECTURE_SURNAME_LAST_LETTER = """
            SELECT RIGHT(Lectures_Surname, 1) AS LastCharacter FROM curriculumDB.curriculums WHERE CurriculumID=?;
            """;

    private static final String LECTION_WITH_MINIMAL = """
            SELECT * FROM curriculumDB.lecture WHERE Students = ( SELECT MIN(Students) FROM curriculumDB.lecture );
            """;
    private static Connection connection;

    /**
     * Deserializes curriculum data from the specified XML file
     *
     * @param fileName file name
     * @return the object that was created
     */
    public static Curriculums importFromJSON(String fileName) {
        try {
            XStream xStream = new XStream(new JettisonMappedXmlDriver());
            xStream.addPermission(AnyTypePermission.ANY);
            xStream.alias("curriculums", Curriculums.class);
            xStream.alias("curriculum", DbCurriculum.class);
            xStream.alias("lecture", LectureForDb.class);
            return (Curriculums) xStream.fromXML(new File(fileName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Serializes country data to the specified JSON file
     *
     * @param curriculums curriculums
     * @param fileName    file name
     */
    public static void exportToJSON(Curriculums curriculums, String fileName) {
        XStream xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.alias("curriculums", Curriculums.class);
        xStream.alias("curriculum", DbCurriculum.class);
        xStream.alias("lecture", LectureForDb.class);
        String xml = xStream.toXML(curriculums);
        try (FileWriter fw = new FileWriter(fileName); PrintWriter out = new PrintWriter(fw)) {
            out.println(xml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Serializes curriculum data from the database to the specified JSON file
     *
     * @param fileName file name
     */
    public static void exportToJSON(String fileName) {
        Curriculums curriculums = getCurriculumFromDB();
        exportToJSON(curriculums, fileName);
    }

    /**
     * Create a connection to the database
     */
    public static void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creating a database and tables with deleting the previous ones
     */
    public static boolean createDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DROP_TABLES);
            statement.executeUpdate(DROP_DATABASE);
            statement.executeUpdate(CREATE_DATABASE);
            statement.executeUpdate(CREATE_TABLE_CURRICULUM);
            statement.executeUpdate(CREATE_TABLE_LECTURE);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Adds all curriculums from the given Curriculums object.
     */
    public static void addAll(Curriculums curriculums) {
        for (DbCurriculum c : curriculums.getList()) {
            addCurriculum(c);
        }
    }

    /**
     * Adds a single curriculum to the database.
     */
    public static void addCurriculum(DbCurriculum curriculum) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_COUNTRIES);
            preparedStatement.setString(1, curriculum.getName());
            preparedStatement.setString(2, curriculum.getLectureSurname());
            preparedStatement.execute();
            for (int i = 0; i < curriculum.getLecture().length; i++) {
                addLecture(curriculum.getName(), (LectureForDb) curriculum.getLecture(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a Curriculum object that stores all the data from the database
     *
     * @return the object in which the data about the curriculum is entered
     */
    public static Curriculums getCurriculumFromDB() {
        try {
            Curriculums curriculums = new Curriculums();
            Statement statement = connection.createStatement();
            ResultSet setOfCurriculum = statement.executeQuery(SELECT_ALL_CURRICULUM);
            while (setOfCurriculum.next()) {
                curriculums.getList().add(getCurriculumFromDB(setOfCurriculum));
            }
            return curriculums;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a curriculum object by filling it with data from the database
     *
     * @param name name of the curriculum
     * @return an object filled with data from the database
     */
    public static DbCurriculum getCurriculumByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet setOfCurriculum = preparedStatement.executeQuery();
            setOfCurriculum.next();
            return getCurriculumFromDB(setOfCurriculum);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the curriculum with the minimal count of students.
     *
     * @return The LectureForDb object representing the lecture with the minimal count of students,
     * or null if no lecture is found.
     */
    public static LectureForDb getCurriculumMinimalCountOfStudents(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(LECTION_WITH_MINIMAL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new LectureForDb(resultSet.getString("Topic"), resultSet.getInt("Students"), resultSet.getString("Date"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Retrieves the last letter of the lecture surname for a given curriculum.
     *
     * @return The last character of the lecture surname, or null if no lecture is found.
     */
    public static String getCurriculumLastLetter(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_LECTURE_SURNAME_LAST_LETTER);
            preparedStatement.setInt(1, getIdByName(name));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("LastCharacter");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates and returns a curriculum object from the result dataset
     *
     * @param setOfCurriculum the result dataset from which the curriculum data is retrieved
     * @return the created curriculum with lecture data
     * @throws SQLException an exception related to a SQL query error
     */
    public static DbCurriculum getCurriculumFromDB(ResultSet setOfCurriculum) throws SQLException {
        DbCurriculum curriculum = new DbCurriculum(setOfCurriculum.getString("Name"), setOfCurriculum.getString("Lectures_Surname"));
        int id = setOfCurriculum.getInt("CurriculumID");
        curriculum.setId(id);
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_LECTURE);
        preparedStatement.setInt(1, id);
        ResultSet setOfLecture = preparedStatement.executeQuery();
        while (setOfLecture.next()) {
            LectureForDb lecture = new LectureForDb(setOfLecture.getString("Topic"),
                    setOfLecture.getInt("Students"), setOfLecture.getString("Date"));
            lecture.setId(setOfLecture.getInt("LectureID"));
            curriculum.getLectures().add(lecture);
        }
        return curriculum;
    }

    /**
     * Gets the curriculum ID by name
     *
     * @param curriculumName name of the curriculum
     * @return curriculum ID in the database
     */
    public static int getIdByName(String curriculumName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, curriculumName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("CurriculumID");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays all data from the database on the console, sequentially for each curriculum
     */
    public static void showAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CURRICULUM);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> names = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                names.add(name);
            }
            resultSet.close();
            for (String name : names) {
                showCurriculum(name, Show.UNSORTED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays data about curriculums by name on the console
     *
     * @param curriculumName name of the curriculum
     * @param byPopulation   the order of output determined by the Show list
     */
    public static void showCurriculum(String curriculumName, Show byPopulation) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NAME);
            preparedStatement.setString(1, curriculumName);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.printf("%s\t  %s\t  %s%n", "ID", "Name", "Lecture's surname");
            resultSet.next();
            System.out.printf("%s\t  %s\t  %s%n", resultSet.getString("CurriculumID"),
                    resultSet.getString("Name"), resultSet.getString("Lectures_Surname"));
            resultSet.close();
            PreparedStatement anotherStatement;
            if (byPopulation == Show.SORTED_ALPHABET) {
                anotherStatement = connection.prepareStatement(SELECT_FROM_LECTURE_BY_ASC);
            } else if (byPopulation == Show.SORTED_BY_COUNT) {
                anotherStatement = connection.prepareStatement(SELECT_FROM_LECTURE_BY_COUNT);
            } else {
                anotherStatement = connection.prepareStatement(SELECT_FROM_LECTURE);
            }
            anotherStatement.setInt(1, getIdByName(curriculumName));
            ResultSet anotherSet = anotherStatement.executeQuery();
            System.out.printf("%s\t  %s\t  %s \t%s%n", "ID", "Topic", "Students", "Date");
            while (anotherSet.next()) {
                System.out.printf("%s\t  %s\t  %s\t\t%s%n",
                        anotherSet.getString("LectureID"), anotherSet.getString("Topic"),
                        anotherSet.getString("Students"), anotherSet.getString("Date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays the curriculum sorted by alphabet for the given curriculum name.
     */
    public static void showSortedByAlphabet(String curriculumName) {
        showCurriculum(curriculumName, Show.SORTED_ALPHABET);
    }

    /**
     * Displays the curriculum sorted by number of words increased.
     */
    public static void showSortedByCount(String curriculumName) {
        showCurriculum(curriculumName, Show.SORTED_BY_COUNT);
    }

    /**
     * Searches for a given word in the lecture information and prints the matching results.
     */
    public static void findWord(String word) {
        try {
            String query = SELECT_FROM_LECTURE_WHERE_WORD.replace("key_word", word);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s\t  %s\t  %s\t  %s\t\t%s%n",
                        resultSet.getString("LectureID"), resultSet.getString("Name"),
                        resultSet.getString("Topic"), resultSet.getString("Students"), resultSet.getString("Date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds information about the lecture of a specific curriculum to the database
     *
     * @param curriculumName name of the curriculum whose lecture is added
     * @param lecture        the lecture to be added
     */
    public static void addLecture(String curriculumName, LectureForDb lecture) {
        DbCurriculum curriculum = getCurriculumByName(curriculumName);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CENSUSES);
            preparedStatement.setString(1, lecture.getTopic());
            preparedStatement.setInt(2, lecture.getStudents());
            preparedStatement.setString(3, lecture.getData());
            preparedStatement.setInt(4, getIdByName(curriculum.getName()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes the specified lecture from the database
     *
     * @param curriculumName curriculum name
     */
    public static void removeLecture(String curriculumName, String topic) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LECTURE_BY_TOPIC);
            preparedStatement.setInt(1, getIdByName(curriculumName));
            preparedStatement.setString(2, topic);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the connection to the database
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
