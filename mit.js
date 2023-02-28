const fs = require("fs");
const path = require("path");
const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
rl.setPrompt('> ');
rl.prompt();
rl.on('line', line => {
  const [mit, command, directoryPath] = line.split(' ');
  if(command === 'list') {
    const files = fs.readdirSync(directoryPath);
    for(const file of files) {
      const filePath = path.resolve(directoryPath, file);
      const stats = fs.statSync(filePath);
      const fileSize = (stats.size / 1000).toFixed(2);
      console.log(`${file} ${fileSize}KB`);
    }
  }
  rl.close();
});