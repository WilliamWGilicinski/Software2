import components.statement.Statement;
import components.statement.StatementKernel.Condition;
import components.statement.StatementKernel.Kind;

public class Homework22 {

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                if (s.lengthOfBlock() > 0) {
                    Statement temp = s.removeFromBlock(0);
                    Kind k = temp.kind();
                    if (k.equals(k.CALL)) {
                        count++;
                    } else {
                        count += countOfPrimitiveCalls(s);
                    }
                    s.addToBlock(s.lengthOfBlock(), temp);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement body = s.newInstance();
                Condition cond = s.disassembleIf(body);
                count += countOfPrimitiveCalls(s);
                s.assembleIf(cond, body);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                Condition cond = s.disassembleIfElse(s1, s2);
                count += countOfPrimitiveCalls(s1);
                count += countOfPrimitiveCalls(s2);
                s.assembleIfElse(cond, s1, s2);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement s1 = s.newInstance();
                Condition cond = s.disassembleWhile(s1);
                count += countOfPrimitiveCalls(s);
                s.assembleWhile(cond, s1);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                String instruction = s.disassembleCall();
                if (instruction.equals("move") || instruction.equals("skip")
                        || instruction.equals("infect")
                        || instruction.equals("turnleft")
                        || instruction.equals("turnright")
                        || instruction.equals("wait")) {
                    count++;
                }

                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }

}