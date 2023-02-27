const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

const inputCommand = () => {
    console.log("빈 자리는 다음과 같습니다.");
    rl.on("line", (line) => {
      const [command, seatNum] = line.split(" ") 
      switch (command) {
        case "new":
          activateUser();
          break;
        default:
           console.log('명령을 다시 입력해주세요 new / stop number / checkuser / checkseat / quit\n')
      }
    });
  };