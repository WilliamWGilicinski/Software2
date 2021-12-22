import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
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
        try {
            input = new BufferedReader(new FileReader(args[0]));
        } catch (IOException e) {
            System.err.println("Error opening File");
            return;
        }
        try {
            output = new BufferedWriter(
                    new PrintWriter(new FileWriter(args[1])));

            while (input.ready()) {
                String line = input.readLine();
                if (line != null) {
                    output.write(line);
                    output.write("\n");
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
