import components.map.Map;
import components.map.Map.Pair;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Colton Davie & William Gilicinski
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        //check the keyword INSTRUCTION.
        String instruction = tokens.dequeue();
        Reporter.assertElseFatalError(instruction.equals("INSTRUCTION"),
                "Error: keyword \"INSTRUCTION\" not found.");

        //check the instruction's name. If it's equal to primitive
        //instructions, send error message.
        String instructionName = tokens.dequeue();
        boolean validName = !instructionName.equals("move")
                && !instructionName.equals("turnright")
                && !instructionName.equals("turnleft")
                && !instructionName.equals("infect")
                && !instructionName.equals("skip");
        Reporter.assertElseFatalError(validName,
                "Error: instruction name can't be the same as primitive instructions.");

        //check the keyword IS.
        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Error: keyword \"IS\" not found.");

        //parse the block after the keywords.
        body.parseBlock(tokens);

        //check the keyword END.
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: keyword \"END\" not found.");

        //check the instruction's name. If it's not the same as the instruction
        //name in the front of the instruction, send error message.
        String endInstructionName = tokens.dequeue();
        Reporter.assertElseFatalError(
                endInstructionName.equals(instructionName),
                "Error: keyword \"" + endInstructionName
                        + "\" is not the same as \"" + instructionName + "\".");

        // This line added just to make the program compilable.
        return instructionName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        //create a map to be the context of the program.
        Map<String, Statement> context = this.newContext();

        //check the keyword PROGRAM.
        String programm = tokens.dequeue();
        Reporter.assertElseFatalError(programm.equals("PROGRAM"),
                "Error: keyword \"PROGRAM\" not found.");

        //get the program's name. Check the keyword IS.
        String programName = tokens.dequeue();
        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Error: keyword \"IS\" not found.");

        //check whether the next part is an instruction or the body.
        String currentFirstToken = tokens.front();
        while (currentFirstToken.equals("INSTRUCTION")) {
            //create a body for the current instruction, and parse the
            //instruction. Check if the current instruction was already defined before.
            Statement instructionBody = this.newBody();
            String instructionName = parseInstruction(tokens, instructionBody);
            for (Pair<String, Statement> contexts : context) {
                Reporter.assertElseFatalError(
                        !contexts.key().equals(instructionName),
                        "Error: the instruction \"" + instructionName
                                + "\" is already defined.");
            }
            //add the instruction to the context.
            context.add(instructionName, instructionBody);
            //change the string to next line to avoid error occuring.
            currentFirstToken = tokens.front();
        }

        //create a new statement to be the body of the program.
        Statement programBody = this.newBody();

        //check the keyword BEGIN.
        String begin = tokens.dequeue();
        Reporter.assertElseFatalError(begin.equals("BEGIN"),
                "Error: keyword \"BEGIN\" not found.");

        //parse the block after the keywords.
        programBody.parseBlock(tokens);

        //check the keyword END.
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: keyword \"END\" not found.");

        //check the program's name. If it's not the same as the program name
        //in the front of the BL program, send error message.
        String endProgramName = tokens.dequeue();
        Reporter.assertElseFatalError(endProgramName.equals(programName),
                "Error: keyword \"" + endProgramName
                        + "\" is not the same as \"" + programName + "\".");

        //check whether the last token is ### END OF INPUT ### or not.
        String endOfInput = tokens.dequeue();
        Reporter.assertElseFatalError(endOfInput.equals("### END OF INPUT ###"),
                "Error: missing ending line \"### END OF INPUT ###\"");

        this.setName(programName);
        this.swapBody(programBody);
        this.swapContext(context);

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
