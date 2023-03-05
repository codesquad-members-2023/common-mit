const fs = require('fs');
const path = require('path');
const os = require('os');
const zlib = require('zlib');
const { pipeline } = require('node:stream');
const { promisify } = require('node:util');
const { bytesToSize } = require('./bytesToSize');

const pipe = promisify(pipeline);
// 노드 util모듈의 promisify 메소드를 사용해서 비동기로 처리하고자 하는 함수를 Promise로 감싸지 않을 수 있다.
async function do_gzip(input, output) {
	const gzip = zlib.createGzip();
	const source = fs.createReadStream(input);
	const destination = fs.createWriteStream(output);
	await pipe(source, gzip, destination);
}

const doZlib = async dirPath => {
	try {
		const realPath = path.join(os.homedir(), dirPath);
		const fileList = await fs.promises.readdir(realPath);
		// .z 확장자명이 아닌 파일들만 압축할 수 있도록 한다.
		const nonCompressedFileList = fileList.filter(file => !file.includes('.z'));
		// do_Gzip을 호출할 때 await를 넣어주어야 프로그램이 순차적으로 실행된다.
		for (const file of nonCompressedFileList) {
			try {
				await do_gzip(`${realPath}/${file}`, `${realPath}/${file}.z`);
			} catch (err) {
				console.log(err);
				process.exitCode = 1;
			}
		}
		// 압축이 끝난 후 해당 디렉토리 내 새로운 파일들을 탐색한 후
		// .z 확장자명을 가진 압축된 파일들만 사이즈를 계산하고 출력한다.
		const newFileList = await fs.promises.readdir(realPath);
		const compressedFileList = newFileList.filter(file => file.includes('.z'));

		for (const file of compressedFileList) {
			const stats = await fs.promises.stat(`${realPath}/${file}`);
			console.log(`${file} (${bytesToSize(stats.size)})`);
		}
	} catch (err) {
		console.log(err);
	}
};

module.exports = { doZlib };
