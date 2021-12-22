import components.map.Map;
import components.program.Program;
import components.statement.Statement;
import components.statement.StatementKernel.Condition;

public class Homework25 {

    /**
     * Refactors the given {@code Statement} by renaming every occurrence of
     * instruction {@code oldName} to {@code newName}. Every other statement is
     * left unmodified.
     *
     * @param s
     *            the {@code Statement}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates s
     * @requires [newName is a valid IDENTIFIER]
     * @ensures <pre>
     * s = [#s refactored so that every occurrence of instruction oldName
     *   is replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Statement s, String oldName,
            String newName) {
        switch (s.kind()) {
            case BLOCK: {

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement child = s.removeFromBlock(i);
                    renameInstruction(child, oldName, newName);
                    s.addToBlock(i, child);
                }
                break;

            }
            case IF: {

                Statement body = s.newInstance();
                Condition cond = s.disassembleIf(body);
                renameInstruction(body, oldName, newName);
                s.assembleIf(cond, body);
                break;

            }
            case IF_ELSE: {

                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                Condition cond = s.disassembleIfElse(s1, s2);
                renameInstruction(s1, oldName, newName);
                renameInstruction(s2, oldName, newName);
                s.assembleIfElse(cond, s1, s2);
                break;
            }
            case WHILE: {

                Statement body = s.newInstance();
                Condition cond = s.disassembleWhile(body);
                renameInstruction(body, oldName, newName);
                s.assembleWhile(cond, body);

            }
            case CALL: {

                String instruction = s.disassembleCall();
                if (instruction.equals(oldName)) {
                    instruction = newName;
                }
                s.assembleCall(instruction);
            }
            default: {
                break;
            }
        }
    }

    /**
     * Refactors the given {@code Program} by renaming instruction
     * {@code oldName}, and every call to it, to {@code newName}. Everything
     * else is left unmodified.
     *
     * @param p
     *            the {@code Program}
     * @param oldName
     *            the name of the instruction to be renamed
     * @param newName
     *            the new name of the renamed instruction
     * @updates p
     * @requires <pre>
     * oldName is in DOMAIN(p.context)  and
     * [newName is a valid IDENTIFIER]  and
     * newName is not in DOMAIN(p.context)
     * </pre>
     * @ensures <pre>
     * p = [#p refactored so that instruction oldName and every call
     *   to it are replaced by newName]
     * </pre>
     */
    public static void renameInstruction(Program p, String oldName,
            String newName) {
        Map<String, Statement> ctxt = p.newContext();
        Map<String, Statement> c = p.newContext();
        p.swapContext(ctxt);
        while (ctxt.size() > 0) {
            Map.Pair<String, Statement> instr = ctxt.removeAny();
            renameInstruction(instr.value(), oldName, newName);
            if (instr.key().equals(oldName)) {
                c.add(newName, instr.value());
            } else {
                c.add(instr.key(), instr.value());
            }
        }

        p.swapContext(c);
        Statement pBody = p.newBody();
        p.swapBody(pBody);
        renameInstruction(pBody, oldName, newName);
        p.swapBody(pBody);
    }

}