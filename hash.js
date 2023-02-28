const fs = require('fs/promises');
const path = require('path');
const crypto = require('crypto');

const hash = async line => {
	try {
		const inputPath = line.split(' ')[2];
		const realPath = path.resolve(`/Users/silvertae${inputPath}`);
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
