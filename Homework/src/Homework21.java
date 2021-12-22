import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.tree.Tree;

public class Homework21 {

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size(Tree<T> t) {

        int treeSize = 1;

        if (t.numberOfSubtrees() > 0) {
            int numChild = t.numberOfSubtrees();

            Sequence<Tree<T>> children = new Sequence1L<>();
            t.disassemble(children);

            treeSize += size(children.remove(0));

        }

        return treeSize;

    }

    /**
     * Returns the size of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose size to return
     * @return the size of the given {@code Tree}
     * @ensures size = |t|
     */
    public static <T> int size2(Tree<T> t) {

        int treeSize = 1;

        while (t.numberOfSubtrees() > 0) {

            int numChild = t.numberOfSubtrees();

            Sequence<Tree<T>> children = new Sequence1L<>();
            t.disassemble(children);

            for (int i = 0; i < numChild; i++) {

                t = children.entry(i);

            }

        }

        return treeSize;

    }

    /**
     * Returns the height of the given {@code Tree<T>}.
     *
     * @param <T>
     *            the type of the {@code Tree} node labels
     * @param t
     *            the {@code Tree} whose height to return
     * @return the height of the given {@code Tree}
     * @ensures height = ht(t)
     */
    public static <T> int height(Tree<T> t) {

        int max = 1;

        if (t.numberOfSubtrees() > 0) {

            max++;

            Sequence<Tree<T>> children = new Sequence1L<>();
            t.disassemble(children);

            int temp = height(children.remove(0));
            if (temp > max) {
                max = temp;
            }

        }
        return max;
    }

    /**
     * Returns the largest integer in the given {@code Tree<Integer>}.
     *
     * @param t
     *            the {@code Tree<Integer>} whose largest integer to return
     * @return the largest integer in the given {@code Tree<Integer>}
     * @requires |t| > 0
     * @ensures <pre>
     * max is in labels(t)  and
     * for all i: integer where (i is in labels(t)) (i <= max)
     * </pre>
     */
    public static int max(Tree<Integer> t) {

        int largest = t.root();

        if (t.numberOfSubtrees() > 0) {

            Sequence<Tree<Integer>> children = new Sequence1L<>();
            t.disassemble(children);

            int temp = max(children.remove(0));
            if (temp > largest) {
                largest = temp;
            }

        }
        return largest;
    }

    public static void main(String[] args) {

    }

}