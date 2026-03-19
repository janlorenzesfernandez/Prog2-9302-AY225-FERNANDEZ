import java.io.*;
import java.util.Scanner;

public class MP05_ExtractColumn {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter CSV file path: ");
        String filePath = sc.nextLine();
        
        System.out.print("Enter Column Index to extract (0, 1, 2...): ");
        int colIndex = sc.nextInt();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("\n--- Extracted Values ---");
            while ((line = br.readLine()) != null) {
                // Logic: Split line by comma and access specific index
                String[] columns = line.split(",");
                if (colIndex < columns.length) {
                    System.out.println(columns[colIndex].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            sc.close();
        }
    }
}