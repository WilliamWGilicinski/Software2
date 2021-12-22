import java.util.ArrayList;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        ArrayList<EmailAccount> emails = new ArrayList<>();

        out.println("Enter a name (enter a blank to quit)");
        String input = in.nextLine();
        while (!input.equals("")) {
            String[] names = input.split(" ");
            EmailAccount account = new EmailAccount1(names[0], names[1]);
            emails.add(account);
            for (EmailAccount acc : emails) {
                out.println(acc);
            }
            out.println("\nEnter a name (blank to quit)");
            input = in.nextLine();
        }

        in.close();
        out.close();
    }

}
