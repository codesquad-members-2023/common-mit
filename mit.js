const fs = require('fs');
const readline = require('readline');
const path = require('path');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const isCommand = (input) => /^mit\s(line|hash|zlib)\s/.test(input);
const isExit = (input) => input === 'exit';

const readlineHandler = (input) => {
  try {
    if (isExit(input)) {
      rl.close();
      process.exit();
    }
    if (!isCommand(input)) {
      throw new Error('mit으로 명령어를 다시 입력해주세요.\n');
    }

    const [command, type, dirPath] = input.split(' ');
  } catch (error) {
    console.log(error.message);
  } finally {
    rl.prompt();
  }
};

const mit = () => {
  rl.setPrompt('$ ');
  rl.prompt();
  rl.on('line', readlineHandler.bind(this));
};

mit();
