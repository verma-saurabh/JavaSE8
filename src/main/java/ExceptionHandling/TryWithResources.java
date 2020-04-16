package ExceptionHandling;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
Support for try-with-resources – introduced in Java 7 –
allows us to declare resources to be used in a try block with the
assurance that the resources will be closed when after the execution of that block.

The resources declared must implement the AutoCloseable interface.
 */
public class TryWithResources {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("test.txt"))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        /*
        Multiple Resources
         */

        try (Scanner scanner = new Scanner(new File("testRead.txt"));
             PrintWriter writer = new PrintWriter(new File("testWrite.txt"))) {
            while (scanner.hasNext()) {
                writer.print(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /*
        Resources that were defined/acquired first will be closed last;
         */
    }
}
