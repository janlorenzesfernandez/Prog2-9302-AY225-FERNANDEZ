import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MonthlyPerformanceAnalyzer extends JFrame {

    private JButton analyzeButton;
    private JLabel fileLabel;
    private JLabel errorLabel;
    private JLabel bestMonthLabel;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private File selectedFile;

    public MonthlyPerformanceAnalyzer() {
        setTitle("Monthly Performance Analyzer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(244, 246, 249));

        // --- Top Panel ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel("Monthly Performance Analyzer");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(44, 62, 80));

        fileLabel = new JLabel("No file selected");
        fileLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        fileLabel.setForeground(Color.GRAY);

        JButton browseButton = new JButton("Browse CSV");
        styleButton(browseButton);
        browseButton.addActionListener(e -> browseFile());

        analyzeButton = new JButton("Analyze");
        styleButton(analyzeButton);
        analyzeButton.addActionListener(e -> processFile());

        topPanel.add(titleLabel);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(fileLabel);
        topPanel.add(browseButton);
        topPanel.add(analyzeButton);

        // --- Error Label ---
        errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));

        // --- Table ---
        tableModel = new DefaultTableModel(new String[]{"Month", "Total Sales"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultsTable = new JTable(tableModel);
        resultsTable.setFont(new Font("Arial", Font.PLAIN, 13));
        resultsTable.setRowHeight(28);
        resultsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        resultsTable.getTableHeader().setBackground(new Color(44, 62, 80));
        resultsTable.getTableHeader().setForeground(Color.WHITE);
        resultsTable.setSelectionBackground(new Color(189, 215, 238));
        resultsTable.setGridColor(new Color(221, 221, 221));

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // --- Best Month Label ---
        bestMonthLabel = new JLabel(" ");
        bestMonthLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bestMonthLabel.setForeground(new Color(0, 128, 0));
        bestMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bestMonthLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 15, 10));

        // --- Layout ---
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(errorLabel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(bestMonthLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 13));
        button.setBackground(new Color(44, 62, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 32));
    }

    private void browseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            fileLabel.setText(selectedFile.getName());
            errorLabel.setText(" ");
        }
    }

    private void processFile() {
        errorLabel.setText(" ");
        tableModel.setRowCount(0);
        bestMonthLabel.setText(" ");

        if (selectedFile == null) {
            errorLabel.setText("Please select a CSV file.");
            return;
        }

        if (!selectedFile.getName().toLowerCase().endsWith(".csv")) {
            errorLabel.setText("File must be in CSV format.");
            return;
        }

        try {
            Map<String, Double> totals = new TreeMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                boolean firstLine = true;

                while ((line = br.readLine()) != null) {
                    if (firstLine) {
                        firstLine = false;
                        continue; // skip header
                    }

                    line = line.trim();
                    if (line.isEmpty()) continue;

                    String[] parts = splitCSVLine(line);
                    if (parts.length < 13) continue;

                    String dateStr = parts[12].trim();
                    String salesStr = parts[7].trim();

                    if (dateStr.isEmpty()) continue;

                    Date parsedDate;
                    try {
                        parsedDate = sdf.parse(dateStr);
                    } catch (ParseException e) {
                        continue; // skip invalid dates
                    }

                    double sales;
                    try {
                        sales = Double.parseDouble(salesStr);
                    } catch (NumberFormatException e) {
                        continue; // skip invalid sales
                    }

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(parsedDate);
                    String month = cal.get(Calendar.YEAR) + "-" +
                            String.format("%02d", cal.get(Calendar.MONTH) + 1);

                    totals.merge(month, sales, Double::sum);
                }
            }

            if (totals.isEmpty()) {
                errorLabel.setText("No valid data found.");
                return;
            }

            String bestMonth = null;
            double highestSales = Double.NEGATIVE_INFINITY;

            for (Map.Entry<String, Double> entry : totals.entrySet()) {
                tableModel.addRow(new Object[]{
                        entry.getKey(),
                        String.format("%.2f", entry.getValue())
                });
                if (entry.getValue() > highestSales) {
                    highestSales = entry.getValue();
                    bestMonth = entry.getKey();
                }
            }

            bestMonthLabel.setText("Best Performing Month: " + bestMonth +
                    "  (Sales: " + String.format("%.2f", highestSales) + ")");



        } catch (IOException e) {
            errorLabel.setText("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Splits a CSV line, respecting quoted fields that may contain commas.
     */
    private String[] splitCSVLine(String line) {
        java.util.List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MonthlyPerformanceAnalyzer::new);
    }
}