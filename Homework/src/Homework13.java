import components.binarytree.BinaryTree;

public class Homework13 {

    /**
     * Returns the {@code String} prefix representation of the given
     * {@code BinaryTree<T>}.
     *
     * @param <T>
     *            the type of the {@code BinaryTree} node labels
     * @param t
     *            the {@code BinaryTree} to convert to a {@code String}
     * @return the prefix representation of {@code t}
     * @ensures treeToString = [the String prefix representation of t]
     */
    public static <T> String treeToString(BinaryTree<T> t) {

        String rep = "";

        if (t.size() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);

            rep += root.toString() + "(" + treeToString(left) + ")("
                    + treeToString(right) + ")";
            t.assemble(root, left, right);
        }

        return rep;

    }

    /**
     * Returns a copy of the the given {@code BinaryTree}.
     *
     * @param t
     *            the {@code BinaryTree} to copy
     * @return a copy of the given {@code BinaryTree}
     * @ensures copy = t
     */
    public static BinaryTree<Integer> copy(BinaryTree<Integer> t) {

        BinaryTree<Integer> h = t.newInstance();

        if (t.size() > 0) {
            BinaryTree<Integer> left = t.newInstance();
            BinaryTree<Integer> right = t.newInstance();
            Integer root = t.disassemble(left, right);

            h.assemble(root, copy(left), copy(right));
            t.assemble(root, left, right);
        }
        return h;
    }

    public static void main(String[] args) {

    }

}