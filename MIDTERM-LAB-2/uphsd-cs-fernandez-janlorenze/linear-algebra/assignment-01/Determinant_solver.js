/**
Student  : Fernandez, Jan Lorenze S.
Student ID: 25 2262 789
Course   : 9302
Assignment: Determinant via Cofactor Expansion (3×3 Matrix)
Date     : March 18, 2026
Description: Computes the determinant of a hardcoded 3×3
             matrix using cofactor expansion along the first
             row, printing every intermediate step clearly
github url: https://github.com/janlorenzesfernandez/Prog2-9302-AY225-FERNANDEZ.git
 */
//Prints the title banner, student name, and the 3x3 assigned matrix
//Matrix bordered by vertical bars and with each element right-aligned for clarity.
const printMatrix = (M) => {
    console.log("  DETERMINANT SOLVER");
    console.log("  Student: Fernandez, Jan Lorenze S.");
    console.log("  Assigned Matrix:");

    M.forEach(row => {
        const [a, b, c] = row.map(n => String(n).padStart(2));
        console.log(`  | ${a}  ${b}  ${c}  |`);
    });
};

//Calculates the determinant of the 4 elements and returns their determinant using the formula (a*d) - (b*c).
const computeMinor = (a, b, c, d) => (a * d) - (b * c);


//Prints each 2x2 minor, each cofactor term, the expansion sum, and the final determinant.

const solveDeterminant = (M) => {

    console.log("Expanding along Row 1 (cofactor expansion):");
    console.log();

    //Pivot elements
    const [m00, m01, m02] = M[0];

    //Minor M11:
    const minor11 = computeMinor(M[1][1], M[1][2], M[2][1], M[2][2]);
    console.log(
        `  Step 1 \u2014 Minor M\u2081\u2081: det([${M[1][1]},${M[1][2]}],[${M[2][1]},${M[2][2]}])` +
        ` = (${M[1][1]}\u00d7${M[2][2]}) - (${M[1][2]}\u00d7${M[2][1]})` +
        ` = ${M[1][1]*M[2][2]} - ${M[1][2]*M[2][1]} = ${minor11}`
    );

    //Minor M12:
    const minor12 = computeMinor(M[1][0], M[1][2], M[2][0], M[2][2]);
    console.log(
        `  Step 2 \u2014 Minor M\u2081\u2082: det([${M[1][0]},${M[1][2]}],[${M[2][0]},${M[2][2]}])` +
        ` = (${M[1][0]}\u00d7${M[2][2]}) - (${M[1][2]}\u00d7${M[2][0]})` +
        ` = ${M[1][0]*M[2][2]} - ${M[1][2]*M[2][0]} = ${minor12}`
    );

    //Minor M13:
    const minor13 = computeMinor(M[1][0], M[1][1], M[2][0], M[2][1]);
    console.log(
        `  Step 3 \u2014 Minor M\u2081\u2083: det([${M[1][0]},${M[1][1]}],[${M[2][0]},${M[2][1]}])` +
        ` = (${M[1][0]}\u00d7${M[2][1]}) - (${M[1][1]}\u00d7${M[2][0]})` +
        ` = ${M[1][0]*M[2][1]} - ${M[1][1]*M[2][0]} = ${minor13}`
    );

    console.log();

    //Compute each signed cofactor term
    const term1 =  m00 * minor11;  
    const term2 = -m01 * minor12;  
    const term3 =  m02 * minor13;  

    console.log(`  Cofactor C\u2081\u2081 = (+1) \u00d7 ${m00} \u00d7 ${String(minor11).padStart(2)} = ${String(term1).padStart(4)}`);
    console.log(`  Cofactor C\u2081\u2082 = (-1) \u00d7 ${m01} \u00d7 ${String(minor12).padStart(2)} = ${String(term2).padStart(4)}`);
    console.log(`  Cofactor C\u2081\u2083 = (+1) \u00d7 ${m02} \u00d7 ${String(minor13).padStart(2)} = ${String(term3).padStart(4)}`);

    console.log();

    //first-row cofactor expansion formula
    const det = M[0][0] * (M[1][1]*M[2][2] - M[1][2]*M[2][1])
              - M[0][1] * (M[1][0]*M[2][2] - M[1][2]*M[2][0])
              + M[0][2] * (M[1][0]*M[2][1] - M[1][1]*M[2][0]);

    // Print expansion sum then show final result after
    console.log(`  det(M) = ${term1} + (${term2}) + ${term3}`);
    console.log();
    console.log(`DETERMINANT = ${det}`);

    //if the determinant is zero then the matrix is singular and has no inverse
    if (det === 0) {
        console.log("  The matrix is SINGULAR \u2014 it has no inverse.");
    }
};

// --------------------------------------------------------
// Hardcoded matriz Fernandez, Jan Lorenze S. 
// --------------------------------------------------------
const matrix = [
    [4, 1, 3],
    [2, 6, 5],
    [1, 3, 2]
];

// Run the program
printMatrix(matrix);
solveDeterminant(matrix);