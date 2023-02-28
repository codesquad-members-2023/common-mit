import * as readline from "node:readline/promises";
import { stdin as input, stdout as output } from "node:process";

class CommandLine {
  constructor(mit) {
    this.rl = readline.createInterface({ input, output });
    this.mit = mit;
  }

  async start() {
    this.rl.setPrompt("> ");
    this.rl.prompt();

    for await (const input of this.rl) {
      await this.relayCommand(input);
      console.log("\n");
      this.rl.prompt();
    }
  }

  end() {
    this.rl.close();
  }

  async relayCommand(command) {
    const [mit, cmd, dirname] = command.split(" ");

    if (mit === "quit") {
      console.log("Quitting program");
      this.end();
    }
    if (mit !== "mit") {
      console.log(`Command not found: ${mit}`);
      return;
    }

    switch (cmd) {
      case "list":
        try {
          (await this.mit.list(dirname)).forEach(({ name, size }) => {
            console.log(`${name} ${size}`);
          });
        } catch (err) {
          console.log(err);
        }
        break;
      case "hash":
        try {
          (await this.mit.hash(dirname)).forEach(({ name, hash }) => {
            console.log(`${name} = ${hash}`);
          });
        } catch (err) {
          console.log(err);
        }
        break;
      case "zlib":
        try {
          (await this.mit.zlib(dirname)).forEach(({ name, size }) => {
            console.log(`${name} ${size}`);
          });
        } catch (err) {
          console.log(err);
        }
        break;
      default:
        console.log(`Command not found: mit ${cmd}`);
        break;
    }
  }
}

export default CommandLine;
