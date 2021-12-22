import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Homework4Test {

    @Test
    public void test1() {
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE - 1;
        int answer = Homework4.average(j, k);
        int expected = Integer.MAX_VALUE - 1;
        assertEquals(answer, expected);
    }

    @Test
    public void test2() {
        int j = Integer.MIN_VALUE;
        int k = Integer.MIN_VALUE + 1;
        int answer = Homework4.average(j, k);
        int expected = Integer.MIN_VALUE + 1;
        assertEquals(answer, expected);
    }

    @Test
    public void test3() {
        int j = Integer.MIN_VALUE;
        int k = Integer.MIN_VALUE;
        int answer = Homework4.average(j, k);
        int expected = Integer.MIN_VALUE;
        assertEquals(answer, expected);
    }

    @Test
    public void test4() {
        int j = Integer.MAX_VALUE;
        int k = Integer.MAX_VALUE;
        int answer = Homework4.average(j, k);
        int expected = Integer.MAX_VALUE;
        assertEquals(answer, expected);
    }

    @Test
    public void test5() {
        int j = 5;
        int k = 8;
        int answer = Homework4.average(j, k);
        int expected = 6;
        assertEquals(answer, expected);
    }

    @Test
    public void test6() {
        int j = -5;
        int k = -8;
        int answer = Homework4.average(j, k);
        int expected = -6;
        assertEquals(answer, expected);
    }

    @Test
    public void test7() {
        int j = 11;
        int k = -4;
        int answer = Homework4.average(j, k);
        int expected = 3;
        assertEquals(answer, expected);
    }

    @Test
    public void test8() {
        int j = -3;
        int k = 2;
        int answer = Homework4.average(j, k);
        int expected = 0;
        assertEquals(answer, expected);
    }

    @Test
    public void test9() {
        int j = 3;
        int k = 5;
        int answer = Homework4.average(j, k);
        int expected = 4;
        assertEquals(answer, expected);
    }

    @Test
    public void test10() {
        int j = -3;
        int k = -5;
        int answer = Homework4.average(j, k);
        int expected = -4;
        assertEquals(expected, answer);
    }

}
