public class Determinant {
    public static void main(String[] args) {
        int[][] mat = {
            {4, 1, 3},
            {2, 6, 5},
            {1, 3, 2}
        };

        int determinant = mat[0][0] * (mat[1][1] * mat[2][2] - mat[1][2] * mat[2][1]) -
                          mat[0][1] * (mat[1][0] * mat[2][2] - mat[1][2] * mat[2][0]) +
                          mat[0][2] * (mat[1][0] * mat[2][1] - mat[1][1] * mat[2][0]);
        
        int m1 = mat[0][0] * (mat[1][1] * mat[2][2] - mat[1][2] * mat[2][1]); // 4(6 * 2 - 5 * 3)
        int m1a = mat[1][1] * mat[2][2]; // 6 * 2 = 12
        int m1b = mat[1][2] * mat[2][1]; // 5 * 3 = 15
        int m1c = mat[1][1] * mat[2][2] - mat[1][2] * mat[2][1]; // 12 - 15 = -3
        int m1d = m1c * mat[0][0]; // -3 * 4 = -12

        int m2 = mat[0][1] * (mat[1][0] * mat[2][2] - mat[1][2] * mat[2][0]); // 1(2 * 2 - 5 * 1)
        int m2a = mat[1][0] * mat[2][2]; // 2 * 2 = 4
        int m2b = mat[1][2] * mat[2][0]; // 5 * 1 = 5
        int m2c = m2a - m2b; // 4 - 5 = -1
        int m2d = m2c * mat[0][1]; // -1 * 1 = -1
        
        int m3 = mat[0][2] * (mat[1][0] * mat[2][1] - mat[1][1] * mat[2][0]); // 3(2 * 3 - 6 * 1)
        int m3a = mat[1][0] * mat[2][1]; // 2 * 3 = 6
        int m3b = mat[1][1] * mat[2][0]; // 6 * 1 = 6
        int m3c = m3a - m3b; // 6 - 6 = 0
        int m3d = m3c * mat[0][2]; // 0 * 3 = 0


        
        System.out.println("================================");
        System.out.println("The determinant of the matrix is: " + determinant);
        System.out.println("================================");

        System.out.println("================================");
        System.out.println("4(6*2 - 5*3)");
        System.out.println("4" + "(" + m1a + " - " + m1b + ")");
        System.out.println(mat[0][0] + " * " + m1c + " = " + m1d);
        System.out.println("m1:" + m1);
        System.out.println("================================");

        System.out.println("================================");
        System.out.println("1(2*2 - 5*1)");
        System.out.println("1" + "(" + m2a + " - " + m2b + ")");
        System.out.println(mat[0][1] + " * " + m2c + " = " + m2d);
        System.out.println("m2:" + m2);
        System.out.println("================================");

        System.out.println("================================");
        System.out.println("3(2*3 - 6*1)");
        System.out.println("3" + "(" + m3a + " - " + m3b + ")");
        System.out.println(mat[0][2] + " * " + m3c + " = " + m3d);
        System.out.println("m3:" + m3);
        System.out.println("================================");

        System.out.println("================================");
        System.out.println("Final solution: " + m1d + " - " + m2d + " + " + m3d + " = " + determinant);
        System.out.println("================================");
    }
}
    