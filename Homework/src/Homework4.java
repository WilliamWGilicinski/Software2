public class Homework4 {

    /**
     *  * Returns the integer average of two given {@code int}s.  *  * @param j
     *  *            the first of two integers to average  * @param k
     *  *            the second of two integers to average  * @return the
     * integer average of j and k  * @ensures average = (j+k)/2  
     */
    public static int average(int j, int k) {
        int jRem = j % 2;
        int kRem = k % 2;
        int answer = ((j / 2) + (k / 2));
        int total = jRem + kRem;
        if (total == 2) {
            answer += 1;
        } else if (total == -2) {
            answer -= 1;
        }

        return answer;
    }

    public static void main(String[] args) {

    }

}