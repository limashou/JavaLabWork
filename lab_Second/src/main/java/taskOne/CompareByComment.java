package taskOne;

import java.util.Comparator;
/** Class which contains realisation of Comparator interface  */
public class CompareByComment implements Comparator<Lecture> {
    @Override
    public int compare(Lecture tm1, Lecture tm2) {
        return Integer.compare(tm1.getTopic().length(),tm2.getTopic().length());
    }
}
