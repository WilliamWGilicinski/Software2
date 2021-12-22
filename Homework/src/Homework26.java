import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;

public class Homework26 {

    /**
     * Definition of whitespace separators.
     */
    private static final String SEPARATORS = " \t\n\r";

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code SEPARATORS}) or "separator string" (maximal length string of
     * characters in {@code SEPARATORS}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection entries(SEPARATORS) = {}
     * then
     *   entries(nextWordOrSeparator) intersection entries(SEPARATORS) = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection entries(SEPARATORS) /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of entries(SEPARATORS)  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of entries(SEPARATORS))
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position) {

        Set<Character> sepSet = new Set1L<>();
        sepSet.add(' ');
        sepSet.add('\t');
        sepSet.add('\n');
        sepSet.add('\r');

        StringBuilder output = new StringBuilder();
        if (sepSet.contains(text.charAt(position))) {
            for (int i = position; i < text.length()
                    && sepSet.contains(text.charAt(i)); i++) {
                output.append(text.substring(i, i + 1));
            }
        } else {
            for (int i = position; i < text.length()
                    && !sepSet.contains(text.charAt(i)); i++) {
                output.append(text.substring(i, i + 1));
            }
        }
        return output.toString();
    }

    /**
     * Tokenizes the entire input getting rid of all whitespace separators and
     * returning the non-separator tokens in a {@code Queue<String>}.
     *
     * @param in
     *            the input stream
     * @return the queue of tokens
     * @requires in.is_open
     * @ensures <pre>
     * tokens =
     *   [the non-whitespace tokens in #in.content] * <END_OF_INPUT>  and
     * in.content = <>
     * </pre>
     */
    public static Queue<String> tokens(SimpleReader in) {

        Set<Character> sepSet = new Set1L<>();
        sepSet.add(' ');
        sepSet.add('\t');
        sepSet.add('\n');
        sepSet.add('\r');

        int count = 0;
        Queue<String> output = new Queue1L<>();
        while (!in.atEOS()) {
            String text = in.nextLine();
            while (count <= text.length()) {
                String temp = nextWordOrSeparator(text, count);
                if (sepSet.contains(temp.charAt(0))) {
                    output.enqueue(temp);
                }
                count += temp.length();
            }
        }
        return output;

    }

}