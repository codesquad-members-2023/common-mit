const readline = require("readline");

function mitCommand(input) {
  const command = input.split(" ").filter((_, index) => index === 1);

  switch (command) {
    case "list":
      break;
    case "hash":
      break;
    case "zlib":
      break;
  }
}

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question("$ ", (input) => {
  console.log(input);
  rl.close();
});
