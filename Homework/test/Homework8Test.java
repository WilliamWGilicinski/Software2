import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class Homework8Test {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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
    public void testAdd() {
        Set<String> test = this.createFromArgsTest();
        Set<String> ref = this.createFromArgsRef("Hello");
        test.add("Hello");
        assertEquals(test, ref);
    }

    @Test
    public void testRemove() {
        Set<String> test = this.createFromArgsTest("Hello");
        Set<String> ref = this.createFromArgsRef();
        test.remove("Hello");
        assertEquals(test, ref);
    }

    @Test
    public void testRemoveAny() {
        Set<String> test = this.createFromArgsTest("Hello", "There");
        Set<String> ref = this.createFromArgsRef("There");
        test.removeAny();
        assertEquals(test, ref);
    }

    @Test
    public void testContains() {
        Set<String> test = this.createFromArgsTest("Hello");
        boolean ref = true;
        boolean testTrue = test.contains("Hello");
        assertEquals(testTrue, ref);
    }

    @Test
    public void testSize() {
        Set<String> test = this.createFromArgsTest("Hello");
        int sizeRef = 1;
        int sizeTest = test.size();
        assertEquals(sizeTest, sizeRef);
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

}