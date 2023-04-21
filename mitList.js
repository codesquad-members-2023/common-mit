const fs = require("fs");

function list(directory) {
  fs.readdir(directory, (err, files) => {
    if (err) {
      console.error(err);
      return;
    }
    files.forEach((file) => {
      const filePath = `${directory}/${file}`;
      fs.stat(filePath, (err, stats) => {
        if (err) {
          console.error(err);
          return;
        }
        console.log(`${file} (${(stats.size / 1024).toFixed(2)} KB)`);
      });
    });
  });
}

module.exports = { list };
