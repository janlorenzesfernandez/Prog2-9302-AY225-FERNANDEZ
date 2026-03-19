import java.util.Scanner;

public class javasourcefile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Input Collection
        System.out.print("Enter attendance (0-100): ");
        double attendance = scanner.nextDouble();
        
        System.out.print("Enter Lab Work 1 grade (0-100): ");
        double lab1 = scanner.nextDouble();
        
        System.out.print("Enter Lab Work 2 grade (0-100): ");
        double lab2 = scanner.nextDouble();
        
        System.out.print("Enter Lab Work 3 grade (0-100): ");
        double lab3 = scanner.nextDouble();
        
        // Calculations
        double labWorkAverage = (lab1 + lab2 + lab3) / 3;
        double classStanding = (attendance * 0.40) + (labWorkAverage * 0.60);
        
        // Calculate required Prelim Exam score for passing (75) and excellent (100)
        double requiredPrelimForPassing = (75 - (classStanding * 0.30)) / 0.70;
        double requiredPrelimForExcellent = (100 - (classStanding * 0.30)) / 0.70;
        
        // Output Results
        System.out.println("\n--- Results ---");
        System.out.printf("Attendance: %.2f\n", attendance);
        System.out.printf("Lab Work 1 Grade: %.2f\n", lab1);
        System.out.printf("Lab Work 2 Grade: %.2f\n", lab2);
        System.out.printf("Lab Work 3 Grade: %.2f\n", lab3);
        System.out.printf("Lab Work Average: %.2f\n", labWorkAverage);
        System.out.printf("Class Standing: %.2f\n", classStanding);
        
        System.out.printf("Required Prelim Exam Score to Pass (75): %.2f\n", requiredPrelimForPassing);
        System.out.printf("Required Prelim Exam Score for Excellent (100): %.2f\n", requiredPrelimForExcellent);
        
        // Remarks
        if (requiredPrelimForPassing <= 100) {
            System.out.println("You can pass if you achieve this Prelim score.");
        } else {
            System.out.println("It is not possible to pass based on current scores.");
        }

        if (requiredPrelimForExcellent <= 100) {
            System.out.println("You can achieve Excellent standing with this Prelim score.");
        } else {
            System.out.println("It is not possible to achieve Excellent standing based on current scores.");
        }
        
        scanner.close();
    }
}
