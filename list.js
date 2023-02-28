const fs = require("fs");
const path = require("path");
const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.on("line", (line) => {
  if (line.indexOf("mit list ") === 0) {
    const directoryPath = line.split("mit list ")[1];
    const files = fs.readdirSync(directoryPath);
    // console.log(files);

    files.forEach((file) => {
      const filePath = path.join(directoryPath, file);
      const stats = fs.statSync(filePath);
      const fileSizeInKiloBytes = (stats.size / 1024).toFixed(2);
      console.log(`${file} ${fileSizeInKiloBytes}KB`);
    });

    rl.close();
  }
});
