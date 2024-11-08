package comp6;

import java.util.Scanner;
public class OPGtoOPM {
 static int[] index = new int[5];
 static int[][] opm = {
 {0, 0, 0, 1, 0, 0, 1, 0},
 {0, 0, 0, 0, 1, 0, 0, 0},
 {0, 0, 0, 0, 0, 0, 0, 0},
 {0, 1, 0, 3, 2, 2, 3, 2},
 {0, 0, 1, 3, 3, 2, 3, 2},
 {1, 0, 0, 2, 2, 2, 0, 2},
 {0, 0, 0, 3, 3, 0, 3, 0},
 {0, 0, 0, 3, 3, 0, 3, 0}
 };

 static char[] lhs = {'E', 'E', 'T', 'T', 'F', 'F'};
 static String[] prod = {"E+T", "T", "T*F", "F", "(E)", "i"};
 static char[] symbs = {'E', 'T', 'F', '+', '*', '(', ')', 'i'};
 static String[] parsing = new String[20];
 static char[] hand = new char[10];
 static int flag = 0, ind = 0, cnt = 0;
 public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);

 System.out.println("OPM Parsing");
 System.out.print("Enter string to parse: ");
 parsing[0] = scanner.nextLine();

 System.out.println("Step-by-step parsing:");
 for (int i = 0; parsing[i].length() != 1; i++) {
 flag = 0;
 int pos = 0;
 clearHand();

 hand[0] = parsing[i].charAt(0);
 for (int j = 0; flag != 1 && j < parsing[i].length(); j++) {
 System.out.println("i=" + i + " j=" + j);
 System.out.println("\tparsing[" + i + "][" + j + "]=" +
parsing[i].charAt(j));

 int a = func(parsing[i].charAt(j));
 if (a == -1) {
 System.out.println("Invalid symbol in input.");
 return;
 }

 System.out.println("a=" + a);

 int b = 0;
 if (j + 1 < parsing[i].length()) {
 b = func(parsing[i].charAt(j + 1));
 if (b == -1) {
 System.out.println("Invalid symbol in input.");
 return;
 }
 System.out.println("\tb=" + b);
 }

 if (j + 1 == parsing[i].length()) {
 greater(pos);
 }

 if (opm[a][b] == 1 || opm[a][b] == 0) {
 if (hand[0] == '\0') {
 hand[0] = parsing[i].charAt(j + 1);
 pos = j + 1;
 } else {
 hand[cnt] = parsing[i].charAt(j + 1);
 cnt++;
 }
 }

 if (opm[a][b] == 0) {
 cnt++;
 if (cnt == 1) {
 index[ind] = parsing[i].charAt(j + 1);
 }
 if (cnt == 2) {
 greater(ind);
 }
 }

 if (opm[a][b] == 3) {
 greater(pos);
 }

 if (opm[a][b] == 2) {
 // Specific processing logic if needed.
 }
 }
 }

 scanner.close();
 }

 static void clearHand() {
 for (int g = 0; g < hand.length; g++) {
 hand[g] = '\0';
 }

 }
 static void greater(int pos) {
 int z = -1, f = 0;
 for (int x = 0; x < lhs.length; x++) {
 if (new String(hand).trim().equals(prod[x])) {
 z = x;
 f = 1;
 }
 }
 if (f != 1) {
 System.out.println("Entered string is not parsable");
 System.exit(0);
 }
 flag = 1;
 step(z, pos);
 System.out.println("Call to greater with z=" + z + " and pos=" + pos);
 }
 static void step(int z, int pos) {
 int x, y, k1;
 for (x = 0; x < pos; x++) {
 parsing[ind + 1] = parsing[ind].substring(0, pos) + lhs[z] +
parsing[ind].substring(pos + 1);
 }

 System.out.println("Step is " + (ind + 1) + ": " + parsing[ind + 1]);
 }
 static int func(char c) {
 for (int i = 0; i < symbs.length; i++) {
 if (c == symbs[i]) {
 return i;
 }
 }
 return -1; // Return -1 if character is not found
 }
}