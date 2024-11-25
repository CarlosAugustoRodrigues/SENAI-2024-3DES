const con = require('mysql').createConnection({
    host: 'localhost',
    user: 'root',
    database: 'projeto_final'
});

module.exports = con;