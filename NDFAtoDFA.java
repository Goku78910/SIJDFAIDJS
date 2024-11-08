/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp2;

import java.util.Scanner;

public class NDFAtoDFA {
    public static void main(String[] args) {
        // Define terminal and non-terminal symbols
        char[] terminal = {'a', '*', 'b'};
        char[] nonTerm = {'S', 'U', 'R'};
        
        // Arrays to store user input
        char[] init = new char[3];
        char[] term = new char[3];
        char[] finalSymbols = new char[3];
        
        Scanner scanner = new Scanner(System.in);
        
        // Input for initial, terminal, and final symbols
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter the values for initial symbols: ");
            init[i] = scanner.next().charAt(0);
            
            while (init[i] != nonTerm[0] && init[i] != nonTerm[1] && init[i] != nonTerm[2]) {
                System.out.print("Enter the values for initial symbols again: ");
                init[i] = scanner.next().charAt(0);
            }
            
            System.out.print("Enter the values for terminal symbols: ");
            term[i] = scanner.next().charAt(0);
            
            while (term[i] != terminal[0] && term[i] != terminal[1] && term[i] != terminal[2]) {
                System.out.print("Enter the values for terminal symbols again: ");
                term[i] = scanner.next().charAt(0);
            }
            
            System.out.print("Enter the values for final symbols: ");
            finalSymbols[i] = scanner.next().charAt(0);
            
            while (finalSymbols[i] != nonTerm[0] && finalSymbols[i] != nonTerm[1] && finalSymbols[i] != nonTerm[2]) {
                System.out.print("Enter the values for final symbols again: ");
                finalSymbols[i] = scanner.next().charAt(0);
            }
            System.out.println();
        }
        
        // Finding the index of '*' in terminal symbols
        int index = -1;
        char temp = '\0';
        for (int i = 0; i < 3; i++) {
            if (term[i] == '*') {
                index = i;
                temp = finalSymbols[i];
                break;
            }
        }

        // Swap values for the terminal symbol '*' with corresponding final symbol
        char temp1 = '\0', temp2 = '\0';
        for (int i = 0; i < 3; i++) {
            if (init[i] == temp) {
                temp1 = term[i];
                temp2 = finalSymbols[i];
                break;
            }
        }

        // Perform the swap
        term[index] = temp1;
        finalSymbols[index] = temp2;
        
        // Output Terminal Symbols after conversion
        System.out.println("Terminal Symbols after conversion:");
        for (int j = 0; j < 3; j++) {
            boolean isDuplicate = false;
            for (int k = j + 1; k < 3; k++) {
                if (term[j] == term[k]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                System.out.println(term[j]);
            }
        }

        // Output Non-terminal Symbols after conversion
        System.out.println("\nNon terminal Symbols after conversion:");
        for (int i = 0; i < 3; i++) {
            System.out.println(init[i]);
        }
        
        scanner.close();
    }
}

