import { createHash } from 'node:crypto';
import { createReadStream, createWriteStream } from 'node:fs';
import { readFile, readdir, stat } from 'node:fs/promises';
import { pipeline } from 'node:stream';
import { promisify } from 'node:util';
import { createGzip } from 'node:zlib';
import path from 'path';
import readline from 'readline';

export class App {
  constructor() {
    this.stdio = readline.createInterface({
      input: process.stdin,
      output: process.stdout,
    });
  }

  readCommand() {
    this.stdio.prompt();
    this.stdio.on('line', (input) => {
      const [mit, command, directoryPath] = input.split(' ');
      if (command === 'list') this.list(directoryPath);
      if (command === 'hash') this.hash(directoryPath);
      if (command === 'zlib') this.zlib(directoryPath);
    });
  }

  async list(directoryPath) {
    try {
      const fileList = await readdir(directoryPath);
      fileList.forEach(async (file, index) => {
        const filePath = path.join(directoryPath, file);
        try {
          const stats = await stat(filePath);
          console.log(
            `${index + 1}. ${file} ${(stats.size / 1024).toFixed(2)}KB`
          );
        } catch (err) {
          console.log(`Error getting file stats: ${err}`);
        }
      });
    } catch (err) {
      console.error(err);
    }
  }

  async hash(directoryPath) {
    try {
      const fileList = await readdir(directoryPath);

      fileList.forEach(async (file, index) => {
        const hash = createHash('sha256');
        const filePath = path.join(directoryPath, file);
        const fileData = await readFile(filePath);
        const fileHash = hash.update(fileData).digest('hex');
        console.log(`${index + 1}. ${file} = ${fileHash}`);
      });
    } catch (err) {
      console.error(err);
    }
  }

  async zlib(directoryPath) {
    try {
      const inputFiles = await readdir(directoryPath);
      const outputFiles = await Promise.all(
        inputFiles.map((file, index) => {
          const inputFilePath = path.join(directoryPath, file);
          const outputFilePath = this.compressFile(inputFilePath);
          return outputFilePath;
        })
      );
      outputFiles.forEach(async (file, index) => {
        try {
          const stats = await stat(file);
          console.log(
            `${index + 1}. ${file} ${(stats.size / 1024).toFixed(2)}KB`
          );
        } catch (err) {
          console.log(`Error getting file stats: ${err}`);
        }
      });
    } catch (err) {
      console.error(err);
    }
  }

  async compressFile(filePath) {
    const outputFilePath = `${filePath}.z`;
    const pipe = promisify(pipeline);
    await pipe(
      createReadStream(filePath),
      createGzip(),
      createWriteStream(outputFilePath)
    );
    return outputFilePath;
  }
}
