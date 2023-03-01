const fs = require('fs');
const path = require('path');
const os = require('os');
const gzip = require('zlib');

const { bytesToSize } = require('./bytesToSize');

const zlib = async dirPath => {
	try {
		const realPath = path.join(os.homedir(), dirPath);
		const fileList = await fs.promises.readdir(realPath);

		for (const file of fileList) {
			const source = fs.createReadStream(`${realPath}/${file}`);
			const destination = fs.createWriteStream(`${realPath}/${file}.z`);
			source.pipe(gzip.createGzip()).pipe(destination);
		}

		// for (const file of fileList) {
		// 	const stats = await fs.promises.stat(`${realPath}/${file}.z`);
		// 	console.log(`${file} (${bytesToSize(stats.size)})`);
		// }
	} catch (err) {
		console.log(err);
	}
};

module.exports = { zlib };
