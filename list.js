const fs = require('fs/promises');
const path = require('path');
// 파일 크기 단위를 바꿔주는 함수
function bytesToSize(bytes) {
	const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
	if (bytes === 0) return 'n/a';
	const i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)), 10);
	if (i === 0) return `${bytes} ${sizes[i]}`;
	return `${(bytes / 1024 ** i).toFixed(1)} ${sizes[i]}`;
}

const list = async line => {
	try {
		const inputPath = line.split(' ')[2];
		const realPath = path.resolve(`/Users/silvertae${inputPath}`);
		const filelist = await fs.readdir(realPath);
		for (const file of filelist) {
			const stats = await fs.stat(`${realPath}/${file}`);
			console.log(`${file} (${bytesToSize(stats.size)})`);
		}
	} catch (err) {
		console.log(err);
	}
};

module.exports = { list };
