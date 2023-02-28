const fs = require('fs');
const path = require('path');
const readline = require('readline');

const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});

rl.on('line', line => {
	const command = line.split(' ');
	const inputPath = command[2];

	const realPath = path.resolve(`/Users/silvertae${inputPath}`);

	fs.readdir(realPath, (err, filelist) => {
		if (err) {
			console.log(err);
		} else {
			filelist.forEach(file => {
				console.log(file);
			});
		}
	});

	rl.close();
});
