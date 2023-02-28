import fs from 'fs';
import path from 'path';
import zlib from 'zlib';
import { Transform } from 'stream';

function mitZlib(dirPath) {
  // 디렉토리 내의 파일 목록 읽어오기
  const files = fs.readdirSync(dirPath);

  // js 파일만 필터링
  const jsFiles = files.filter(file => path.extname(file) === '.js');

  // 각 파일을 읽어서 압축하고 .z 파일로 저장
  jsFiles.forEach((file) => {
    const filePath = path.join(dirPath, file);
    const stat = fs.statSync(filePath);

    // 디렉토리인 경우 재귀적으로 호출
    if (stat.isDirectory()) {
      mitZlib(filePath);
    } else {
      // 파일인 경우 압축하여 .z 파일로 저장
      const readStream = fs.createReadStream(filePath);
      const writeStream = fs.createWriteStream(filePath + '.z');

      // 압축 스트림 생성
      const gzip = zlib.createGzip();

      // 파일 이름과 크기를 기록하는 Transform 스트림 생성
      const sizeTransform = new Transform({
        transform(chunk, encoding, callback) {
          this.totalSize += chunk.length;
          callback(null, chunk);
        },
        flush(callback) {
          console.log(`${this.fileName}.z ${(this.totalSize / 1024).toFixed(1)}KB`);
          callback();
        }
      });
      sizeTransform.fileName = file;
      sizeTransform.totalSize = 0;

      // 파일을 읽고 압축하여 .z 파일에 쓰기
      readStream.pipe(gzip).pipe(sizeTransform).pipe(writeStream);
    }
  });
}

export { mitZlib };