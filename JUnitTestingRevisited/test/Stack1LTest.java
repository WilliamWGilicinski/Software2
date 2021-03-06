import components.stack.Stack;
import components.stack.Stack1L;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack1LTest extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        Stack<String> test = new Stack1L<>();
        return test;
    }

    @Override
    protected final Stack<String> constructorRef() {

        Stack<String> ref = new Stack3<>();
        return ref;
    }

}