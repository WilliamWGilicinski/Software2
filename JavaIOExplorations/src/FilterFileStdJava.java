import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class FilterFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private FilterFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        BufferedReader input;
        BufferedWriter output;
        BufferedReader filterIn;
        Set<String> filter = new HashSet<>();

        //Set up the input reader
        try {
            input = new BufferedReader(new FileReader(args[0]));
        } catch (IOException e) {
            System.err.println("Error input File");
            return;
        }

        //Set up the filter reader and the set of filtered words
        try {
            filterIn = new BufferedReader(new FileReader(args[2]));
            while (filterIn.ready()) {
                String line = filterIn.readLine();
                if (line != null) {
                    filter.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error opening filter file");
        }

        //Set up writer and do copys the filtered lines
        try {
            output = new BufferedWriter(
                    new PrintWriter(new FileWriter(args[1])));

            while (input.ready()) {
                String line = input.readLine();
                if (line != null) {
                    //Splits the line into words
                    boolean contains = false;
                    //Checks if the line contains a filter word
                    Iterator<String> it = filter.iterator();
                    while (it.hasNext() && !contains) {
                        if (line.contains(it.next())) {
                            output.write(line);
                            output.write("\n");
                            contains = true;
                        }
                    }
                }
            }
            output.close();
        } catch (IOException e) {
            System.err.println("Error processing File");
        }

        try {
            input.close();
        } catch (IOException e) {
            System.err.println("Error closing File");
        }

    }

}
