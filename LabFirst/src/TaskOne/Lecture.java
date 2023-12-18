package TaskOne;

import java.util.Objects;
import java.util.StringTokenizer;

public class Lecture implements Comparable<Lecture> {
    protected String date;
    protected int students;
    protected String topic;
    /** Constructor of class Lecture
     * @param students number of students
     * @param topic topic about today lecture
     * @param date data */
    public Lecture(String date,int students, String topic) {
        this.date = date;
        this.students = students;
        this.topic = topic;
    }

    public Lecture() { }
    /** Check if topic contains specific word
     * @param word specific word
     * @return true - if the word is in the comment<br>
     * false - if not     */
    public boolean containsSpecificWord(String word) {
        StringTokenizer sr = new StringTokenizer(topic);
        String s;
        while(sr.hasMoreTokens()) {
            s = sr.nextToken();
            if (s.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }
    /** Function which calculates the amount of words in comment to the measure
     * @return int amount of words in measure  */
    public int calculateWordsCount() {
        int wordsCount = 0;
        for (int i = 0; i < topic.length() - 1; i++) {
            if (topic.charAt(i) == ' ' && topic.charAt(i+1) != ' ')
                wordsCount++;
        }
        return wordsCount;
    }
    /** Overrode function of Comparable interface<br>
     * Compares links and then parameters of objects.
     * * @param o object to compare
     * * @return true - equal<br>
     * * false - not equal     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)  return true;
        if (!(obj instanceof Lecture that)) return false;
        return getStudents() == that.getStudents()
                && getTopic().equals(that.getTopic())
                && getDate().equals(that.getDate());
    }
    /** Overrode function hashCode()
     * @return converts objects parameters into string format sentences and returns it    */
    @Override
    public int hashCode() {
        return Objects.hash(students,topic, date);
    }
    /** Overrode function compareTo()
     * @param o object to compare
     * @return Returns some int less 0 if first string is less than second string, <br> 0 if strings are equal, more than 1 if first string is bigger than second.    */
    @Override
    public int compareTo(Lecture o) {
        return Integer.compare(this.getStudents(),o.getStudents());
    }
    /** Overrode function toString()
     * @return Converts objects parameters into string format sentences and returns it.     */
    @Override
    public String toString() {
        return '\"' + date + '\"' + "Number of students:" + students + "\t" + '\"' + topic + '\"';
    }
    /** Function to access the date
     * @return date   */
    public String getDate() {
        return date;
    }
    /**
     * setter of the date
     */
    public void setDate(String date) {this.date = date;}
    /**
     * setter of the topic
     * @return topic
     */
    public String getTopic() {
        return topic;
    }
    /**
     * setter of the topic
     * @param topic
     */
    public void setTopic(String topic) {this.topic = topic; }
    /** Function to access the students
     * @return students  */
    public int getStudents() {
        return students;
    }
    /**
     * setter of the temperature
     * @param students
     */
    public void setStudents(int students) {this.students = students; }
    /** Function of testing Lecture class
     * @param tm  */
    protected void testLecture(Lecture tm) {
        setStudents(22);
        setDate("19.08");
        setTopic("Meaning of new Class[0]");
        System.out.print(this);
        System.out.println(this.equals(tm));
        System.out.println(this.hashCode());
        System.out.println(this.compareTo(tm));
        System.out.println(this.calculateWordsCount());
    }
    /**  Main function which calls test for the class
     * @param args command line arguments  */
    public static void main(String[] args) {
        new Lecture().testLecture(new Lecture("date",21,
                "First step"));
    }

}
