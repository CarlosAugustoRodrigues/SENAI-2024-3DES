const express = require('express');
const cors = require('cors');
const PORT = process.env.PORT || 3000;
const routes = require('./source/routes');
const app = express();

app.use(cors());
app.use(express.json());
app.use(routes);

app.listen(PORT, () => {
    console.log('Server running in PORT: ' + PORT);
});