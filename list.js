const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});

rl.on('line', line => {
	const command = line.split(' ');
	const dirName = command[2];
	const path = `../${dirName}`;

	console.log(__dirname);
	console.log(__filename);
	console.log(process.cwd());

	fs.readdir(path, (err, filelist) => {
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
