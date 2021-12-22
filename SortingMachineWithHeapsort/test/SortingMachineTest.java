import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    //Tests constructor
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    //Test adding elements to an empty sorting machine
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        m.add("green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    //Tests the insertion mode when it is in extraction mode
    @Test
    public final void testIsInInsertionModeFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");

        boolean modeExpected = mExpected.isInInsertionMode();
        boolean mode = m.isInInsertionMode();

        assertEquals(modeExpected, mode);
        assertEquals(m, mExpected);
    }

    //Tests the insertion mode when it is in insertion mode
    @Test
    public final void testIsInInsertionModeTrue() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");

        boolean modeExpected = mExpected.isInInsertionMode();
        boolean mode = m.isInInsertionMode();

        assertEquals(modeExpected, mode);

        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();

        assertEquals(m, mExpected);
    }

    //Tests insertion mode when the sorting machine is empty and in insertion mode
    @Test
    public final void testIsInInsertionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        boolean modeExpected = mExpected.isInInsertionMode();
        boolean mode = m.isInInsertionMode();

        assertEquals(modeExpected, mode);
        assertEquals(m, mExpected);
    }

    //Test changing from insertion to extraction mode with no elements
    @Test
    public final void testChangeToExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    //Test changing from insertion mode to extration mode with an element
    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    //Test extraction mode when elements are out of order
    @Test
    public final void testChangeToExtractionModeOutOfOrder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "b",
                "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b");

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    //Test removing first and making the sorting machine empty
    @Test
    public final void testRemoveFirstLeavingEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();

        assertEquals(test, ref);
        assertEquals(mExpected, m);
    }

    //Test removing first and leaving the sorting machine filled
    @Test
    public final void testRemoveFirstLeavingNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    //Test machine order on empty sorting machine
    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        Comparator<String> test = m.order();
        Comparator<String> ref = mExpected.order();

        assertEquals(test, ref);
        assertEquals(mExpected, m);
    }

    //Test machine order on full sorting machine
    @Test
    public final void testOrderNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b");

        Comparator<String> test = m.order();
        Comparator<String> ref = mExpected.order();

        assertEquals(test, ref);
        assertEquals(mExpected, m);
    }

    //Test size on a non-empty sorting machine in extraction mode
    @Test
    public final void testSizeNonEmptyExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b");

        int test = m.size();
        int ref = mExpected.size();

        assertEquals(test, ref);
        assertEquals(mExpected, m);
    }

    //Tests size on an empty sorting machine in extraction mode
    @Test
    public final void testSizeEmptyExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        int test = m.size();
        int ref = mExpected.size();

        assertEquals(test, ref);
        assertEquals(mExpected, m);
    }

    //Test size on a non-empty sorting machine in insertion mode
    @Test
    public final void testSizeNonEmptyInsertionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "a",
                "b");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a", "b");

        int test = m.size();
        int ref = mExpected.size();

        assertEquals(test, ref);
        assertEquals(mExpected, m);
    }

    //Test size on an empty sorting machine in insertion mode
    @Test
    public final void testSizeEmptyInsertionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        int test = m.size();
        int ref = mExpected.size();

        assertEquals(test, ref);
        assertEquals(mExpected, m);
    }

    //Test removing first form a large heap already in total preorder
    @Test
    public final void testRemoveFirstLargeHeap() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a",
                "b", "c", "d", "e", "f", "g");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "a", "b", "c", "d", "e", "f", "g");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    //Test removing first from a large heap with random order
    @Test
    public final void testRemoveFirstLargeRandom() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "c",
                "f", "g", "a", "b", "d", "e");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "c", "f", "g", "a", "b", "d", "e");

        String test = m.removeFirst();
        String ref = mExpected.removeFirst();

        assertEquals(ref, test);
        assertEquals(mExpected, m);
    }

    //Test adding four from an empty sorting machine
    @Test
    public final void testAddFourFromEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "red", "yellow");
        m.add("green");
        m.add("blue");
        m.add("red");
        m.add("yellow");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    //Test adding one from a non empty sorting machine
    @Test
    public final void testAddFromNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "red");
        m.add("green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    //Test adding four from a non-empty sorting machine
    @Test
    public final void testAddFourFromNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "red", "blue", "yellow", "orange");
        m.add("green");
        m.add("blue");
        m.add("yellow");
        m.add("orange");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

}
