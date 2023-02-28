import readline from 'readline';
import { getSizeOfFiles } from './mit_list.js';

const folder = '/Users/poco/Documents/CodeSquard/masters/CS/CS16/Work/Masters';
const getCommand = (line) => line.split(' ')[1];

const printResult = (command) => {
  switch (command) {
    case 'list':
      break;
    case 'hash':
      break;
    case 'zlib':
      break;
    default:
      console.error('올바른 명령어 입력해라 ex) list, hash, zlib');
      break;
  }
};

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.setPrompt('> ');
rl.prompt();
rl.on('line', (line) => {
  if (line === 'exit') {
    rl.close();
  }
  const command = getCommand(line);
  printResult(command);
});

rl.on('close', () => {
  console.log('잘가 CS16');
  process.exit();
});
