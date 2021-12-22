import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Homework36 {

//    public static void main(String[] args) {
//        SimpleReader in = new SimpleReader1L(args[0]);
//        SimpleWriter fileOut = new SimpleWriter1L(args[1]);
//
//        while (!in.atEOS()) {
//            String line = in.nextLine();
//            fileOut.println(line);
//        }
//
//        in.close();
//        fileOut.close();
//    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader input = new BufferedReader(new FileReader(args[0]));
//        BufferedWriter output = new BufferedWriter(
//                new PrintWriter(new FileWriter(args[1])));
//
//        while (input.ready()) {
//            String line = input.readLine();
//            output.write(line);
//            output.write("\n");
//        }
//
//        input.close();
//        output.close();
//
//    }

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
                output.write(line);
                output.write("\n");
            }
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