public class Homework10 {

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     * </pre>
     */
    public static int mod(int a, int b) {

        int remainder;

        if (a < 0) {
            remainder = a % b;
            remainder = Math.abs(remainder);
            remainder = b - remainder;
        } else {
            remainder = a % b;
        }

        return remainder;
    }

    public static void main(String[] args) {

        int remainder = mod(-1, 10);
        System.out.println(remainder);

    }

}