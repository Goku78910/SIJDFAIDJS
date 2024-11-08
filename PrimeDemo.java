package aos3;

import aos3.Aos3;
import java.util.Scanner;

public class PrimeDemo {
    public static void main(String[] args) {
        int num;
        // Create Scanner object to take input from the user
        Scanner s = new Scanner(System.in);
        try {
            System.out.println("\nEnter up to which number prime numbers are needed:");
            num = s.nextInt();
            // Create and start the Prac3 thread to find prime numbers
            Aos3 pt = new Aos3(num);
            pt.start();
        } finally {
            s.close();  // Always close the scanner to prevent resource leaks
        }
    }
}
