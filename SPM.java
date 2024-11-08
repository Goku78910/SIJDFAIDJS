package comp3;

public class SPM {

    // Define symbol and precedence matrices
    static char[] symbol = {'Z', 'M', 'L', 'a', 'b', '(', ')'};
    static int[][] equalto = new int[7][7];
    static int[][] first = new int[7][7];
    static int[][] last = new int[7][7];
    static int[][] firstplus = new int[7][7];
    static int[][] lastt = new int[7][7];
    static int[][] less = new int[7][7];
    static int[][] greater = new int[7][7];
    static int[][] identity = new int[7][7];
    static int[][] xyz = new int[7][7];
    static int[][] spm = new int[7][7];
    static int[][] something = new int[7][7];

    // Function to return the index of a symbol in the array 'symbol'
    static int indexing(char c) {
        for (int i = 0; i < 7; i++) {
            if (symbol[i] == c) {
                return i;
            }
        }
        return -1; // Return -1 if character is not found
    }

    // Function to print matrix
    static void printMat(int[][] matrix) {
        for (int i = 0; i < 7; i++) {
            System.out.print("\t" + symbol[i]);
        }
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print(symbol[i]);
            for (int j = 0; j < 7; j++) {
                System.out.print("\t" + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // LHS and RHS for the given expressions
        char[] lhs = {'Z', 'M', 'M', 'L'};
        String[] rhs = {"bMb", "a", "(L", "Ma)"};

        // Initialize matrices with zeroes
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                equalto[i][j] = 0;
                first[i][j] = 0;
                last[i][j] = 0;
                firstplus[i][j] = 0;
                lastt[i][j] = 0;
                less[i][j] = 0;
                greater[i][j] = 0;
                identity[i][j] = 0;
                xyz[i][j] = 0;
                something[i][j] = 0;
                spm[i][j] = 0;
            }
        }

        // Fill the equalto matrix based on rhs expressions
        for (int count = 0; count <= 3; count++) {
            int j = 0;
            while (j < rhs[count].length()) {  // Use length() to avoid going out of bounds
                int a = indexing(rhs[count].charAt(j));
                j++;
                if (j < rhs[count].length()) {
                    int b = indexing(rhs[count].charAt(j));
                    equalto[a][b] = 1;
                } else {
                    break;
                }
            }
        }

        // Print EqualTo Matrix
        System.out.println("EqualTo Matrix:");
        printMat(equalto);

        // Fill first matrix
        for (int count = 0; count <= 3; count++) {
            int a = indexing(lhs[count]);
            if (rhs[count].length() > 0) {  // Ensure rhs[count] is not empty
                int b = indexing(rhs[count].charAt(0));  // First character of rhs[count]
                first[a][b] = 1;
            }
        }

        // Print First Matrix
        System.out.println("\nFirst Matrix:");
        printMat(first);

        // Fill last matrix based on rhs length
        for (int count = 0; count <= 3; count++) {
            int len = 0;
            for (int i = 0; i < rhs[count].length(); i++) {  // Check entire string length
                if (rhs[count].charAt(i) != '\0') {
                    len++;
                }
            }
            int a = indexing(lhs[count]);
            if (len > 0) {  // Ensure length is not zero
                int b = indexing(rhs[count].charAt(len - 1));  // Last character of rhs[count]
                last[a][b] = 1;
            }
        }

        // Print Last Matrix
        System.out.println("\nLast Matrix:");
        printMat(last);

        // Fill firstplus matrix (transitive closure for first matrix)
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    firstplus[i][j] = firstplus[i][j] | (first[i][k] & first[k][j]);
                }
            }
        }

        // Fill lastt matrix (transitive closure for last matrix)
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    lastt[i][j] = lastt[i][j] | (last[i][k] & last[k][j]);
                }
            }
        }

        // Fill less matrix based on equalto, first matrices
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    less[i][j] += equalto[i][k] * first[k][j];
                }
            }
        }

        // Fill greater matrix based on lastt, equalto, and something matrices
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    xyz[i][j] += lastt[i][k] * equalto[k][j];
                }
                something[i][j] = identity[i][j] + first[i][j];
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 7; k++) {
                    greater[i][j] = greater[i][j] | (xyz[i][k] & something[k][j]);
                }
            }
        }

        // Fill spm matrix based on conditions
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (equalto[i][j] == 1) {
                    spm[i][j] = 1;
                } else if (less[i][j] == 1) {
                    spm[i][j] = 2;
                } else if (greater[i][j] == 1) {
                    spm[i][j] = 3;
                }
            }
        }

        // Print the final SPM (Symbol Precedence Matrix)
        System.out.println("\nSPM:");
        printMat(spm);

        System.out.println("\n1 denotes equal to entries\n2 denotes less than entries\n3 denotes greater than entries");
    }
}
