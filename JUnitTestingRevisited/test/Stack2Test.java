import components.stack.Stack;
import components.stack.Stack2;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack2Test extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        Stack<String> test = new Stack2<>();
        return test;
    }

    @Override
    protected final Stack<String> constructorRef() {

        Stack<String> ref = new Stack3<>();
        return ref;
    }

}