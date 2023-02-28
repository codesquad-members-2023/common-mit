const readline = require('readline');
const { list } = require('./list');
const { hash } = require('./hash');

const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});

rl.setPrompt('> ');
rl.prompt();
rl.on('line', line => {
	line = line.trim();
	if (line === 'exit') {
		rl.close();
		return;
	}
	const cmd = line.split(' ');
	if (cmd[0] === 'mit' && cmd[1] === 'list') {
		list(cmd[2]);
	} else if (cmd[0] === 'mit' && cmd[1] === 'hash') {
		hash(cmd[2]);
	} else {
		console.log('올바른 명령어를 입력해주세요.');
	}
	rl.prompt();
});

rl.on('close', () => {
	process.exit();
});
