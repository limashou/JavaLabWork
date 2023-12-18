package TaskOne;

import TaskOne.old.Lecture;

/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №4
 * The task is individual
 * Option 20.
 * Class of the lectureForDb
 */

public class LectureForDb extends Lecture {
    private long id = -1;

    /**
     * Returns the lecture ID
     * @return lecture ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the lecture ID
     * @param id ID of the lecture
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * The constructor initializes the object with the default values
     */
    public LectureForDb() {}
    public LectureForDb(String topic,int students, String data) {
        super(topic, students, data);
    }
}
