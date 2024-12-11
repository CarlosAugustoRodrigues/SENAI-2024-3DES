const express = require('express');
const fileUpload = require('express-fileupload');
const cors = require('cors');
const routes = require('./source/routes');
const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());
app.use(fileUpload());
app.use('/uploads', express.static('uploads'));

app.use(routes);

app.listen(PORT, () => {
    console.log('Server running in PORT: ' + PORT);
});