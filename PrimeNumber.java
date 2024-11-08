package aos3;

import java.util.Scanner;

public class Aos3 extends Thread {
    int num;

    // Constructor to initialize the num variable
    Aos3(int num1) {
        this.num = num1;  // Use 'this' to assign the instance variable
    }

    public void run() {
        int n = num;
        // Loop through numbers from 2 to n (exclusive)
        for (int i = 2; i < n; i++) {
            boolean isPrime = true;
            // Check divisibility from 2 to sqrt(i) for efficiency
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;  // No need to check further if 'i' is divisible by 'j'
                }
            }
            // If no divisors were found, print the prime number
            if (isPrime) {
                System.out.println(i);
            }
        }
    }
}


