"use strict";

const fs = require("fs");
const readline = require("readline");

// ─── DataRecord ───────────────────────────────────────────────────────────────

function createDataRecord(date, sales) {
    return { date, sales };
}

function getMonth(record) {
    const parsed = new Date(record.date);
    return (
        parsed.getFullYear() +
        "-" +
        String(parsed.getMonth() + 1).padStart(2, "0")
    );
}

// ─── File Validation ──────────────────────────────────────────────────────────

function validateFile(filePath) {
    if (!filePath.toLowerCase().endsWith(".csv")) {
        return "Invalid format: File must have a .csv extension.";
    }
    if (!fs.existsSync(filePath)) {
        return "File not found: " + filePath;
    }
    try {
        fs.accessSync(filePath, fs.constants.R_OK);
    } catch {
        return "File is not readable: " + filePath;
    }
    return null; // valid
}

// ─── CSV Parsing ──────────────────────────────────────────────────────────────

function splitCSVLine(line) {
    const parts = [];
    let inQuotes = false;
    let current = "";
    for (const c of line) {
        if (c === '"') {
            inQuotes = !inQuotes;
        } else if (c === "," && !inQuotes) {
            parts.push(current);
            current = "";
        } else {
            current += c;
        }
    }
    parts.push(current);
    return parts;
}

function parseCSV(filePath) {
    const text = fs.readFileSync(filePath, "utf8");
    const lines = text.trim().split(/\r?\n/);

    if (lines.length < 2) {
        throw new Error("CSV file is empty or has no data rows.");
    }

    lines.shift(); // remove header

    const records = [];

    lines.forEach((line) => {
        if (!line.trim()) return;

        const parts = splitCSVLine(line);
        if (parts.length < 13) return;

        const date  = parts[12].trim();
        const sales = parseFloat(parts[7]);

        if (!date) return;

        const parsedDate = new Date(date);
        if (isNaN(parsedDate.getTime())) return;
        if (isNaN(sales)) return;

        records.push(createDataRecord(date, sales));
    });

    return records;
}

// ─── Analysis ─────────────────────────────────────────────────────────────────

function groupByMonth(records) {
    const totals = {};
    records.forEach((record) => {
        const month = getMonth(record);
        if (!totals[month]) totals[month] = 0;
        totals[month] += record.sales;
    });
    return totals;
}

function findBestMonth(totals) {
    let bestMonth = null;
    let highestSales = -Infinity;
    for (const [month, sales] of Object.entries(totals)) {
        if (sales > highestSales) {
            highestSales = sales;
            bestMonth = month;
        }
    }
    return { bestMonth, highestSales };
}

// ─── Display ──────────────────────────────────────────────────────────────────

function displayResults(totals) {
    const sortedMonths = Object.keys(totals).sort();
    const { bestMonth, highestSales } = findBestMonth(totals);

    console.log("\n╔══════════════════════════════════════╗");
    console.log("║     Monthly Performance Summary      ║");
    console.log("╠══════════════════════════════════════╣");
    console.log(`║ ${"Month".padEnd(15)} ${"Total Sales".padStart(20)} ║`);
    console.log("╠══════════════════════════════════════╣");

    sortedMonths.forEach((month) => {
        const sales = totals[month].toFixed(2);
        console.log(`║ ${month.padEnd(15)} ${sales.padStart(20)} ║`);
    });

    console.log("╠══════════════════════════════════════╣");
    console.log(`║ Best Month: ${bestMonth.padEnd(10)} ${String(highestSales.toFixed(2)).padStart(14)} ║`);
    console.log("╚══════════════════════════════════════╝\n");
}

// ─── File Path Input Loop ─────────────────────────────────────────────────────

function promptFilePath(rl, callback) {
    rl.question("Enter dataset file path: ", (input) => {
        const filePath = input.trim();
        const error = validateFile(filePath);

        if (error) {
            console.log("  Error: " + error);
            console.log("  Please try again.\n");
            promptFilePath(rl, callback);
        } else {
            callback(filePath);
        }
    });
}

// ─── Main ─────────────────────────────────────────────────────────────────────

function main() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
    });

    console.log("\n==========================================");
    console.log("      Monthly Performance Analyzer        ");
    console.log("==========================================\n");

    promptFilePath(rl, (filePath) => {
        rl.close();

        try {
            const records = parseCSV(filePath);

            if (records.length === 0) {
                console.log("Error: No valid data found in the file.");
                return;
            }

            const totals = groupByMonth(records);
            displayResults(totals);

        } catch (err) {
            console.log("Error processing file: " + err.message);
        }
    });
}

main();
