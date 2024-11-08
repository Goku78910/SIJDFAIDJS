/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp1;
public class RLGtoLLG {
    public static void main(String[] args) {
        // Declare the rhs and lhs arrays
        String[] rhs = {"b", "bU", "cR", "c"};
        char[] lhs = {'R', 'S', 'U', 'U'};
        char temp;

        // Display Initial Productions
        System.out.println("Initial Productions are:-");
        for (int i = 0; i < 4; i++) {
            System.out.println(lhs[i] + " ---> " + rhs[i]);
        }

        System.out.println("Production after Grammar:");

        // Modify rhs if necessary
        for (int i = 0; i < 4; i++) {
            if (rhs[i].length() > 1 && rhs[i].charAt(0) >= 'a' && rhs[i].charAt(0) <= 'z') {
                // Swap the characters in rhs if the first character is lowercase
                temp = rhs[i].charAt(0);
                rhs[i] = String.valueOf(rhs[i].charAt(1)) + temp; // Swap positions
            }
        }

        // Display Modified Productions
        for (int i = 0; i < 4; i++) {
            if (rhs[i].length() == 1) {
                System.out.println(lhs[i] + " ---> S" + rhs[i]);
            } else {
                System.out.println(lhs[i] + " ---> " + rhs[i]);
            }
        }
    }
}


