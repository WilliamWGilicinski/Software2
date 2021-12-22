import components.map.Map;
import components.map.Map2;

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

    private static Map<String, Integer> data = new Map2<>();
    private String firstName;
    private String lastName;
    private int dotNum;

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

        String lowerCaseLast = lastName.toLowerCase();

        if (EmailAccount1.data.hasKey(lowerCaseLast)) {
            int num = EmailAccount1.data.value(lowerCaseLast);
            this.dotNum = num + 1;
            EmailAccount1.data.replaceValue(lowerCaseLast, this.dotNum);
        } else {
            this.dotNum = 1;
            EmailAccount1.data.add(lowerCaseLast, 1);
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

        return this.lastName.toLowerCase() + "." + this.dotNum + "@osu.edu";

    }

    @Override
    public String toString() {

        return "Name: " + EmailAccount1.this.name() + ", Email: "
                + EmailAccount1.this.emailAddress();

    }

}