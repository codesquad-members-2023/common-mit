import crypto from 'crypto';
import fs from 'fs';
import path from 'path';

// hoonding 코드 참고
function mitHash(dirPath) {
  // 디렉토리 내의 파일 목록 읽어오기
  const files = fs.readdirSync(dirPath);

  // 각 파일의 해시 값 출력
  files.forEach((file) => {

    // 파일 경로 생성
    const filePath = path.resolve(dirPath, file);

      // sha256 해시 생성
    if (path.extname(file) === '.js') {
      const hash = crypto.createHash("sha256").update(filePath).digest("hex");

      console.log(`${file} : ${hash}`);
    }
  });
}

export { mitHash };