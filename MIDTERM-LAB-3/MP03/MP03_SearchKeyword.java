package MP03;

import java.io.*;
import java.util.*;

public class MP03_SearchKeyword {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Prompt user for file path
        System.out.print("Enter CSV file path: ");
        String filePath = sc.nextLine();
        
        System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine().toLowerCase();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;
            
            System.out.println("\n--- Search Results ---");
            while ((line = br.readLine()) != null) {
                // Logic: Check if line contains keyword
                if (line.toLowerCase().contains(keyword)) {
                    System.out.println(line);
                    found = true;
                }
            }
            if (!found) System.out.println("No matches found.");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            sc.close(); // scanner closed here to prevent resource leak
        }
    }
}