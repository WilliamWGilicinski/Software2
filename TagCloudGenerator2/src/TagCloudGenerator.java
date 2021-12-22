
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci, William Gilicinski, Colton Davie
 */
public final class TagCloudGenerator {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private TagCloudGenerator() {
        // no code needed here
    }

    /**
     * Set of Separators.
     */
    private static Set<Character> SEPARATORS;

    /**
     * The maximum font size.
     */
    private static final int MAX_FONT_SIZE = 48;

    /**
     * The minumum font size.
     */
    private static final int MIN_FONT_SIZE = 11;

    /**
     * The minimum count of a word.
     */
    private static int MIN_VALUE = 0;

    /**
     * The maximum count of a word.
     */
    private static int MAX_VALUE = 0;

    /**
     * Compare map key {@code String}s in alphabatical order.
     */
    private static class alphabeticalSort
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> first,
                Map.Entry<String, Integer> second) {
            return first.getKey().compareToIgnoreCase(second.getKey());

        }
    }

    /**
     * Compare map value {@code Integer}s in numerical order.
     */
    private static class numericalSort
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> first,
                Map.Entry<String, Integer> second) {
            return second.getValue().compareTo(first.getValue());

        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) {
        //Open console reader/writer.
        Scanner in = new Scanner(System.in);

        //Acquire input file name and create reader for the file.
        System.out.print("Name of input file: ");
        String inputName = in.nextLine();
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(inputName));
        } catch (FileNotFoundException e) {
            System.err.println("Error Opening File");
        }

        //Acquire output file name and create writer for the file.
        System.out.print("Name of output file: ");
        String outputName = in.nextLine();
        PrintWriter html = null;
        try {
            html = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputName)));
        } catch (IOException e) {
            System.err.println("Error Creating File");
        }

        //Acquire number of words to be included in the generated tag cloud
        System.out.print("Number of words for tag cloud: ");
        int numWords = in.nextInt();

        //Create Set of separators.
        SEPARATORS = generateSeparators();

        //Initialize Map.
        Map<String, Integer> wordsAndCounts = new HashMap<>();

        //Create Map with the words and their corresponding counts.
        try {
            while (file.ready()) {
                //Initialize the variable to hold the next word/separator.
                String wOrS;

                //Acquire next line in the file and set starting pos to 0.
                String line = file.readLine();
                int pos = 0;

                //Loop through the current line.
                while (pos < line.length()) {

                    //Acquire next word or separator.
                    wOrS = nextWordOrSeparator(line, pos, SEPARATORS);

                    //Increase the pos by the length of the word/separator.
                    pos = pos + wOrS.length();

                    //Used to check if the returned value contains a word following
                    //separators.
                    while (wOrS.length() > 1
                            && SEPARATORS.contains(wOrS.charAt(0))) {
                        wOrS = wOrS.substring(1);
                    }

                    //Used to check if the returned value contains a separator
                    //at the end of a word.
                    while (wOrS.length() > 1 && SEPARATORS
                            .contains(wOrS.charAt(wOrS.length() - 1))) {
                        wOrS = wOrS.substring(0, wOrS.length() - 1);
                    }

                    //Determine if return value was a word or a separator.
                    if (!SEPARATORS.contains(wOrS.charAt(0))) {
                        //Turns the current word to its lower case.
                        wOrS = wOrS.toLowerCase();
                        if (wordsAndCounts.containsKey(wOrS)) {
                            //Increase the count if the word id already in the Map.
                            wordsAndCounts.put(wOrS,
                                    wordsAndCounts.get(wOrS) + 1);
                        } else {
                            //Add the word and a count of 1 if not in Map already.
                            wordsAndCounts.put(wOrS, 1);
                        }
                    }
                }
            }
        } catch (IOException e1) {
            System.err.println("Error Reading File");
        }

        //Create comparators
        Comparator<Map.Entry<String, Integer>> alphabeticalSort = new alphabeticalSort();
        Comparator<Map.Entry<String, Integer>> numericalSort = new numericalSort();

        //Sort the words (change to java component)
        PriorityQueue<Map.Entry<String, Integer>> sorted = completeSort(
                wordsAndCounts, numWords, alphabeticalSort, numericalSort);

        //Create the html file of the tag cloud
        generateHTML(inputName, html, sorted);

        /*
         * Close input and output streams
         */
        try {
            in.close();
            file.close();
        } catch (IOException e) {
            System.err.println("Error Closing File");
        }
    }

    /**
     * Creates the set of separators.
     *
     * @return Set of characters classified as separators.
     */
    public static Set<Character> generateSeparators() {
        //Initialize Set
        Set<Character> seps = new HashSet<>();

        //Add all necessary separators.
        seps.add(' ');
        seps.add('!');
        seps.add(',');
        seps.add('.');
        seps.add('\t');
        seps.add('-');
        seps.add('?');
        seps.add('\"');
        seps.add('\n');
        seps.add('(');
        seps.add(')');
        seps.add('\'');
        seps.add('\r');
        seps.add('[');
        seps.add(']');
        seps.add(':');
        seps.add(';');
        seps.add('/');
        seps.add('@');
        seps.add('#');
        seps.add('$');
        seps.add('%');
        seps.add('&');
        seps.add('*');
        seps.add('`');

        //Return the Set.
        return seps;
    }

    /**
     * Sort the words in decreasing order and then take the most frequent words
     * and sort them aplhabetically.
     *
     * @param map
     *            the map with all the words and their counts
     * @param numOfWords
     *            the number of words to be shown in the cloud
     * @param alphabeticalOrder
     *            the comparator used to sort the words by alphabetical order
     * @param numericalOrder
     *            the comparator used to sort the words by numerical order
     *
     * @requires numOfWords <= map.size()
     * @return Priority Queue sorting by most frequent and alphabetical
     */
    private static PriorityQueue<Map.Entry<String, Integer>> completeSort(
            Map<String, Integer> map, int numOfWords,
            Comparator<Map.Entry<String, Integer>> alphabeticalOrder,
            Comparator<Map.Entry<String, Integer>> numericalOrder) {

        assert numOfWords <= map.size() : "Violation of: the number of words"
                + " to be showed must be smaller than the words count.";

        //get size of map to create size of priority queue
        int size = map.size();

        //Create a PriorityQueue to sort by numeric order
        PriorityQueue<Entry<String, Integer>> numerical;

        //Create a PriorityQueue to sort by alphabetic order
        PriorityQueue<Entry<String, Integer>> alphabetical;

        if (size != 0) {

            //initialize numerical PriorityQueue
            numerical = new PriorityQueue<>(size, numericalOrder);

            //Remove all the elements from the map and put them into the PriorityQueue
            for (Map.Entry<String, Integer> x : map.entrySet()) {
                numerical.add(x);
            }

            //initialize alphabetical PriorityQueue
            alphabetical = new PriorityQueue<>(size, alphabeticalOrder);

            /*
             * Remove a number of elements from numeric PriorityQueue and add
             * them to alphabetical PriorityQueue
             */
            for (int i = 0; i < numOfWords; i++) {
                Map.Entry<String, Integer> p = numerical.poll();

                //Find the max word count and the min word count
                if (i == 0) {
                    MAX_VALUE = p.getValue();
                }
                if (i == numOfWords - 1) {
                    MIN_VALUE = p.getValue();
                }
                alphabetical.add(p);
            }
        } else {
            //initialize PriorityQueue with size 1
            alphabetical = new PriorityQueue<>(1, alphabeticalOrder);
        }

        return alphabetical; //return the PriorityQueue with the alphabetized words
    }

    /**
     * Creates the HTML file containing all words and their counts.
     *
     * @param name
     *            The name of the input file
     * @param out
     *            output file
     * @param words
     *            queue with terms in alphabetical order
     * @requires out.isOpen()
     * @ensures <pre>
     *      HTML page contains all words and the number of times it appears in
     *      the input file.
     *  </pre>
     *
     */
    public static void generateHTML(String name, PrintWriter out,
            PriorityQueue<Map.Entry<String, Integer>> words) {

        //Write header.
        out.println("<html>");
        out.println("<head>");
        out.println("   <title>Tag Cloud</title>");
        out.println(
                "   <link href=\"http://web.cse.ohio-state.edu/software/2231/"
                        + "web-sw2/assignments/projects/tag-cloud-generator/data/"
                        + "tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("   <h1>Top " + " words in " + name + "</h1>");
        out.println("<hr style=\"border:1px solid black\" />");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");

        //Loop through all words in alphabetical order.
        while (words.size() > 0) {

            Map.Entry<String, Integer> current = words.poll();
            generateWord(current.getKey(), current.getValue(), out);
        }

        //Write footer.
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    /**
     * Generate the word given word with the correct font size.
     *
     * @param word
     *            the word to be added the the tag cloud
     * @param count
     *            number of occurences in the input file
     * @param out
     *            the html file
     */
    public static void generateWord(String word, int count, PrintWriter out) {
        int fontSize = (MAX_FONT_SIZE - MIN_FONT_SIZE) * (count - MIN_VALUE)
                / (MAX_VALUE - MIN_VALUE) + MIN_FONT_SIZE;

        out.println("<span style=\"cursor:default\" class=\"f" + fontSize
                + "\" title=\"count: " + count + "\">" + word + "</span>");
    }

    /**
     *
     * @param text
     *            The current line
     * @param position
     *            The starting position for the next word/separator
     * @param separators
     *            Set of all characters classified as separators
     * @return The next word or separator
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        //Initialize the variable for the next word/separator.
        String nextWoS = "";

        //Get first character from the position
        char c = text.charAt(position);

        //Check if the character is a separator.
        if (separators.contains(c)) {
            int p = position;

            //Run through the next characters until it is not a separator.
            char end = text.charAt(p);
            while (separators.contains(end) && p < text.length()) {
                end = text.charAt(p);
                p++;
            }

            //If the position is the end of the line, add 1 to the position.
            if (p == text.length()) {
                p++;
            }

            //Assign the string of separators to be returned.
            nextWoS = text.substring(position, p - 1);

        } else {
            int p = position;
            char end = text.charAt(p);

            //Run through the next characters until it is a separator.
            while (!separators.contains(end) && p < text.length()) {
                end = text.charAt(p);
                p++;
            }

            //If the position is the end of the line, add 1 to the position.
            if (p == text.length()) {
                p++;
            }

            //Assign the word to be returned.
            nextWoS = text.substring(position, p - 1);
        }

        //Return the word/separator(s)
        return nextWoS;
    }

}
