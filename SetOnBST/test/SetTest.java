import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Colton Davie and William Gilicinski
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public final void testNoArgsConstructor() {
        /*
         * Set up variables
         */
        Set<String> n = this.constructorTest();
        Set<String> nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test
    public final void testSizeZero() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest();
        Set<String> nExpected = this.createFromArgsRef();

        int test = n.size();
        int ref = nExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testSizeEmptyString() {
        /*
         * Set up variables
         */
        String testParam = "";
        String refParam = "";
        Set<String> n = this.createFromArgsTest(testParam);
        Set<String> nExpected = this.createFromArgsRef(refParam);

        int test = n.size();
        int ref = nExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testSizeNonZero() {
        /*
         * Set up variables
         */
        String testParam = "Bubble";
        String refParam = "Bubble";
        Set<String> n = this.createFromArgsTest(testParam);
        Set<String> nExpected = this.createFromArgsRef(refParam);

        int test = n.size();
        int ref = nExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testContainsEmptySet() {
        /*
         * Set up variables
         */
        String testParam = "car";
        String refParam = "car";
        Set<String> n = this.createFromArgsTest();
        Set<String> nExpected = this.createFromArgsRef();

        boolean test = n.contains(testParam);
        boolean ref = nExpected.contains(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(testParam, refParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testContainsEmptyString() {
        /*
         * Set up variables
         */
        String testParam = "";
        String refParam = "";
        Set<String> n = this.createFromArgsTest("apple", testParam);
        Set<String> nExpected = this.createFromArgsRef("apple", refParam);

        boolean test = n.contains(testParam);
        boolean ref = nExpected.contains(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testContainsFalse() {
        /*
         * Set up variables
         */
        String testParam = "";
        String refParam = "";
        Set<String> n = this.createFromArgsTest("apple", testParam);
        Set<String> nExpected = this.createFromArgsRef("apple", refParam);

        boolean test = n.contains("bar");
        boolean ref = nExpected.contains("bar");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testAddFromEmpty() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest();
        Set<String> nExpected = this.createFromArgsRef();

        n.add("apple");
        nExpected.add("apple");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test
    public final void testAddFromNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest("apple");
        Set<String> nExpected = this.createFromArgsRef("apple");

        n.add("bar");
        nExpected.add("bar");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test
    public final void testAddEmptyString() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest("apple");
        Set<String> nExpected = this.createFromArgsRef("apple");

        n.add("");
        nExpected.add("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    @Test
    public final void testRemoveLeavingEmpty() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest("apple");
        Set<String> nExpected = this.createFromArgsRef("apple");

        String test = n.remove("apple");
        String ref = nExpected.remove("apple");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testRemoveLeavingOne() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest("apple", "bar");
        Set<String> nExpected = this.createFromArgsRef("apple", "bar");

        String test = n.remove("bar");
        String ref = nExpected.remove("bar");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testRemoveAnyLeavingEmpty() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest("apple");
        Set<String> nExpected = this.createFromArgsRef("apple");

        String test = n.removeAny();
        String ref = nExpected.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testRemoveAnyLeavingOne() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest("apple", "bar");
        Set<String> nExpected = this.createFromArgsRef("apple", "bar");

        String test = n.removeAny();
        String ref = nExpected.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ref, test);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testContainsRemove() {
        /*
         * Set up variables
         */
        Set<String> n = this.createFromArgsTest("apple", "bar");
        Set<String> nExpected = this.createFromArgsRef("apple", "bar");

        String testWord = "apple";
        String expectedWord = "apple";

        if (n.contains(testWord)) {
            n.remove(testWord);
        }

        if (nExpected.contains(expectedWord)) {
            nExpected.remove(expectedWord);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
        assertEquals(expectedWord, testWord);
    }
}
