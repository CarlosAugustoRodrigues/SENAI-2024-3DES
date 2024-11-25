const express = require('express');
const cors = require('cors');
const PORT = 3000;
const app = express();

const corsConfiguration = {
    origin: 'http://localhost:5500',
    methods: 'GET',
    credentials: true,
} 

app.use(cors(corsConfiguration));
app.use(express.json());

app.listen(PORT, () => {
    console.log('Server running on PORT ' + PORT);
});

app.get("/testando", (req, res) => {
    res.send({
        server: 'Funcionando!'
    });
});
