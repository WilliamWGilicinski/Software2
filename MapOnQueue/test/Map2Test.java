import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;

/**
 * Customized JUnit test fixture for {@code Map2}.
 */
public class Map2Test extends MapTest {

    @Override
    protected final Map<String, String> constructorTest() {
        return new Map2<String, String>();
    }

    @Override
    protected final Map<String, String> constructorRef() {
        return new Map1L<String, String>();
    }

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

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size
    @Test
    public void testConstructor() {
        Map<String, String> test = this.createFromArgsTest();
        Map<String, String> ref = this.createFromArgsRef();
        assertEquals(test, ref);
    }

    @Test
    public void testAdd() {
        Map<String, String> test = this.createFromArgsTest();
        Map<String, String> ref = this.createFromArgsRef("One", "Two");
        test.add("One", "Two");
        assertEquals(test, ref);
    }

    @Test
    public void testAdd3Pairs() {
        Map<String, String> test = this.createFromArgsTest();
        Map<String, String> ref = this.createFromArgsRef("One", "Two", "Two",
                "Three", "Three", "Four");
        test.add("One", "Two");
        test.add("Two", "Three");
        test.add("Three", "Four");
        assertEquals(ref, test);

    }

    @Test
    public void testRemove() {
        Map<String, String> test = this.createFromArgsTest("One", "Two");
        Map<String, String> ref = this.createFromArgsRef();
        test.remove("One");
        assertEquals(test, ref);
    }

    @Test
    public void testRemoveAny() {
        Map<String, String> test = this.createFromArgsTest("One", "Two");
        Map<String, String> ref = this.createFromArgsRef();
        test.removeAny();
        assertEquals(test, ref);
    }

    @Test
    public void testValue() {
        Map<String, String> test = this.createFromArgsTest("One", "Two");
        String two = "Two";
        String testTwo = test.value("One");
        assertEquals(testTwo, two);
    }

    @Test
    public void testHasKey() {
        Map<String, String> test = this.createFromArgsTest("One", "Two");
        boolean ref = true;
        boolean booleanTest = test.hasValue("Two");
        assertEquals(booleanTest, ref);
    }

    @Test
    public void testSize() {
        Map<String, String> test = this.createFromArgsTest("One", "Two");
        int ref = 1;
        int testSize = test.size();
        assertEquals(testSize, ref);
    }

}
