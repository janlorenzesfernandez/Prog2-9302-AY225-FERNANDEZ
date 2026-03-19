const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

rl.question('Enter CSV file path: ', (filePath) => {
    try {
        const data = fs.readFileSync(filePath, 'utf8');
        // Split by newline and filter out empty strings
        const rows = data.split('\n').filter(row => row.trim() !== "");
        
        // Logic: Total rows minus 1 for the header
        const validCount = rows.length > 0 ? rows.length - 1 : 0;
        console.log(`Total valid data rows: ${validCount}`);
    } catch (err) {
        console.error("Error:", err.message);
    }
    rl.close();
});