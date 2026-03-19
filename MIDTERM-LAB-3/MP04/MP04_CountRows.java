package MP04;

import java.io.*;
import java.util.Scanner;

public class MP04_CountRows {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter CSV file path: ");
        String filePath = sc.nextLine();

        int rowCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header row
            
            while ((line = br.readLine()) != null) {
                // Logic: Only count non-empty lines
                if (!line.trim().isEmpty()) {
                    rowCount++;
                }
            }
            System.out.println("Total valid data rows: " + rowCount);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
             sc.close();
        }
    }
}
