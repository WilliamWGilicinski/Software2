import java.util.Map;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    private static Map<String, Integer> data;
    private String firstName;
    private String lastName;

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {

        if (EmailAccount1.data.containsKey(lastName)) {
            int num = EmailAccount1.data.get(lastName);
            EmailAccount1.data.replace(lastName, num, num + 1);
        } else {
            EmailAccount1.data.put(lastName, 1);
        }
        this.firstName = firstName;
        this.lastName = lastName;

    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {

        String fullName = this.firstName + " " + this.lastName;
        return fullName;

    }

    @Override
    public String emailAddress() {

        return this.lastName.toLowerCase() + "."
                + EmailAccount1.data.get(this.lastName) + "@osu.edu";

    }

    @Override
    public String toString() {

        return "Name: " + EmailAccount1.this.name() + ", Email: "
                + EmailAccount1.this.emailAddress();

    }

}