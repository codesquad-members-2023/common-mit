const fs = require("fs");
const path = require("path");
const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const crypto = require('crypto');
const zlib = require('zlib');

rl.setPrompt('> ');
rl.prompt();
rl.on('line', line => {
  const [mit, command, directoryPath] = line.split(' ');
  if(mit === 'mit') {
    const files = fs.readdirSync(directoryPath);
    switch(command) {
      case 'list' :
        for(const file of files) {
          const filePath = path.resolve(directoryPath, file);
          const stats = fs.statSync(filePath);
          const fileSize = (stats.size / 1000).toFixed(2);
          console.log(`${file} ${fileSize}KB`);
        }
        rl.prompt();
        break;
      case 'hash' :
        for(const file of files) {
          const filePath = path.resolve(directoryPath, file);
          const data = fs.readFileSync(filePath);
          const hash = crypto.createHash("sha256").update(data).digest("hex");
          console.log(`${file} ${hash}`);
        }
        rl.prompt();
        break;
      case 'zlib' :
        for(const file of files) {
          const gzip = zlib.createGzip();
          const filePath = path.resolve(directoryPath, file);
          const inputFile = fs.createReadStream(filePath);
          const outputFile = fs.createWriteStream(filePath+'.z');
          inputFile.pipe(gzip).pipe(outputFile);
        }
        rl.prompt();
        break;
      default : rl.close();
    }
  } else {
    console.error('mit 명령어를 다시 입력하세요');
    rl.prompt();
  }
});