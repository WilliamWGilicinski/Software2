import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";

        //dequeue "IF"
        String tokenIf = tokens.dequeue();

        //give error if next token is not a condition
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "Error: " + tokens.front() + " is not a valid Condition.");

        //parse the condition
        Condition condIf = parseCondition(tokens.dequeue());

        //give error if next token is not "THEN"
        Reporter.assertElseFatalError(tokens.front().equals("THEN"),
                "Error: Expected \"THEN\", Found: \"" + tokens.front() + "\"");

        //dequeue "THEN"
        tokens.dequeue();

        //parse block under if
        Statement ifS = s.newInstance();
        ifS.parseBlock(tokens);

        //give error if next token is not ELSE or END
        Reporter.assertElseFatalError(
                tokens.front().equals("ELSE") || tokens.front().equals("END"),
                "Error: Expected \"ELSE\" or \"END\", Found: \""
                        + tokens.front() + "\"");

        if (tokens.front().equals("ELSE")) {
            //dequeue "ELSE"
            tokens.dequeue();

            //parse block under else
            Statement elseS = s.newInstance();
            elseS.parseBlock(tokens);

            //assemble if
            s.assembleIfElse(condIf, ifS, elseS);

            //give error if next token is not "END"
            Reporter.assertElseFatalError(tokens.front().equals("END"),
                    "Error: Expected \"END\", Found: \"" + tokens.front()
                            + "\"");

            //dequeue "END"
            tokens.dequeue();
        } else {
            //assemble if
            s.assembleIf(condIf, ifS);

            //give error if next token is not "END"
            Reporter.assertElseFatalError(tokens.front().equals("END"),
                    "Error: Expected \"END\", Found: \"" + tokens.front()
                            + "\"");
            //dequeue "END"
            tokens.dequeue();
        }

        //give error if next token is not "IF"
        Reporter.assertElseFatalError(tokens.front().equals(tokenIf),
                "Error: Expected \"IF\" Found: \"" + tokens.front() + "\"");

        //dequeue "IF"
        tokens.dequeue();

    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        //dequeue "WHILE"
        String tokenWhile = tokens.dequeue();

        //give error if next token is not a condition
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "Error: " + tokens.front() + " is not a valid Condition.");

        //parse the condition
        Condition condWhile = parseCondition(tokens.dequeue());

        //give error if next token is not "DO"
        Reporter.assertElseFatalError(tokens.front().equals("DO"),
                "Error: Expected \"DO\", Found: \"" + tokens.front() + "\"");

        //dequeue "DO"
        tokens.dequeue();

        //parse block under while
        Statement whileS = s.newInstance();
        whileS.parseBlock(tokens);

        //assemble while
        s.assembleWhile(condWhile, whileS);

        //give error if next token is not "END"
        Reporter.assertElseFatalError(tokens.front().equals("END"),
                "Error: Expected \"END\", Found: \"" + tokens.front() + "\"");

        //dequeue "END"
        tokens.dequeue();

        //give error if next token is not "WHILE"
        Reporter.assertElseFatalError(tokens.front().equals(tokenWhile),
                "Error: Expected \"WHILE\", Found:\"" + tokens.front() + "\"");

        //dequeue "WHILE"
        tokens.dequeue();

    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";

        //dequeue call and assemble the call
        String call = tokens.dequeue();
        s.assembleCall(call);

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        //give error if next token is not "IF", "WHILE", or an identifier
        Reporter.assertElseFatalError(
                Tokenizer.isIdentifier(tokens.front())
                        || tokens.front().equals("IF")
                        || tokens.front().equals("WHILE"),
                "Expected IF, WHILE, or IDENTIFIER, Found: " + tokens.front());

        if (tokens.front().equals("IF")) {
            //parse the if
            parseIf(tokens, this);
        } else if (tokens.front().equals("WHILE")) {
            //parse the while
            parseWhile(tokens, this);
        } else {
            //parse other
            parseCall(tokens, this);
        }

    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        Statement newS = this.newInstance();

        //give error if next token is not "IF", "WHILE", or an identifier
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(tokens.front())
                || tokens.front().equals("IF") || tokens.front().equals("WHILE")
                || tokens.front().equals("END"),
                "Expected IF, WHILE, or IDENTIFIER, Found: " + tokens.front());

        //loop through if, while, and identifier statements
        for (int i = 0; tokens.front().equals("IF")
                || tokens.front().equals("WHILE")
                || Tokenizer.isIdentifier(tokens.front()); i++) {

            //parse the statement
            newS.parse(tokens);

            //add to block
            this.addToBlock(i, newS);

        }

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
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parseBlock(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
