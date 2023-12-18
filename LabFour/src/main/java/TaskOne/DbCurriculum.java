package TaskOne;

import TaskOne.old.CurriculumWithStreams;

/**
 * @author Maksym POLIATSKYI KN-221a
 * Discipline "Advanced course of Java programming"
 * Laboratory work №4
 * The task is individual
 * Option 20.
 * The class of the DbCurriculum
 */
public class DbCurriculum extends CurriculumWithStreams {
    private long id = -1;
    /**
     * Returns the curriculum ID
     * @return curriculum ID
     */
    public long getId() {
        return id;
    }
    /**
     * Sets the curriculum ID
     * @param id curriculum ID
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * The constructor initializes the object with the default values
     */
    public DbCurriculum() {}
    public DbCurriculum(String name, String lectureSunarme) {
        setName(name);
        setLectureSurname(lectureSunarme);
    }
}
