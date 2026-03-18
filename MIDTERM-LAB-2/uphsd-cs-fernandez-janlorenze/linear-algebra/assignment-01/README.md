--Fernandez, Jan Lorenze S. BSIT-GD

--Course: Math 101 – Linear Algebra, UPHSD Molino Campus

--Assignment title and number: programming 2 assignment 1: 3x3 matrix Determinant solver

--assigned matrix:
4 1 3
2 6 5
1 3 2

--how to run java program:
Step 1: Compile the Code
Copy and paste this command to create the executable bytecode:
javac DeterminantSolver.java
Note: If you get an error here, it usually means there is a typo in your filename or Java isn't installed.

Step 2: Run the Program
Copy and paste this command to see your output:
java DeterminantSolver

--how to run javascript program:
type in the terminal:
node Determinant_solver.js
Note: you must make sure your node.js is installed

--sample output(JAVA):
 3x3 DETERMINANT SOLVER
  Student: Fernandez, Jan Lorenze S.
  Assigned Matrix:
  |  4   1   3  |
  |  2   6   5  |
  |  1   3   2  |

Expanding along Row 1 (cofactor expansion):

  Step 1 Minor M: det([6,5],[3,2]) = (6 * 2) - (5 * 3) = 12 - 15 = -3
  Step 2 Minor M: det([2,5],[1,2]) = (2 * 2) - (5 * 1) = 4 - 5 = -1  
  Step 3 Minor M: det([2,6],[1,3]) = (2 * 3) - (6 * 1) = 6 - 6 = 0   

  Cofactor C11 = (+1) x 4 x -3 =  -12
  Cofactor C12 = (-1) x 1 x -1 =    1
  Cofactor C13 = (+1) x 3 x  0 =    0

  det(M) = -12 + (1) + 0

  DETERMINANT = -11

--sample output(JAVASCRIPT):
 DETERMINANT SOLVER
  Student: Fernandez, Jan Lorenze S.
  Assigned Matrix:
  |  4   1   3  |
  |  2   6   5  |
  |  1   3   2  |
Expanding along Row 1 (cofactor expansion):
  Step 1 — Minor M₁₁: det([6,5],[3,2]) = (6×2) - (5×3) = 12 - 15 = -3
  Step 2 — Minor M₁₂: det([2,5],[1,2]) = (2×2) - (5×1) = 4 - 5 = -1
  Step 3 — Minor M₁₃: det([2,6],[1,3]) = (2×3) - (6×1) = 6 - 6 = 0
  Cofactor C₁₁ = (+1) × 4 × -3 =  -12
  Cofactor C₁₂ = (-1) × 1 × -1 =    1
  Cofactor C₁₃ = (+1) × 3 ×  0 =    0
  det(M) = -12 + (1) + 0
DETERMINANT = -11

--The final determinant value = -11