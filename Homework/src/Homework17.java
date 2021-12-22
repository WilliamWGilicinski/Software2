import components.binarytree.BinaryTree;

public class Homework17 {

    /**
     * Checks if the given {@code BinaryTree<Integer>} satisfies the heap
     * ordering property according to the <= relation.
     *
     * @param t
     *            the binary tree
     * @return true if the given tree satisfies the heap ordering property;
     *         false otherwise
     * @ensures <pre>
     * satisfiesHeapOrdering = [t satisfies the heap ordering property]
     * </pre>
     */
    private static boolean satisfiesHeapOrdering(BinaryTree<Integer> t) {

        boolean satisfied = true;

        if (t.size() > 0) {
            BinaryTree<Integer> right = t.newInstance();
            BinaryTree<Integer> left = t.newInstance();
            Integer root = t.disassemble(left, right);

            if (right.size() > 0) {
                satisfied = (root.compareTo(right.root()) < 0);
                satisfied = satisfiesHeapOrdering(right);
            }
            if (left.size() > 0) {
                satisfied = (root.compareTo(left.root()) < 0);
                satisfied = satisfiesHeapOrdering(right);
            }

            t.assemble(root, left, right);

        }

        return satisfied;
    }

    public static void main(String[] args) {

    }

}