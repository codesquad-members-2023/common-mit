import fs from 'fs';

const getSizeOfFiles = (folder) => {
  const files = fs.readdirSync(folder);
  files.forEach((file) => {
    const filePath = `${folder}/${file}`;
    const fileSizeInKB = (fs.statSync(filePath).size / 1024).toFixed(2);
    console.log(`${file}: ${fileSizeInKB} KB`);
  });
};

export { getSizeOfFiles };
