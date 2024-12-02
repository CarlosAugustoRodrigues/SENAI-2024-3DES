const express = require('express');
const routes = express.Router();

const comentarioControllers = require('./controllers/comentarioControllers');
const ocorrenciaControllers = require('./controllers/ocorrenciaControllers');
const usuarioControllers = require('./controllers/usuarioControllers');
const funcionarioControllers = require('./controllers/funcionarioControllers');
const mensagemControllers = require('./controllers/mensagemControllers');
const authControllers = require('./controllers/authControllers');
const middleware = require('./middleware/middleware');

routes.get('/', (req, res) => {
    res.send('A API está funcionando!');
});

// ROTAS QUE USUARIOS PODEM UTILIZAR
routes.post("/ocorrencia", middleware.validarAcessoUsuario, ocorrenciaControllers.registrarOcorrencia); // FUNCIONANDO
routes.get("/ocorrencia/usuario/:usuarioId", middleware.validarAcessoUsuario, ocorrenciaControllers.lerOcorrenciaPorUsuario); // FUNCIONANDO
routes.put("/ocorrencia/:usuarioId/:ocorrenciaId", middleware.validarAcessoUsuario, ocorrenciaControllers.excluirOcorrencia); // FUNCIONANDO

routes.post("/comentario", middleware.validarAcessoUsuario, comentarioControllers.registrarComentario); // TESTAR
routes.post("/mensagem/:ocorrenciaId", middleware.validarAcessoUsuario || middleware.validarAcessoFuncionario, mensagemControllers.registrarMensagem); // TESTAR
routes.get("/mensagem/:ocorrenciaId", middleware.validarAcessoUsuario || middleware.validarAcessoFuncionario, mensagemControllers.lerMensagens); // TESTAR



// ROTAS QUE FUNCIONARIOS PODEM UTILIZAR
routes.get()
routes.get()
routes.get()
routes.get()

routes.get()
routes.get()
routes.get()
routes.get()

// ROTAS QUE ADMIN PODE UTILIZAR
routes.get("/funcionario", middleware.validarAcessoAdmin, funcionarioControllers.lerFuncionarios); // FUNCIONANDO
routes.post("/funcionario/registrar", middleware.validarAcessoAdmin, funcionarioControllers.registrarFuncionario); // FUNCIONANDO
routes.put("/funcionario/alterar_senha/:email", middleware.validarAcessoAdmin, funcionarioControllers.alterarSenha); // FUNCIONANDO
routes.get("/ocorrencia/excluidas", middleware.validarAcessoAdmin, ocorrenciaControllers.lerOcorrenciasExcluidas); // FUNCIONANDO



// ROTAS QUE GUEST (QUALQUER PESSOA QUE NÃO ESTÁ LOGADA NO SISTEMA) PODE UTILIZAR
routes.post("/usuario/registrar", usuarioControllers.registrarUsuario); // FUNCIONANDO
routes.post("/login/usuario", authControllers.loginUsuario); // FUNCIONANDO
routes.put("/usuario/alterar_senha/:email", usuarioControllers.alterarSenha); // FUNCIONANDO

routes.post("/login/funcionario", authControllers.loginFuncionario); // FUNCIONANDO

routes.get("/ocorrencia", ocorrenciaControllers.lerOcorrencias); // FUNCIONANDO

routes.get("/usuario/ocorrencia/:ocorrenciaId", ocorrenciaControllers.lerUsuarioPorOcorrencia); // FUNCIONANDO

routes.get("/ocorrencia/encerradas", ocorrenciaControllers.lerOcorrenciasEncerradas); // TESTAR

routes.get("/comentario/:ocorrenciaId", comentarioControllers.lerComentarios); // TESTAR

// ROTAS DE TESTES
routes.get("/usuario", usuarioControllers.lerUsuarios);

module.exports = routes;