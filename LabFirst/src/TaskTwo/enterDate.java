package TaskTwo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №1
 * The task 2
 * A class for validating and processing date inputs.
 */
public class enterDate {
    /**
     * The main method of the EnterDate class.
     * Validates date inputs and performs operations on valid dates.
     * @param args the command-line arguments
     * @throws ParseException if an error occurs during date parsing
     */
    public static void main(String[] args) throws ParseException {
        String[] dates = {"07.08.2021", "10,11.2010", "11,11,20122", "07.08.2010", "30.02.2007"};
        for (String date : dates) {
            if (check(date)) {
                outDate(date);
                outLocalDate(date);
                outCalendar(date);
                System.out.println();
            } else {
                System.out.println(date + "\tWrong date\n");
            }
        }
    }
    /**
     * Prints the parsed date using the Date class.
     * @param line the date string to be parsed
     * @throws ParseException if an error occurs during date parsing
     */
    public static void outDate(String line) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date = format.parse(line);
        System.out.println("Using Date:");
        System.out.println(date);
    }
    /**
     * Prints the parsed date using the Calendar class.
     * @param line the date string to be parsed
     * @throws ParseException if an error occurs during date parsing
     */
    private static void outCalendar(String line) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(line));
        System.out.println("Using Calendar:");
        System.out.println(calendar.getTime());
    }
    /**
     * Prints the parsed date using the LocalDate class.
     * @param line the date string to be parsed
     */
    public static void outLocalDate(String line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.uuuu").withResolverStyle(ResolverStyle.STRICT);
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(line, formatter);
        } catch (Exception e) {
            System.err.println("Invalid date: " + line);
            return;
        }
        System.out.println("Using LocalDate:");
        System.out.println(localDate);
    }
    /**
     * Checks if the given date string matches the format "dd.MM.yyyy".
     * @param line the date string to be checked
     * @return true if the date string matches the format, false otherwise
     */
    private static boolean check(String line) {
        Pattern pattern = Pattern.compile("^\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d$");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
}
