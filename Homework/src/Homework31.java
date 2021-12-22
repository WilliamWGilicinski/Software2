//import components.map.Map;
//import components.map.Map.Pair;
//import components.program.Program;
//import components.program.Program.Instruction;
//import components.queue.Queue;
//import components.queue.Queue1L;
//import components.sequence.Sequence;
//import components.sequence.Sequence1L;
//import components.simplereader.SimpleReader;
//import components.simplereader.SimpleReader1L;
//import components.simplewriter.SimpleWriter;
//import components.simplewriter.SimpleWriter1L;
//import components.statement.Statement;
//import components.statement.StatementKernel.Condition;
//import components.utilities.Reporter;
//
///**
// * This program calculates the value of a Boolean expression consisting of
// * Boolean operators NOT, AND, and OR, operands T and F, and parentheses.
// *
// * @author Put your name here
// *
// */
//public final class Homework31 {
//
//    /**
//     * Private no-argument constructor so this utility class cannot be
//     * instantiated.
//     */
//    private Homework31() {
//    }
//
//    /**
//     * Converts {@code Condition} into corresponding conditional jump
//     * instruction byte code.
//     *
//     * @param c
//     *            the {@code Condition} to be converted
//     * @return the conditional jump instruction byte code corresponding to
//     *         {@code c}
//     * @ensures <pre>
//     * conditionalJump =
//     *  [conditional jump instruction byte code corresponding to c]
//     * </pre>
//     */
//    private static Instruction conditionalJump(Condition c) {...}
//
//    /**
//     * Generates the sequence of virtual machine instructions ("byte codes")
//     * corresponding to {@code s} and appends it at the end of {@code cp}.
//     *
//     * @param s
//     *            the {@code Statement} for which to generate code
//     * @param context
//     *            the {@code Context} in which to find user defined instructions
//     * @param cp
//     *            the {@code Sequence} containing the generated code
//     * @updates cp
//     * @ensures <pre>
//     * if [all instructions called in s are either primitive or
//     *     defined in context]  and
//     *    [context does not include any calling cycles, i.e., recursion] then
//     *  cp = #cp * [sequence of virtual machine "byte codes" corresponding to s]
//     * else
//     *  [reports an appropriate error message to the console and terminates client]
//     * </pre>
//     */
//    private static void generateCodeForStatement(Statement s,
//            Map<String, Statement> context, Sequence<Integer> cp) {
//
//        final int dummy = 0;
//
//        switch (s.kind()) {
//            case BLOCK: {
//
//                for (int i = 0; i < s.lengthOfBlock(); i++) {
//                    Statement temp = s.removeFromBlock(i);
//                    generateCodeForStatement(s, context, cp);
//                    s.addToBlock(i, temp);
//                }
//
//                break;
//            }
//            case IF: {
//                Statement b = s.newInstance();
//                Condition c = s.disassembleIf(b);
//                cp.add(cp.length(), conditionalJump(c).byteCode());
//                int jump = cp.length();
//                cp.add(cp.length(), dummy);
//                generateCodeForStatement(b, context, cp);
//                cp.replaceEntry(jump, cp.length());
//                s.assembleIf(c, b);
//                break;
//            }
//            case IF_ELSE: {
//
//                Statement b1 = s.newInstance();
//                Statement b2 = s.newInstance();
//                Condition c = s.disassembleIfElse(b1, b2);
//                cp.add(cp.length(), conditionalJump(c).byteCode());
//                int jump = cp.length();
//                cp.add(cp.length(), dummy);
//                generateCodeForStatement(b1, context, cp);
//                cp.replaceEntry(jump, cp.length());
//                cp.add(cp.length(), dummy);
//                jump = cp.length();
//                generateCodeForStatement(b2, context, cp);
//                cp.replaceEntry(jump, cp.length());
//                s.assembleIfElse(c, b1, b2);
//
//                break;
//            }
//            case WHILE: {
//
//                Statement b = s.newInstance();
//                Condition c = s.disassembleWhile(b);
//                cp.add(cp.length(), conditionalJump(c).byteCode());
//                int jump = cp.length();
//                cp.add(cp.length(), dummy);
//                generateCodeForStatement(b, context, cp);
//                cp.add(jump, jump);
//                cp.replaceEntry(jump + 1, cp.length());
//                s.assembleWhile(c, b);
//
//                break;
//            }
//            case CALL: {
//
//                boolean valid = false;
//                String c = s.disassembleCall();
//                while (context.size() > 0 && !valid) {
//                    Pair<String, Statement> temp = context.removeAny();
//                    if (temp.key().equals(c)) {
//                        valid = true;
//                        generateCodeForStatement(temp.value(), context, cp);
//                    }
//                    context.add(temp.key(), temp.value());
//                }
//
//                if (!valid) {
//                    try {
//                        int byteCode = Instruction.valueOf(c).byteCode();
//                        cp.add(cp.length(), byteCode);
//                        valid = true;
//                    } catch (IllegalArgumentException e) {
//                        Reporter.assertElseFatalError(!valid,
//                                "Must be primitive or an instruction");
//                    } catch (NullPointerException e) {
//                        Reporter.assertElseFatalError(!valid,
//                                "Must be primitive or an instruction");
//                    }
//                }
//
//                break;
//            }
//            default: {
//                // nothing to do here: this will never happen...
//                break;
//            }
//        }
//    }
//
//    /**
//     * Generates and returns the sequence of virtual machine instructions ("byte
//     * codes") corresponding to {@code this}.
//     *
//     * @return the compiled program
//     * @ensures <pre>
//     * if [all instructions called in this are either primitive or
//     *     defined in this.context]  and
//     *    [this does not include any calling cycles, i.e., recursion] then
//     *  generatedCode =
//     *   [the sequence of virtual machine "byte codes" corresponding to this]
//     * else
//     *  [reports an appropriate error message to the console and terminates client]
//     * </pre>
//     */
//    public Sequence<Integer> generatedCode() {
//        Sequence<Integer> cp = new Sequence1L<>();
//
//        Map<String, Statement> ctxt = this.newContext();
//        this.swapContext(ctxt);
//        Statement body = this.newBody();
//        this.swapBody(body);
//        generateCodeForStatement(body, context, cp);
//
//        return cp;
//    }
//
//    /**
//     * Main method.
//     *
//     * @param args
//     *            the command line arguments
//     */
//    public static void main(String[] args) {
//        SimpleReader in = new SimpleReader1L();
//        SimpleWriter out = new SimpleWriter1L();
//        out.print("Enter a valid Boolean expression: ");
//        String source = in.nextLine();
//        while (source.length() > 0) {
//            boolean value = valueOfBoolExpr(tokens(source));
//            out.println(source + " = " + value);
//            out.print("Enter a valid Boolean expression: ");
//            source = in.nextLine();
//        }
//        in.close();
//        out.close();
//    }
//
//}