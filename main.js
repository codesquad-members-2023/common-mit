import { mitList } from "./list.js";
import { mitHash } from "./hash.js";
import { mitZlib } from "./zlib.js";
import readline from 'readline';

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

rl.question('명령어를 입력하세요. \nex)mit list 경로\n-> ', (commands) => {
  const command = commands.split(' ')[1]
  const path = commands.split(' ')[2]
  if (command === 'list') {
    mitList(path);
  } else if (command === 'hash') {
    mitHash(path);
  } else if (command === 'zlib') {
    mitZlib(path);
  } else {
    console.log('명령어가 올바르지 않습니다.')
  }
  rl.close();
});