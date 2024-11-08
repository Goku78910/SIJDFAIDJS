/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp5;

import java.util.Arrays;

public class OPM {
    static final int N = 8;  // Size of the matrix

    // Symbols and the grammar's rule indices
    static char[] symbol = {'E', 'T', 'F', 'i', '+', '*', '(', ')'};
    static char[] lhs = {'E', 'E', 'T', 'T', 'F', 'F'};
    static String[] rhs = {"E+T", "T", "T*F", "F", "(E)", "i"};

    public static void main(String[] args) {
        // Matrices
        int[][] equalto = new int[N][N];
        int[][] first = new int[N][N];
        int[][] last = new int[N][N];
        int[][] identity = new int[N][N];
        int[][] firstStar = new int[N][N];
        int[][] firstTerm = new int[N][N];
        int[][] temp = new int[N][N];
        int[][] temp1 = new int[N][N];
        int[][] lastStar = new int[N][N];
        int[][] lastTerm = new int[N][N];
        int[][] temp2 = new int[N][N];
        int[][] temp3 = new int[N][N];
        int[][] temp4 = new int[N][N];

        // Initialize all matrices to 0
        assign(equalto);
        assign(first);
        assign(last);
        assign(identity);
        assign(temp2);
        assign(temp3);
        assign(firstStar);
        assign(firstTerm);
        assign(temp);
        assign(temp1);
        assign(lastStar);
        assign(lastTerm);

        // Initialize identity matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                identity[i][j] = (i == j) ? 1 : 0;
            }
        }

        // Set up the Equalto matrix
        for (int count = 0; count <= 5; count++) {
            int j = 0;
            while (j < rhs[count].length()) {
                int a = indexing(rhs[count].charAt(j));
                j++;
                if (j < rhs[count].length() && rhs[count].charAt(j) != '\0') {
                    equalto[a][indexing(rhs[count].charAt(j))] = 1;
                }
                if (j >= rhs[count].length()) {
                    break; // Avoid out of bounds
                }
            }
        }

        // Printing matrices
        System.out.println("\nThe Equalto matrix is:\n");
        printMat(equalto);

        System.out.println("\nThe First matrix is:\n");
        for (int count = 0; count <= 5; count++) {
            int len = rhs[count].length();
            int a = indexing(lhs[count]);
            int b = indexing(rhs[count].charAt(0));
            first[a][b] = 1;
        }
        printMat(first);

        System.out.println("\nThe first+ matrix is: \n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    first[i][j] = first[i][j] | (first[i][k] & first[k][j]);
                }
            }
        }
        printMat(first);

        // Compute the first* matrix
        System.out.println("\nThe First* matrix is: \n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                firstStar[i][j] = identity[i][j] | first[i][j];
            }
        }
        printMat(firstStar);

        // Compute the First Term matrix
        System.out.println("\nThe First Term matrix is: \n");
        for (int count = 0; count <= 5; count++) {
            for (int i = 0; i < rhs[count].length(); i++) {
                if ("i+*()".indexOf(rhs[count].charAt(i)) >= 0) {
                    int a = indexing(lhs[count]);
                    int b = indexing(rhs[count].charAt(i));
                    firstTerm[a][b] = 1;
                    break;
                }
            }
        }
        printMat(firstTerm);

        // Calculate (First*).(First Term)
        System.out.println("\nThe (First*).(First Term) matrix is: \n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    temp[i][j] = temp[i][j] | (firstStar[i][k] & firstTerm[k][j]);
                }
            }
        }
        printMat(temp);

        // Calculate (Equalto).(First*).(First Term)
        System.out.println("\nThe (equalto).(First*).(First Term) matrix is: \n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    temp1[i][j] = temp1[i][j] | (equalto[i][k] & temp[k][j]);
                }
            }
        }
        printMat(temp1);

        // Now process the "last" matrices, similar to the previous steps
        System.out.println("\nThe last matrix is: \n");
        for (int count = 0; count <= 5; count++) {
            int len = rhs[count].length();
            int a = indexing(lhs[count]);
            int b = indexing(rhs[count].charAt(len - 1));
            last[a][b] = 1;
        }
        printMat(last);

        // Compute the Last+ matrix
        System.out.println("\nThe Last+ matrix is: \n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    last[i][j] = last[i][j] | (last[i][k] & last[k][j]);
                }
            }
        }
        printMat(last);

        // Compute the Last* matrix
        System.out.println("\nThe Last* matrix is: \n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                lastStar[i][j] = identity[i][j] | last[i][j];
            }
        }
        printMat(lastStar);

        // Compute the Last Term matrix
        System.out.println("\nThe Last Term matrix is: \n");
        for (int count = 0; count <= 5; count++) {
            for (int i = 0; i < rhs[count].length(); i++) {
                if ("i+*()".indexOf(rhs[count].charAt(i)) >= 0) {
                    int a = indexing(lhs[count]);
                    int b = indexing(rhs[count].charAt(i));
                    lastTerm[a][b] = 1;
                }
            }
        }
        printMat(lastTerm);

        // Calculate (Last*).(Last Term)
        System.out.println("\nThe (Last*).(Last Term) matrix is: \n");
        assign(temp2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    temp2[i][j] = temp2[i][j] | (lastStar[i][k] & lastTerm[k][j]);
                }
            }
        }
        printMat(temp2);

        // Final calculations for OPM matrix
        System.out.println("\nThe {(Last*).(Last Term)}.(equalto) matrix is: \n");
        assign(temp4);
        transpose(temp2, temp4);
        assign(temp2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    temp2[i][j] = temp2[i][j] | (temp4[i][k] & equalto[k][j]);
                }
            }
        }
        printMat(temp1);

        // Output the final OPM matrix
        System.out.println("\nOPM matrix is:\n");
        char[][] opm = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (equalto[i][j] == 1) {
                    opm[i][j] = '=';
                } else if (temp1[i][j] == 1) {
                    opm[i][j] = '<';
                } else if (temp2[i][j] == 1) {
                    opm[i][j] = '>';
                }
            }
        }

        // Print the OPM matrix
        for (int count = 0; count < N; count++) {
            System.out.print("\t" + symbol[count]);
        }
        System.out.println();
        for (int count = 0; count < N; count++) {
            System.out.print(symbol[count]);
            for (int j = 0; j < N; j++) {
                System.out.print("\t" + opm[count][j]);
            }
            System.out.println();
        }
    }

    // Assigns all values in the matrix to 0
    public static void assign(int[][] p) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(p[i], 0);
        }
    }

    // Prints the matrix in a readable format
    public static void printMat(int[][] p) {
        for (int count = 0; count < N; count++) {
            System.out.print("\t" + symbol[count]);
        }
        System.out.println();
        for (int count = 0; count < N; count++) {
            System.out.print(symbol[count]);
            for (int j = 0; j < N; j++) {
                System.out.print("\t" + p[count][j]);
            }
            System.out.println();
        }
    }

    // Returns the index of a character from the symbol array
    public static int indexing(char c) {
        for (int z = 0; z < N; z++) {
            if (symbol[z] == c) {
                return z;
            }
        }
        return -1;  // This should not happen if the character is valid
    }

    // Transposes matrix p into matrix q
    public static void transpose(int[][] p, int[][] q) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                q[i][j] = p[j][i];
            }
        }
    }
}
