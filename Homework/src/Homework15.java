import java.util.Comparator;

import components.queue.Queue;

public class Homework15 {

    /**
     * Inserts the given {@code T} in the {@code Queue<T>} sorted according to
     * the given {@code Comparator<T>} and maintains the {@code Queue<T>}
     * sorted.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to insert into
     * @param x
     *            the {@code T} to insert
     * @param order
     *            the {@code Comparator} defining the order for {@code T}
     * @updates q
     * @requires <pre>
     * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(q, #q * <x>)  and
     * IS_SORTED(q, [relation computed by order.compare method])
     * </pre>
     */
    private static <T> void insertInOrder(Queue<T> q, T x,
            Comparator<T> order) {
        for (int i = 0; i < q.length(); i++) {
            T temp = q.front();
            if (order.compare(x, temp) < 0) {
                q.enqueue(x);
            }
            q.rotate(1);
        }
    }

    /**
     * Sorts {@code this} according to the ordering provided by the
     * {@code compare} method from {@code order}.
     *
     * @param order
     *            ordering by which to sort
     * @updates this
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(this, #this)  and
     * IS_SORTED(this, [relation computed by order.compare method])
     * </pre>
     */
//    public void sort(Comparator<T> order) {
//        Queue<T> sorted = new Queue2<>();
//        while(this.length() > 0) {
//            insertInOrder(sorted, this.dequeue(), order);
//        }
//        this.transferFrom(sorted);
//    }

    /**
     * Integer greater-than-or-equal-to Comparator. This effect is achieved by
     * reversing the natural ordering provided by interface Comparable's
     * compareTo, which Integer implements as less-than-or-equal-to.
     */
    private static class IntegerGE implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    public static void main(String[] args) {

    }

}