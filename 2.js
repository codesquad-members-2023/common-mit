const fs = require("fs");
const path = require("path");
const readline = require("readline");
const crypto = require("crypto");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

rl.on("line", (input) => {
    const [command, directoryPath] = input.split(" ");

    if (command === "mit" && directoryPath === "hash") {
        const targetPath = path.resolve(process.cwd(), input.split(" ").slice(2).join(" "));
        const files = fs.readdirSync(targetPath);

        files.forEach((file) => {
            const filePath = path.join(targetPath, file);
            const stats = fs.statSync(filePath);

            if (stats.isFile()) {
                const hash = crypto.createHash("sha256");
                const input = fs.readFileSync(filePath);
                hash.update(input);
                console.log(`${file} ${hash.digest("hex")}`);
            }
        });

        rl.close();
    }
});

console.log('탐색을 원하는 디렉토리의 경로를 "mit hash [디렉토리 경로]" 명령어와 같이 입력해주세요');
