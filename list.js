const fs = require('fs');
const path = require('path');
const readline = require('readline');

const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});

rl.on('line', async line => {
	try {
		const inputPath = line.split(' ')[2];
		const realPath = path.resolve(`/Users/silvertae${inputPath}`);
		const filelist = await fs.promises.readdir(realPath);
		for (const file of filelist) {
			const stats = await fs.promises.stat(`${realPath}/${file}`);
			console.log(`${file} (${stats.size})`);
		}
		rl.close();
	} catch (err) {
		console.log(err);
	}
});
