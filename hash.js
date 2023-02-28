const fs = require('fs/promises');
const path = require('path');
const os = require('os');
const crypto = require('crypto');

const hash = async dirPath => {
	try {
		const realPath = path.join(os.homedir(), dirPath);
		const filelist = await fs.readdir(realPath);
		for (const file of filelist) {
			const buff = await fs.readFile(`${realPath}/${file}`);
			const hash = crypto.createHash('sha256').update(buff).digest('hex');
			console.log(`${file} = ${hash}`);
		}
	} catch (err) {
		console.log(err);
	}
};

module.exports = { hash };
