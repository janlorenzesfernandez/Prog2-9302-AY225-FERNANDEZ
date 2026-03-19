const readline = require('readline');

// Create an interface to read input from the console
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Input Collection
rl.question("Enter attendance (0-100): ", (attendance) => {
    rl.question("Enter Lab Work 1 grade (0-100): ", (lab1) => {
        rl.question("Enter Lab Work 2 grade (0-100): ", (lab2) => {
            rl.question("Enter Lab Work 3 grade (0-100): ", (lab3) => {
                
                // Convert inputs to numbers
                attendance = parseFloat(attendance);
                lab1 = parseFloat(lab1);
                lab2 = parseFloat(lab2);
                lab3 = parseFloat(lab3);
                
                // Calculations
                let labWorkAverage = (lab1 + lab2 + lab3) / 3;
                let classStanding = (attendance * 0.40) + (labWorkAverage * 0.60);

                // Calculate required Prelim Exam score for passing (75) and excellent (100)
                let requiredPrelimForPassing = (75 - (classStanding * 0.30)) / 0.70;
                let requiredPrelimForExcellent = (100 - (classStanding * 0.30)) / 0.70;

                // Output Results
                console.log("--- Results ---");
                console.log(`Attendance: ${attendance.toFixed(2)}`);
                console.log(`Lab Work 1 Grade: ${lab1.toFixed(2)}`);
                console.log(`Lab Work 2 Grade: ${lab2.toFixed(2)}`);
                console.log(`Lab Work 3 Grade: ${lab3.toFixed(2)}`);
                console.log(`Lab Work Average: ${labWorkAverage.toFixed(2)}`);
                console.log(`Class Standing: ${classStanding.toFixed(2)}`);
                console.log(`Required Prelim Exam Score to Pass (75): ${requiredPrelimForPassing.toFixed(2)}`);
                console.log(`Required Prelim Exam Score for Excellent (100): ${requiredPrelimForExcellent.toFixed(2)}`);

                if (requiredPrelimForPassing <= 100) {
                    console.log("You can pass if you achieve this Prelim score.");
                } else {
                    console.log("It is not possible to pass based on current scores.");
                }

                if (requiredPrelimForExcellent <= 100) {
                    console.log("You can achieve Excellent standing with this Prelim score.");
                } else {
                    console.log("It is not possible to achieve Excellent standing based on current scores.");
                }

                // Close the readline interface
                rl.close();
            });
        });
    });
});
