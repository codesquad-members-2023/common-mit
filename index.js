const readline = require('readline');
var fs = require('fs');

function run() {
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });

  rl.on('line', userInput => {
    mitCommand(userInput);
  });
}

function mitCommand(inputCommand) {
  const [command, type, directory] = inputCommand.split(' ');

  if (command !== 'mit') return;
  switch (type) {
    case 'list':
      break;

    default:
      break;
  }
}

run();
