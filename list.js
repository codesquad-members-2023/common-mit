import fs from 'fs';
import path from 'path';

// dirPath = 디렉토리 경로
function mitList(dirPath) {

// 디렉토리 내의 파일 목록 읽어오기
  const files = fs.readdirSync(dirPath);

// 각 파일의 크기와 이름 출력
  files.forEach((file) => {
  // 파일 경로 생성
  const filePath = path.join(dirPath, file);

  // 파일 정보 가져오기
  const stat = fs.statSync(filePath);

  // js 파일만 출력
  if (path.extname(file) === '.js') {
    console.log(`${file}: ${(stat.size / 1024).toFixed(1)} KB`);
  }
});
}

export { mitList };