const fs = require("fs");
const path = require("path");
const readline = require("readline");
const crypto = require("crypto");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.on("line", (line) => {
  if (line.indexOf("mit hash ") === 0) {
    const directoryPath = line.split("mit hash ")[1];
    const files = fs.readdirSync(directoryPath);
    console.log(files);

    files.forEach((file) => {
      const filePath = path.join(directoryPath, file);
      const fileData = fs.readFileSync(filePath);
      const hash = crypto.createHash("sha256").update(fileData).digest("hex");
      console.log(`${file} = ${hash}`);
    });

    rl.close();
  }
});
