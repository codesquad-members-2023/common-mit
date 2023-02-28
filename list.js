const fs = require('fs/promises');
const path = require('path');

const list = async line => {
	try {
		const inputPath = line.split(' ')[2];
		const realPath = path.resolve(`/Users/silvertae${inputPath}`);
		const filelist = await fs.readdir(realPath);
		for (const file of filelist) {
			const stats = await fs.stat(`${realPath}/${file}`);
			console.log(`${file} (${stats.size})`);
		}
	} catch (err) {
		console.log(err);
	}
};

module.exports = { list };
