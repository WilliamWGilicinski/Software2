import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code ExpressionEvaluator}'s {@code valueOfExpr}
 * static method.
 *
 * @author Put your name here
 *
 */
public final class ExpressionEvaluatorTest {

    @Test
    public void testExample() {
        StringBuilder exp = new StringBuilder(
                "281/7/2-1-5*(15-(14-1))+((1))+20=30!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(30, value);
        assertEquals("=30!", exp.toString());
    }

    @Test
    public void testAdd() {
        StringBuilder exp = new StringBuilder("1+1=2!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(2, value);
        assertEquals("=2!", exp.toString());
    }

    @Test
    public void testSubtractParen() {
        StringBuilder exp = new StringBuilder("(((((((1-1)))))))=0!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(0, value);
        assertEquals("=0!", exp.toString());
    }

    @Test
    public void testSimpleExpr() {
        StringBuilder exp = new StringBuilder("(9*2)/3+6-(2/1)=10!");
        int value = ExpressionEvaluator.valueOfExpr(exp);
        assertEquals(10, value);
        assertEquals("=10!", exp.toString());
    }

}
