const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

rl.question('Enter CSV file path: ', (filePath) => {
    rl.question('Enter Column Index to extract (0, 1, 2...): ', (index) => {
        try {
            const data = fs.readFileSync(filePath, 'utf8');
            const rows = data.split('\n').filter(row => row.trim() !== "");
            const colIndex = parseInt(index);

            console.log('\n--- Extracted Values ---');
            rows.forEach(row => {
                // Logic: Split each row and display value at index
                const columns = row.split(',');
                if (columns[colIndex]) {
                    console.log(columns[colIndex].trim());
                }
            });
        } catch (err) {
            console.error("Error:", err.message);
        }
        rl.close();
    });
});