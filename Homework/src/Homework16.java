import java.util.Comparator;

import components.queue.Queue;

public class Homework16 {

    /**
     * Partitions {@code q} into two parts: entries no larger than
     * {@code partitioner} are put in {@code front}, and the rest are put in
     * {@code back}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be partitioned
     * @param partitioner
     *            the partitioning value
     * @param front
     *            upon return, the entries no larger than {@code partitioner}
     * @param back
     *            upon return, the entries larger than {@code partitioner}
     * @param order
     *            ordering by which to separate entries
     * @clears q
     * @replaces front, back
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * perms(#q, front * back)  and
     * for all x: T where (<x> is substring of front)
     *  ([relation computed by order.compare method](x, partitioner))  and
     * for all x: T where (<x> is substring of back)
     *  (not [relation computed by order.compare method](x, partitioner))
     * </pre>
     */
    private static <T> void partition(Queue<T> q, T partitioner, Queue<T> front,
            Queue<T> back, Comparator<T> order) {

        while (q.length() > 0) {
            T temp = q.dequeue();
            if (order.compare(temp, partitioner) < 0) {
                back.enqueue(temp);
            } else {
                front.enqueue(temp);
            }
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
//        if (this.length() > 1) {
//            /*
//             * Dequeue the partitioning entry from this
//             */
//            T partitioner = this.dequeue();
//            /*
//             * Partition this into two queues as discussed above (you will need
//             * to declare and initialize two new queues)
//             */
//            Queue<T> left = this.newInstance();
//            Queue<T> right = this.newInstance();
//            partition(this, partitioner, left, right, order);
//            /*
//             * Recursively sort the two queues
//             */
//            left.sort(order);
//            right.sort(order);
//            /*
//             * Reconstruct this by combining the two sorted queues and the
//             * partitioning entry in the proper order
//             */
//            this.append(left);
//            this.enqueue(partitioner);
//            this.append(right);
//        }
//    }

    public static void main(String[] args) {

    }

}