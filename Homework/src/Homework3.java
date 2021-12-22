import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class Homework3 {

    /**
     * Default constructor--private to prevent instantiation.
     */

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     *
     * @updates s1
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s1| = (2 * |s1|) - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    s1 is updated to:
     *        s1 = a * <i> * <(i+j)/2> * <j> * d)
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1) {

        if (s1.length() >= 2) {
            int average = (s1.entry(0) + s1.entry(1)) / 2;
            int delete = s1.remove(0);
            smooth(s1);
            s1.add(0, delete);
            s1.add(1, average);
        }

//        for (int i = 0; i < s1.length() - 1 && s1.length() >= 2; i += 2) {
//            int average = (s1.entry(i) + s1.entry(i + 1)) / 2;
//            s1.add(i + 1, average);
//        }

    }

    private Homework3() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        Sequence<Integer> s1 = new Sequence1L<Integer>();
        s1.add(0, 2);
        s1.add(1, 4);
        s1.add(2, 6);
        smooth(s1);
        System.out.println(s1.toString());
    }

}
