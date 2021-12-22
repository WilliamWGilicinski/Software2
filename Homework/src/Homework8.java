import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci
 */
public final class Homework8 {

    /**
     * Finds {@code x} in {@code q} and, if such exists, moves it to the front
     * of {@code q}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be searched
     * @param x
     *            the entry to be searched for
     * @updates q
     * @ensures <pre>
     * perms(q, #q)  and
     * if <x> is substring of q
     *  then <x> is prefix of q
     * </pre>
     */
    private static <T> void moveToFront(Queue<T> q, T x) {
        if (q.front().equals(x)) {
            q.enqueue(x);
        } else {
            T temp = q.dequeue();
            moveToFront(q, x);
            q.enqueue(temp);
        }

    }

    private Homework8() {
        // no code needed here
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        Queue<Integer> q = new Queue1L<>();
        int x = 5;
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);

        System.out.println(q.toString());
        moveToFront(q, x);
        System.out.println(q.toString());

    }

}
