import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;
import components.queue.Queue;
import components.queue.Queue1L;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddStringAB() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("A", "B");

        String a = "A";
        String b = "B";
        s.add(a, b);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddInteger12() {
        /*
         * Set up variables
         */
        Map s = this.constructorTest();
        Map sExpected = this.constructorRef();

        Integer one = 1;
        Integer two = 2;
        s.add(one, two);
        sExpected.add(one, two);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddTwoPairs() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.createFromArgsTest("A", "B", "C",
                "D");

        String a = "A", b = "B", c = "C", d = "D";
        s.add(a, b);
        s.add(c, d);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddManyPairs() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.createFromArgsTest("q", "w", "e",
                "r", "t", "y", "u", "i", "o", "p");

        String q = "q", w = "w", e = "e", r = "r", t = "t", y = "y", u = "u",
                i = "i", o = "o", p = "p";
        s.add(q, w);
        s.add(e, r);
        s.add(t, y);
        s.add(u, i);
        s.add(o, p);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveOnePair() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b");
        Map<String, String> sExpected = this.constructorRef();

        String a = "a";
        s.remove(a);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveTwoPair() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> sExpected = this.constructorRef();

        String a = "a", c = "c";
        s.remove(a);
        s.remove(c);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddRemoveAddString() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");
        Map<String, String> sExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f");

        String c = "c", d = "d";
        s.remove(c);
        s.add(c, d);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddRemoveAddQueueCharacter() {
        /*
         * Set up variables
         */
        Map s = this.constructorTest();
        Map sExpected = this.constructorRef();

        Queue<Integer> qu = new Queue1L<>();
        Character c = 'c';

        s.add(qu, c);
        s.remove(qu);
        s.add(qu, c);

        sExpected.add(qu, c);
        sExpected.remove(qu);
        sExpected.add(qu, c);
        /*
         * Assert that values of variables match expectations
         */
    }

    @Test
    public final void testRemoveAnyOnePair() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b");
        Map<String, String> sExpected = this.constructorRef();

        s.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveAnyManyPairs() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f", "g", "h");
        Map<String, String> sExpected = this.createFromArgsRef("a", "b", "c",
                "d", "e", "f", "g", "h");

        s.removeAny();
        sExpected.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveAnyValue() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d");
        Map<String, String> sExpected = this.createFromArgsRef("a", "b", "c",
                "d");

        Pair<String, String> sPair = s.removeAny();
        Pair<String, String> sExpectedPair = sExpected.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sExpectedPair, sPair);
    }

    @Test
    public final void testValue() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b");
        String valueExpected = "b";

        String valueTest = s.value("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(valueExpected, valueTest);
    }

    @Test
    public final void testValueManyIntegers() {
        /*
         * Set up variables
         */
        Map s = this.constructorTest();
        s.add(1, 2);
        s.add(3, 4);
        s.add(5, 6);
        s.add(7, 8);

        Integer valueExpected = 6;
        Integer valueTest = (Integer) s.value(5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(valueExpected, valueTest);
    }

    @Test
    public final void testHasKeyOne() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b");

        boolean sTest = s.hasKey("a");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(sTest);
    }

    @Test
    public final void testHasKeyThree() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");

        boolean sTest = s.hasKey("c");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(sTest);
    }

    @Test
    public final void testHasKeyOneFalse() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b");

        boolean sTest = s.hasKey("c");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(!sTest);
    }

    @Test
    public final void testHasKeyThreeFalse() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");

        boolean sTest = s.hasKey("f");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(!sTest);
    }

    @Test
    public final void testSizeOne() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b");

        int sExpected = 1;
        int sTest = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, sTest);
    }

    @Test
    public final void testSizeAddOne() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.constructorTest();

        s.add("a", "b");
        int sExpected = 1;
        int sTest = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, sTest);
    }

    @Test
    public final void testSizeRemoveOne() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");

        s.remove("c");
        int sExpected = 2;
        int sTest = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, sTest);
    }

    @Test
    public final void testSizeRemoveAny() {
        /*
         * Set up variables
         */
        Map<String, String> s = this.createFromArgsTest("a", "b", "c", "d", "e",
                "f");

        s.remove("c");
        int sExpected = 2;
        int sTest = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, sTest);
    }

}
