import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @replaces s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1 != s2 : "Violation of: s1 is not s2";
        assert s1.length() >= 1 : "Violation of: |s1| >= 1";

        while (s2.length() > 0) {
            s2.remove(0);
        }

        for (int i = 0; i < s1.length() - 1; i++) {
            int x = (s1.entry(i) + s1.entry(i + 1)) / 2;
            s2.add(i, x);
        }
//
//        if (s1.length() >= 2) {
//            int x = (s1.entry(0) + s1.entry(1)) / 2;
//            int y = s1.remove(0);
//            smooth(s1, s2);
//            s2.add(0, x);
//            s1.add(0, y);
//        } else {
//            while (s2.length() > 0) {
//                s2.remove(0);
//            }
//        }

    }

    public static void main(String[] args) {
        Sequence<Integer> s1 = new Sequence1L<Integer>();
        s1.add(0, 2);
        s1.add(1, 4);
        s1.add(2, 6);
        Sequence<Integer> s2 = new Sequence1L<Integer>();
        s2.add(0, 2);
        smooth(s1, s2);
        System.out.println(s2.toString());
        System.out.println(s1.toString());
    }

}