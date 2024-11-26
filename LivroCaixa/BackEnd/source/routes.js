const express = require('express');
const routes = express.Router();

const usuarioController = require('./controllers/usuario');
const lancamentoController = require('./controllers/lancamento');

routes.get('/usuario', usuarioController.readAll);
routes.get('/usuario/:id', usuarioController.readById);
routes.post('/usuario', usuarioController.create);

routes.get('/lancamento', lancamentoController.readAll);
routes.get('/lancamento/:id', lancamentoController.readById);
routes.post('/lancamento', lancamentoController.create);
routes.put('/lancamento/:id', lancamentoController.update);
routes.delete('/lancamento/:id', lancamentoController.del);

module.exports = routes;