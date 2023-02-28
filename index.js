let fs = require("fs");

const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const showList = (directoryName) => {
  fs.readdir(directoryName, (err, filelist) => {
    if (err) {
      console.log(err);
    } else {
      filelist.forEach((element) => {
        let stats = fs.statSync(element);
        let fileSizeInBytes = Math.round(stats.size * 0.001 * 100) / 100;
        console.log(`${element} ${fileSizeInBytes}KB`);
      });
    }
  });
};

const inputCommand = () => {
  console.log(
    "명령 형식은 mit {기능} {디렉토리명} 입니다 입력형식에 맞지 않는 경우 종료합니다"
  );
  rl.on("line", (line) => {
    const [mit, command, directoryName] = line.split(" ");
    switch (command) {
      case "list":
        showList(directoryName);
        break;
      case "hash":
        // method();
        break;
      case "zlib":
        // method();
        break;
      default:
        rl.close();
        console.log("종료합니다");
    }
  });
};

inputCommand();
