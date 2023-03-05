const fs = require('fs/promises');
const path = require('path');
const os = require('os');
const { bytesToSize } = require('./bytesToSize');

const list = async dirPath => {
	try {
		const realPath = path.join(os.homedir(), dirPath);
		const fileList = await fs.readdir(realPath);
		for (const file of fileList) {
			const stats = await fs.stat(`${realPath}/${file}`);
			console.log(`${file} (${bytesToSize(stats.size)})`);
		}
	} catch (err) {
		console.log(err);
	}
};

module.exports = { list };
