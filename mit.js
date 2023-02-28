const fs = require('fs');
const readline = require('readline');
const crypto = require('crypto');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const isCommand = (input) => /^mit\s(list|hash|zlib)\s.+/.test(input);
const isExit = (input) => input === 'exit';

const printFileList = (dirPath) => {
  const files = fs.readdirSync(dirPath);

  files.forEach((file, index) => {
    const fileSize = Math.floor((fs.statSync(file).size / 1000) * 100) / 100;
    console.log(`${index + 1}.${file} = ${fileSize}KB`);
  });
};

const printFileHash = (dirPath) => {
  const files = fs.readdirSync(dirPath);

  files.forEach((file, index) => {
    const hashSum = crypto.createHash('sha256');
    hashSum.update(file);

    const hex = hashSum.digest('hex');

    console.log(`${index + 1}.${file}: ${hex}`);
  });
};

const readlineHandler = (input) => {
  try {
    if (isExit(input)) {
      rl.close();
      process.exit();
    }
    if (!isCommand(input)) {
      throw new Error('mit으로 명령어를 다시 입력해주세요.');
    }

    const [command, type, dirPath] = input.split(' ');

    switch (type) {
      case 'list':
        printFileList(dirPath);
        break;
      case 'hash':
        printFileHash(dirPath);
        break;

      default:
        break;
    }
  } catch (error) {
    console.log(error.message);
  } finally {
    rl.prompt();
  }
};

const mit = () => {
  rl.setPrompt('\n$ ');
  rl.prompt();
  rl.on('line', readlineHandler.bind(this));
};

mit();
