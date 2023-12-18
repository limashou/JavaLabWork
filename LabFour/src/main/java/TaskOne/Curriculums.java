package TaskOne;

import java.util.ArrayList;
import java.util.List;

public class Curriculums {
    private List<DbCurriculum> list = new ArrayList<>();

    /**
     * Returns a list of curriculum (java.util.List)
     * @return list of curriculum
     */
    public List<DbCurriculum> getList() {
        return list;
    }
    /**
     * Provides a representation of the object as a string
     * @return a list view as a string
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
