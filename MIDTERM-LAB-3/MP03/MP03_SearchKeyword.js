const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

// Prompt for file path
rl.question('Enter CSV file path: ', (filePath) => {
    rl.question('Enter keyword to search: ', (keyword) => {
        try {
            // Read file content
            const data = fs.readFileSync(filePath, 'utf8');
            const rows = data.split('\n');
            
            console.log('\n--- Search Results ---');
            // Logic: Filter rows that include the keyword
            const results = rows.filter(row => row.toLowerCase().includes(keyword.toLowerCase()));
            
            results.forEach(row => console.log(row));
            if (results.length === 0) console.log("No matches found.");
        } catch (err) {
            console.error("Error reading file:", err.message);
        }
        rl.close();
    });
});