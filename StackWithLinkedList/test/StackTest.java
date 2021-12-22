import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    @Test
    public final void testConstructorNoArgs() {

        Stack<String> test = this.constructorTest();
        Stack<String> ref = this.constructorRef();

        assertEquals(ref, test);

    }

    @Test
    public final void testPushFromEmpty() {

        String hello = "Hello";

        Stack<String> s = this.createFromArgsTest();
        Stack<String> sRef = this.createFromArgsRef(hello);

        s.push(hello);

        assertEquals(sRef, s);
    }

    @Test
    public final void testPopToEmpty() {

        String hello = "Hello";

        Stack<String> s = this.createFromArgsRef(hello);
        Stack<String> sRef = this.createFromArgsTest();

        String pulled = s.pop();

        assertEquals(sRef, s);
        assertEquals(hello, pulled);

    }

    @Test
    public final void testPushFromNonEmpty() {

        String world = "world";
        String hello = "Hello";

        Stack<String> s = this.createFromArgsTest(world);
        Stack<String> sRef = this.createFromArgsRef(hello, world);

        s.push(hello);

        assertEquals(sRef, s);
    }

    @Test
    public final void testPopToNonEmpty() {

        String world = "world";
        String hello = "Hello";

        Stack<String> s = this.createFromArgsTest(hello, world);
        Stack<String> sRef = this.createFromArgsRef(world);

        String test = s.pop();

        assertEquals(hello, test);
        assertEquals(sRef, s);
    }

    @Test
    public final void testLengthEmpty() {
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sRef = this.createFromArgsRef();
        int l = s.length();
        assertEquals(0, l);
        assertEquals(sRef, s);
    }

    @Test
    public final void testLengthNonEmpty() {

        String hello = "Hello";

        Stack<String> s = this.createFromArgsTest(hello);
        Stack<String> sRef = this.createFromArgsRef(hello);

        int l = s.length();
        int lRef = sRef.length();

        assertEquals(lRef, l);

    }
}
