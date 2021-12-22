import components.binarytree.BinaryTree;

public class Homework12 {

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size(BinaryTree<T> t) {

        int count = 0;

        BinaryTree<T> empty = t.newInstance();
        if (!t.equals(empty)) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);

            count = size(t) + 1;
        }

        return count;

    }

    /**
     * Returns the size of the given {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} whose size to return
     * @return the size of the given {@code BinaryTree}
     * @ensures size = |t|
     */
    public static <T> int size2(BinaryTree<T> t) {

        int count = 0;

        for (T x : t) {
            count++;
        }

        return count;

    }

    public static void main(String[] args) {

    }

}