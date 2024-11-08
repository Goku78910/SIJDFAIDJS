/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp4;


import java.util.Stack;

public class SPGtoSPM {
    // Global variables
    static int i = 0, j = 0, c = 0;
    static String a = "32423";  // Example input string
    static String ac = "REDUCE TO E -> ";  // Action message for reduction
    static Stack<Character> stk = new Stack<>();  // Stack to hold symbols

    // Method to check the stack for any applicable production rules
    public static void check() {
        // Check for production rule E -> 4
        for (int z = 0; z < stk.size(); z++) {
            if (stk.get(z) == '4') {
                System.out.print(ac + "4");
                stk.set(z, 'E');
                stk.add(z + 1, '\0');
                System.out.println("\n$" + stackToString() + "\t" + a.substring(j) + "$");
            }
        }

        // Check for production rule E -> 2E2
        for (int z = 0; z < stk.size() - 2; z++) {
            if (stk.get(z) == '2' && stk.get(z + 1) == 'E' && stk.get(z + 2) == '2') {
                System.out.print(ac + "2E2");
                stk.set(z, 'E');
                stk.set(z + 1, '\0');
                stk.set(z + 2, '\0');
                System.out.println("\n$" + stackToString() + "\t" + a.substring(j) + "$");
                i = i - 2;
            }
        }

        // Check for production rule E -> 3E3
        for (int z = 0; z < stk.size() - 2; z++) {
            if (stk.get(z) == '3' && stk.get(z + 1) == 'E' && stk.get(z + 2) == '3') {
                System.out.print(ac + "3E3");
                stk.set(z, 'E');
                stk.set(z + 1, '\0');
                stk.set(z + 2, '\0');
                System.out.println("\n$" + stackToString() + "\t" + a.substring(j) + "$");
                i = i - 2;
            }
        }
    }

    // Helper method to convert the stack to a string
    public static String stackToString() {
        StringBuilder sb = new StringBuilder();
        for (Character c : stk) {
            sb.append(c);
        }
        return sb.toString();
    }

    // Driver function
    public static void main(String[] args) {
        System.out.println("GRAMMAR is -");
        System.out.println("E -> 2E2");
        System.out.println("E -> 3E3");
        System.out.println("E -> 4");

        // Calculate the length of the input string
        c = a.length();
        
        // Print initial labels
        System.out.println("\nstack \t input \t action");

        // Initial print of the stack and input
        System.out.println("\n$\t" + a + "$");

        // Start parsing the input string
        while (j < c) {
            // Perform shift action: move from input to stack
            System.out.print("SHIFT");
            stk.push(a.charAt(j));
            j++;
            System.out.println("\n$" + stackToString() + "\t" + a.substring(j) + "$");

            // Check for production rules after each shift
            check();
        }

        // Final check after parsing the entire input
        check();

        // Check if the start symbol 'E' is at the top of the stack to accept the input
        if (stk.size() == 1 && stk.peek() == 'E') {
            System.out.println("Accept");
        } else {
            System.out.println("Reject");
        }
    }
}
