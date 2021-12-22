import components.statement.Statement;
import components.statement.StatementKernel;
import components.statement.StatementKernel.Condition;

public class Homework23 {

    /**
     * Refactors the given {@code Statement} so that every IF_ELSE statement
     * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
     * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
     * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
     * switched. Every other statement is left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @updates s
     * @ensures <pre>
     * s = [#s refactored so that IF_ELSE statements with "not"
     *   conditions are simplified so the "not" is removed]
     * </pre>
     */
    public static void simplifyIfElse(Statement s) {
        switch (s.kind()) {
            case BLOCK: {

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement child = s.removeFromBlock(i);
                    simplifyIfElse(child);
                    s.addToBlock(i, child);
                }

                break;
            }
            case IF: {

                Statement body = s.newInstance();
                Condition cond = s.disassembleIf(body);
                simplifyIfElse(body);
                s.assembleIf(cond, body);

                break;
            }
            case IF_ELSE: {

                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                Condition cond = s.disassembleIfElse(s1, s2);
                Condition nonEmpt = StatementKernel.Condition.NEXT_IS_NOT_EMPTY;
                Condition nonEnemy = StatementKernel.Condition.NEXT_IS_NOT_ENEMY;
                Condition nonFriend = StatementKernel.Condition.NEXT_IS_NOT_FRIEND;
                Condition nonWall = StatementKernel.Condition.NEXT_IS_NOT_WALL;

                if (cond.equals(nonEmpt)) {
                    cond = StatementKernel.Condition.NEXT_IS_EMPTY;
                } else if (cond.equals(nonEnemy)) {
                    cond = StatementKernel.Condition.NEXT_IS_ENEMY;
                } else if (cond.equals(nonFriend)) {
                    cond = StatementKernel.Condition.NEXT_IS_FRIEND;
                } else if (cond.equals(nonWall)) {
                    cond = StatementKernel.Condition.NEXT_IS_WALL;
                }

                s.assembleIfElse(cond, s2, s1);

                break;
            }
            case WHILE: {

                Statement body = s.newInstance();
                Condition cond = s.disassembleWhile(body);
                simplifyIfElse(body);
                s.assembleWhile(cond, body);

                break;
            }
            case CALL: {
                // nothing to do here...can you explain why?
                break;
            }
            default: {
                // this will never happen...can you explain why?
                break;
            }
        }
    }
}