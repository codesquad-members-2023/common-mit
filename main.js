const readline = require("readline");

function mitCommand(input) {
  const [command, directory] = input.split(" ").slice(1);

  switch (command) {
    case "list":
      list(directory);
      break;
    case "hash":
      hash(directory);
      break;
    case "zlib":
      zlib(directory);
      break;
  }
}

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question("$ ", (input) => {
  mitCommand(input);
  rl.close();
});
