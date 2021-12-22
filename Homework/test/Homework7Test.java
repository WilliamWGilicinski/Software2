import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class Homework7Test {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    @Test
    public void testAdd() {
        Sequence<String> seq = this.createFromArgsTest("1", "2");
        Sequence<String> expected = this.createFromArgsRef("1", "2", "3");
        seq.add(seq.length() - 1, "3");
        assertEquals(seq, expected);
    }

    @Test
    public void testAddBeg() {
        Sequence<String> seq = this.createFromArgsTest("1", "2");
        Sequence<String> expected = this.createFromArgsRef("0", "1", "2");
        seq.add(0, "0");
        assertEquals(seq, expected);
    }

    @Test
    public void testRemove() {
        Sequence<String> seq = this.createFromArgsTest("1", "2");
        Sequence<String> expected = this.createFromArgsRef("1");
        seq.remove(seq.length() - 1);
        assertEquals(seq, expected);
    }

    @Test
    public void length() {
        Sequence<String> seq = this.createFromArgsTest("1", "2");
        int ref = 2;
        int test = seq.length();
        assertEquals(ref, test);
    }

    @Test
    public void length0() {
        Sequence<String> seq = this.createFromArgsTest();
        int ref = 0;
        int test = seq.length();
        assertEquals(ref, test);
    }

}