/*
  Student  : Fernandez, Jan Lorenze S. 
  Student ID: 25 2262 789
  Course   : 9302
  Assignment: Determinant via Cofactor Expansion
  Date     : March 18, 2026
  Description: Computes the determinant of a hardcoded 3×3
               matrix using cofactor expansion along the first
               row, printing every intermediate step clearly.
  github url: https://github.com/janlorenzesfernandez/Prog2-9302-AY225-FERNANDEZ.git
 */

public class DeterminantSolver {

    
    //declares the assigned matrix and triggersthe matrix display followed by the full cofactor expansion process to compute the determinant.
    
    public static void main(String[] args) {

        // Hardcoded assigned matrix for Fernandez, Jan Lorenze S.
        int[][] matrix = {
            {4, 1, 3},
            {2, 6, 5},
            {1, 3, 2}
        };

        // Show the header banner and the matrix, then solve for the determinant
        printMatrix(matrix);
        solveDeterminant(matrix);
    }

    
    // Prints the title banner, student name, and the 3x3
    public static void printMatrix(int[][] M) {
        System.out.println("  3x3 DETERMINANT SOLVER");
        System.out.println("  Student: Fernandez, Jan Lorenze S.");
        System.out.println("  Assigned Matrix:");
        for (int[] row : M) {
            System.out.printf("  | %2d  %2d  %2d  |%n", row[0], row[1], row[2]);
        }
        System.out.println();
    }

    
    // Accepts the 4 elements of a 2x2 sub-matrix and returns its determinant using the formula (a*d) - (b*c).
    
    public static int computeMinor(int a, int b, int c, int d) {
        return (a * d) - (b * c);
    }

    
    //prints each 2x2, each signed cofactor term, the expansion sum, and the determinant.
    
    public static void solveDeterminant(int[][] M) {

        System.out.println("Expanding along Row 1 (cofactor expansion):");
        System.out.println();

        // Pivot elements
        int m00 = M[0][0], m01 = M[0][1], m02 = M[0][2];

        //Minor M11
        int minor11 = computeMinor(M[1][1], M[1][2], M[2][1], M[2][2]);
        System.out.printf("  Step 1 Minor M: det([%d,%d],[%d,%d]) = (%d * %d) - (%d * %d) = %d - %d = %d%n",
            M[1][1], M[1][2], M[2][1], M[2][2],
            M[1][1], M[2][2], M[1][2], M[2][1],
            M[1][1]*M[2][2], M[1][2]*M[2][1], minor11);

        //Minor M12
        int minor12 = computeMinor(M[1][0], M[1][2], M[2][0], M[2][2]);
        System.out.printf("  Step 2 Minor M: det([%d,%d],[%d,%d]) = (%d * %d) - (%d * %d) = %d - %d = %d%n",
            M[1][0], M[1][2], M[2][0], M[2][2],
            M[1][0], M[2][2], M[1][2], M[2][0],
            M[1][0]*M[2][2], M[1][2]*M[2][0], minor12);

        //Minor M13
        int minor13 = computeMinor(M[1][0], M[1][1], M[2][0], M[2][1]);
        System.out.printf("  Step 3 Minor M: det([%d,%d],[%d,%d]) = (%d * %d) - (%d * %d) = %d - %d = %d%n",
            M[1][0], M[1][1], M[2][0], M[2][1],
            M[1][0], M[2][1], M[1][1], M[2][0],
            M[1][0]*M[2][1], M[1][1]*M[2][0], minor13);

        System.out.println();

        //Compute each signed cofactor term 
        int term1 =  m00 * minor11;   
        int term2 = -m01 * minor12;   
        int term3 =  m02 * minor13;   

        System.out.printf("  Cofactor C11 = (+1) x %d x %2d = %4d%n", m00, minor11, term1);
        System.out.printf("  Cofactor C12 = (-1) x %d x %2d = %4d%n", m01, minor12, term2);
        System.out.printf("  Cofactor C13 = (+1) x %d x %2d = %4d%n", m02, minor13, term3);

        System.out.println();

        //first-row cofactor expansion formula
        int det = M[0][0] * (M[1][1]*M[2][2] - M[1][2]*M[2][1])
                - M[0][1] * (M[1][0]*M[2][2] - M[1][2]*M[2][0])
                + M[0][2] * (M[1][0]*M[2][1] - M[1][1]*M[2][0]);

        // Print the expansion sum before the final result
        System.out.printf("  det(M) = %d + (%d) + %d%n", term1, term2, term3);
        System.out.println();
        System.out.printf("  DETERMINANT = %d%n", det);

        //determinant = zero = the matrix cannot be inverted
        if (det == 0) {
            System.out.println("  The matrix is SINGULAR \u2014 it has no inverse.");
        }
    }
}