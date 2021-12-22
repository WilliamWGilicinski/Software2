import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Colton Davie
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testConstructorIntZero() {
        /*
         * Set up variables
         */
        int testParam = 0;
        int refParam = 0;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testConstructorIntTen() {
        /*
         * Set up variables
         */
        int testParam = 10;
        int refParam = 10;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testConstructorIntMax() {
        /*
         * Set up variables
         */
        int testParam = Integer.MAX_VALUE;
        int refParam = Integer.MAX_VALUE;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testConstructorStringZero() {
        /*
         * Set up variables
         */
        String testParam = "0";
        String refParam = "0";
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testConstructorStringHundred() {
        /*
         * Set up variables
         */
        String testParam = "100";
        String refParam = "100";
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testConstructorStringAboveLongMax() {
        /*
         * Set up variables
         */
        String testParam = "" + Long.MAX_VALUE + 1;
        String refParam = "" + Long.MAX_VALUE + 1;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testConstructorNNZero() {
        /*
         * Set up variables
         */
        NaturalNumber testParam = new NaturalNumber2(0);
        NaturalNumber refParam = new NaturalNumber2(0);
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testConstructorNNTwenty() {
        /*
         * Set up variables
         */
        NaturalNumber testParam = new NaturalNumber2(20);
        NaturalNumber refParam = new NaturalNumber2(20);
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testConstructorNNAboveIntMax() {
        /*
         * Set up variables
         */
        NaturalNumber testParam = new NaturalNumber2(
                "" + (long) Integer.MAX_VALUE + 1);
        NaturalNumber refParam = new NaturalNumber2(
                "" + (long) Integer.MAX_VALUE + 1);
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    /*
     * test cases for kernel methods.
     */

    @Test
    public final void testZeroMultiplyBy10AddZero() {
        /*
         * Set up variables
         */
        int testParam = 0;
        int refParam = 0;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        nExpected.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testZeroMultiplyBy10AddNonZero() {
        /*
         * Set up variables
         */
        int testParam = 0;
        int refParam = 0;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        n.multiplyBy10(6);
        nExpected.multiplyBy10(6);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testNonZeroMultiplyBy10AddZero() {
        /*
         * Set up variables
         */
        int testParam = 6;
        int refParam = 6;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        nExpected.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testNonZeroMultiplyBy10AddNonZero() {
        /*
         * Set up variables
         */
        int testParam = 6;
        int refParam = 6;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        n.multiplyBy10(5);
        nExpected.multiplyBy10(5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testAboveLongMaxMultiplyBy10NonZero() {
        /*
         * Set up variables
         */
        String testParam = "" + Long.MAX_VALUE;
        String refParam = "" + Long.MAX_VALUE;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        n.multiplyBy10(9);
        nExpected.multiplyBy10(9);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testAboveLongMaxMultiplyBy10Zero() {
        /*
         * Set up variables
         */
        String testParam = "" + Long.MAX_VALUE;
        String refParam = "" + Long.MAX_VALUE;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        nExpected.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
    }

    @Test
    public final void testZeroDivideBy10() {
        /*
         * Set up variables
         */
        int testParam = 0;
        int refParam = 0;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        int r = n.divideBy10();
        int rE = nExpected.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(rE, r);
    }

    @Test
    public final void testOneDigitDivideBy10() {
        /*
         * Set up variables
         */
        int testParam = 6;
        int refParam = 6;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        int r = n.divideBy10();
        int rE = nExpected.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(rE, r);
    }

    @Test
    public final void test30DivideBy10() {
        /*
         * Set up variables
         */
        int testParam = 30;
        int refParam = 30;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        int r = n.divideBy10();
        int rE = nExpected.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(rE, r);
    }

    @Test
    public final void test37DivideBy10() {
        /*
         * Set up variables
         */
        int testParam = 37;
        int refParam = 37;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        int r = n.divideBy10();
        int rE = nExpected.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(rE, r);
    }

    @Test
    public final void testAboveLongMaxDivideBy10() {
        /*
         * Set up variables
         */
        String testParam = "" + Long.MAX_VALUE + 1;
        String refParam = "" + Long.MAX_VALUE + 1;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        int r = n.divideBy10();
        int rE = nExpected.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(rE, r);
    }

    @Test
    public final void test0isZero() {
        /*
         * Set up variables
         */
        int testParam = 0;
        int refParam = 0;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        boolean b = n.isZero();
        boolean bE = nExpected.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(bE, b);
    }

    @Test
    public final void test6isZero() {
        /*
         * Set up variables
         */
        int testParam = 6;
        int refParam = 6;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this
                .constructorRef(refParam);/*
                                           * Call method under test
                                           */
        boolean b = n.isZero();
        boolean bE = nExpected.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(bE, b);
    }

    @Test
    public final void test100isZero() {
        /*
         * Set up variables
         */
        int testParam = 100;
        int refParam = 100;
        NaturalNumber n = this.constructorTest(testParam);
        NaturalNumber nExpected = this.constructorRef(refParam);
        /*
         * Call method under test
         */
        boolean b = n.isZero();
        boolean bE = nExpected.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(bE, b);
    }

    @Test
    public final void testisZeroAboveIntMax() {
        /*
         * Set up variables
         */
        int testParam = Integer.MAX_VALUE;
        int refParam = Integer.MAX_VALUE;
        NaturalNumber n = this.constructorTest("" + (long) testParam * 10);
        NaturalNumber nExpected = this
                .constructorRef("" + (long) refParam * 10);
        /*
         * Call method under test
         */
        boolean b = n.isZero();
        boolean bE = nExpected.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(refParam, testParam);
        assertEquals(nExpected, n);
        assertEquals(bE, b);
    }
}
