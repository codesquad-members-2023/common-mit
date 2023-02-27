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
      getFileInfo(directory);
      break;

    default:
      break;
  }
}

function getFileInfo(directory) {
  const regex = /^[^.].*[^.]$/;
  const filelist = fs.readdirSync(directory);
  const filteredFiles = filelist.filter(file => regex.test(file));
  filteredFiles.forEach(file => {
    const stats = fs.statSync(`${directory}/${file}`);
    const fileName = file.split('.')[0];
    const fileSize = stats.size;
  });
}

run();
