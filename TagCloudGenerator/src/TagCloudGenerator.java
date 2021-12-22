import java.io.Serializable;
import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map2;
import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author P. Bucci, William Gilicinski, Colton Davie
 */
public final class TagCloudGenerator {

    /**
     * Asks the user for a file name for the input and output file
     *
     * @ensures The name of the file is read and an output file is created
     * @return String array of the file names
     */
    public static String[] userInput(SimpleWriter write, SimpleReader read) {
        //Gets the names of the input and output files
        write.println("Enter the name of in input file");
        String input = read.nextLine();
        write.println("Enter the name of an output file");
        String output = read.nextLine();
        write.println("Enter the amount of words in the cloud");
        String num = read.nextLine();
        String[] both = new String[] { input, output, num };
        return both;
    }

    /**
     * Creates a queue of all the words in the document and ignores all
     * separator characters (i.e. ' ', '.', '!'...)
     *
     * @param scan
     * @return A Queue of all the words in the file
     */
    public static Queue<String> wordOrSeparator(SimpleReader scan) {
        //Creates data structures and adds all the separators to the list
        Queue<String> words = new Queue2<>();
        Set<Character> separator = new Set2<>();
        StringBuilder build = new StringBuilder();

        //Fills the set with the contents of this array. I set it up this way as
        //calling separator.add adds a lot of lines which are harder to change
        Character[] sepArr = new Character[] { ' ', '\t', '\n', '\r', ',', '-',
                '.', '!', '?', '[', ']', '\'', ';', ':', '/', '(', ')', '"' };
        for (int i = 0; i < sepArr.length; i++) {
            separator.add(sepArr[i]);
        }

        //Scans in the words a line at a time. Logic then determines what
        //constitutes as a word and when the line of words end.
        while (!scan.atEOS()) {
            //String of a line
            String temp = scan.nextLine();
            for (int i = 0; i < temp.length(); i++) {
                //Adds a character if not separator
                if (!separator.contains(temp.charAt(i))) {
                    build.append(temp.charAt(i));
                    //Stops creating word if end of line
                    if (i == temp.length() - 1) {
                        words.enqueue(build.toString());
                        build.delete(0, build.length());
                    }
                    //If stringbuilder has one or more characters add to queue
                } else if (build.length() >= 1) {
                    words.enqueue(build.toString());
                    build.delete(0, build.length());
                }
            }
        }
        return words;
    }

    /**
     * Creates a map of all the words in the file and the amount of times they
     * are written
     *
     * @param words
     * @return
     */
    public static Map<String, Integer> count(Queue<String> words) {
        Map<String, Integer> wordCount = new Map2<>();

        //Goes through each word added to the queue and counts how many exist
        while (words.length() > 0) {
            String temp = words.dequeue();
            if (wordCount.hasKey(temp)) {
                wordCount.replaceValue(temp, wordCount.value(temp) + 1);
            } else {
                wordCount.add(temp, 1);
            }
        }

        return wordCount;
    }

    /**
     * A simple comparator that compares strings in lexicographical order
     *
     * @return if the string is more, less, or equal to another.
     */
    private static class StringComp
            implements Comparator<Map.Pair<String, Integer>>, Serializable {

        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o1.key().compareToIgnoreCase(o2.key());
        }

    }

    /**
     * A simple comparator that compares integer values
     *
     * @return if the int is more, less, or equals to another
     */
    private static class IntegerComp
            implements Comparator<Map.Pair<String, Integer>>, Serializable {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o2.value().compareTo(o1.value());
        }
    }

    /**
     * Writes the HTML by sorting the words with the top N word count and then
     * by lexigraphical order.
     *
     * @param wordCount
     *            The map with all the words and the amount of times used
     * @param w
     *            The writer to the output file
     * @param output
     *            The name of the output file
     */
    public static void writeHTML(Map<String, Integer> wordCount, SimpleWriter w,
            String output, int N) {
        //Amount of words wanted in word cloud
        //Creates the head and table
        w.println("<html>");
        w.println(" <head>");
        w.println("  <title>Top " + N + " words in " + output + "</title>");
        w.println(
                "  <link href=\"http://web.cse.ohio-state.edu/software/2231/web-"
                        + "sw2/assignments/projects/tag-cloud-generator/data/"
                        + "tagcloud.css\" "
                        + "rel=\"stylesheet\" type=\"text/css\">\r\n");
        w.println(
                "  <link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/"
                        + "css\">\r\n");
        w.println(" </head>");
        w.println(" <body>");
        w.println("  <h2>Top " + N + " words in " + output + "</h2>");
        w.println("  <hr />");
        w.println("  <div class=\"cdiv\">");
        w.println("  <p class=\"cbox\">");

        //Creates the comparators and sorting machines
        Comparator<Pair<String, Integer>> strComp = new StringComp();
        Comparator<Pair<String, Integer>> intComp = new IntegerComp();
        SortingMachine<Pair<String, Integer>> strSort = new SortingMachine1L<>(
                strComp);
        SortingMachine<Pair<String, Integer>> intSort = new SortingMachine1L<>(
                intComp);

        //Sorts by frequency of words
        while (wordCount.size() > 0) {
            intSort.add(wordCount.removeAny());
        }
        intSort.changeToExtractionMode();

        int highest = 0;
        int lowest = 0;
        //Sorts by lexicographic order
        for (int i = 0; i < N; i++) {
            Pair<String, Integer> temp = intSort.removeFirst();

            //Finds the highest and lowest bound for wordCount
            if (i == 0) {
                highest = temp.value();
            } else if (i == N - 1) {
                lowest = temp.value();
            }

            strSort.add(temp);
        }
        strSort.changeToExtractionMode();

        //Adds rows with the key and values

        //Finds the font size of the words relative to the largest and
        //smallest word counts of the top N words
        int amountOfFValues = 37;
        int minimumFValue = 11;
        int difference = highest - lowest;
        int interDifference = difference / amountOfFValues;
        if (interDifference == 0) {
            interDifference = 1;
        }

        while (strSort.size() > 0) {

            Pair<String, Integer> temp = strSort.removeFirst();

            int fValue = ((temp.value() - lowest) / interDifference)
                    + minimumFValue;

            w.println("  <span style = \"cursor:defualt\" class = \"f" + fValue
                    + "\" title = \"count: " + temp.value() + "\">" + temp.key()
                    + "</span>");
        }

        //Finishes the HTML
        w.println("  </table>");
        w.println(" </body>");
        w.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        //Creates reader and writer
        SimpleWriter write = new SimpleWriter1L();
        SimpleReader read = new SimpleReader1L();
        //Runs the methods
        String[] files = userInput(write, read);
        SimpleReader scan = new SimpleReader1L(files[0]);
        Queue<String> words = wordOrSeparator(scan);
        Map<String, Integer> wordCount = count(words);
        SimpleWriter w = new SimpleWriter1L(files[1]);
        writeHTML(wordCount, w, files[0], Integer.parseInt(files[2]));
    }

}
