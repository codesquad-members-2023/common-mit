const fs = require('fs');

const test = '../CS15';

fs.readdir(test, (err, filelist) => {
	if (err) {
		console.log(err);
	} else {
		filelist.forEach(file => {
			console.log(file);
		});
	}
});
