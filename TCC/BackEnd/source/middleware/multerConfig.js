const multer = require('multer');

const storage = multer.memoryStorage(); // Armazena os arquivos na memória como Buffer
const upload = multer({ storage });

module.exports = upload;