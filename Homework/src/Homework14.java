import components.binarytree.BinaryTree;

public class Homework14 {

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {

        boolean in = false;

        if (t.size() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);

            int difference = root.compareTo(x);
            if (difference == 0) {
                in = true;
            } else if (difference > 0) {
                in = isInTree(left, x);
            } else {
                in = isInTree(right, x);
            }
        }

        return in;

    }

    public static void main(String[] args) {

    }

}