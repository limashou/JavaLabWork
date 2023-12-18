package taskOne;

public class CurriculumWithArray extends AbstractCurriculum {
    private Lecture[] lectures;
    private String name;
    private String lectureSurname;
    public CurriculumWithArray(String name, String lectureSurname, Lecture[] lectures) {
        super(name,lectureSurname);
        this.lectures = lectures;
    }
    @Override
    public String getLectureSurname() {
        return lectureSurname;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public Lecture[] getLecture() {
        return this.lectures;
    }
    @Override
    public void setName(String name) { this.name = name; }
    @Override
    public void setLectureSurname(String lectureSurname) { this.lectureSurname = lectureSurname; }

}

