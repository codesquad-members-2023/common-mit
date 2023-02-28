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
    
  }
  rl.close();
});