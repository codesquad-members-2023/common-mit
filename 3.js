const fs = require("fs");
const path = require("path");
const readline = require("readline");
const zlib = require("zlib");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});

rl.on("line", (input) => {
    const [command, directoryPath] = input.split(" ");

    if (command === "mit" && directoryPath === "zlib") {
        const targetPath = path.resolve(process.cwd(), input.split(" ").slice(2).join(" "));
        const files = fs.readdirSync(targetPath);

        files.forEach((file) => {
            const filePath = path.join(targetPath, file);
            const stats = fs.statSync(filePath);

            if (stats.isFile()) {
                const readStream = fs.createReadStream(filePath);
                const writeStream = fs.createWriteStream(`${filePath}.z`);
                const gzip = zlib.createGzip();
                readStream.pipe(gzip).pipe(writeStream);
            }
        });

        rl.close();
    }
});

console.log('탐색을 원하는 디렉토리의 경로를 "mit zlip [디렉토리 경로]" 명령어와 같이 입력해주세요');
