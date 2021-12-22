public class Homework24 {

//    /**
//     * Refactors the given {@code Statement} so that every IF_ELSE statement
//     * with a negated condition (NEXT_IS_NOT_EMPTY, NEXT_IS_NOT_ENEMY,
//     * NEXT_IS_NOT_FRIEND, NEXT_IS_NOT_WALL) is replaced by an equivalent
//     * IF_ELSE with the opposite condition and the "then" and "else" BLOCKs
//     * switched. Every other statement is left unmodified.
//     *
//     * @param s
//     *            the {@code Statement}
//     * @updates s
//     * @ensures <pre>
//     * s = [#s refactored so that IF_ELSE statements with "not"
//     *   conditions are simplified so the "not" is removed]
//     * </pre>
//     */
//    public void prettyPrint(SimpleWriter out, int offset) {
//        switch (s.kind()) {
//            case BLOCK: {
//
//                for (int i = 0; i < this.lengthOfBlock(); i++) {
//                    Statement child = this.removeFromBlock(i);
//                    child.prettyPrint(out, offset);
//                    this.addToBlock(i, child);
//                }
//
//                break;
//            }
//            case IF: {
//
//                Statement body = this.newInstance();
//                Condition cond = this.disassembleIf(body);
//                out.println(printSpaces(out, offset) + "IF " + this.name()
//                        + " THEN");
//                body.prettyPrint(out, offset + 4);
//                out.println("END IF");
//                s.assembleIf(cond, body);
//
//            }
//            case IF_ELSE: {
//
//                Statement s1 = this.newInstance();
//                Statement s2 = this.newInstance();
//                Condition cond = this.disassembleIfElse(s1, s2);
//                out.println(printSpaces(out, offset) + "IF " + this.name()
//                        + " THEN");
//                s1.prettyPrint(out, offset + 4);
//                out.println(printSpaces(out, offset) + "ELSE");
//                s2.prettyPrint(out, offset + 4);
//                out.println("END IF");
//
//                this.assembleIfElse(cond, s2, s1);
//
//                break;
//            }
//            case WHILE: {
//
//                Statement body = this.newInstance();
//                Condition cond = this.disassembleWhile(body);
//                out.println(printSpaces(out, offset) + "WHILE "
//                        + toStringCondition(cond) + " DO");
//                body.prettyPrint(out, offset + 4);
//
//                s.assembleWhile(cond, body);
//            }
//            case CALL: {
//
//                out.println(printSpaces(out, offset) + this.toString());
//                break;
//            }
//            default: {
//                // this will never happen...can you explain why?
//                break;
//            }
}