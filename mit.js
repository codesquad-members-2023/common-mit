const readline = require("readline");
const fs = require("fs");
const path = require("path");
const crypto = require("crypto");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

rl.question("명령어를 입력해주세요.\n", (answer) => {
  const [system, command, directoryPath] = answer.split(" ");

  if (system !== "mit") {
    console.log("mit 명령어를 입력해주세요.\n");

    return;
  }

  switch (command) {
    case "list":
      readDirectory(directoryPath, (files) => {
        files.forEach((file) => {
          const filePath = path.join(directoryPath, file);

          fs.stat(filePath, (err, stats) => {
            if (err) {
              console.error(err);

              return;
            }

            console.log(`${file} ${stats.size}Byte`);
          });
        });
      });

      break;

    case "hash":
      readDirectory(directoryPath, (files) => {
        files.forEach((file) => {
          const filePath = path.join(directoryPath, file);
          const fileData = fs.readFileSync(filePath, { encoding: "utf8" });
          const sha256Hash = crypto.createHash("sha256").update(fileData).digest("hex");

          console.log(`${file} = ${sha256Hash}`);
        });
      });

      break;

    default:
      console.log("정상적인 명령어가 아닙니다.");
  }
});

const readDirectory = (directoryPath, callback) => {
  fs.readdir(directoryPath, (err, files) => {
    if (err) {
      console.error(err);

      return;
    }

    callback(files);
  });
};
