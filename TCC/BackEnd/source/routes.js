const express = require('express');
const routes = express.Router();

const comentarioControllers = require('./controllers/comentarioControllers');
const ocorrenciaControllers = require('./controllers/ocorrenciaControllers');
const usuarioControllers = require('./controllers/usuarioControllers');
const funcionarioControllers = require('./controllers/funcionarioControllers');
const mensagemControllers = require('./controllers/mensagemControllers');
const authControllers = require('./controllers/authControllers');
const middleware = require('./middleware/middleware');
const uploadImagem = require('./middleware/multerConfig');

routes.get('/', (req, res) => {
    res.send('A API está funcionando!');
});

// ROTAS QUE USUARIOS PODEM UTILIZAR
routes.post(
    "/ocorrencia", 
    middleware.validarAcessoUsuario,
    ocorrenciaControllers.registrarOcorrencia
); // FUNCIONANDO

routes.get(
    "/ocorrencia/usuario/:usuarioId", 
    middleware.validarAcessoUsuario, 
    ocorrenciaControllers.lerOcorrenciaPorUsuario
); // FUNCIONANDO

routes.put(
    "/ocorrencia/:usuarioId/:ocorrenciaId", 
    middleware.validarAcessoUsuario, 
    ocorrenciaControllers.excluirOcorrencia
); // FUNCIONANDO

routes.post(
    "/comentario", 
    middleware.validarAcessoUsuario, 
    comentarioControllers.registrarComentario
); // TESTAR

routes.post(
    "/mensagem/:ocorrenciaId", 
    middleware.validarAcessoUsuario || middleware.validarAcessoFuncionario, 
    mensagemControllers.registrarMensagem
); // FUNCIONANDO

routes.get(
    "/mensagem/:ocorrenciaId", 
    middleware.validarAcessoUsuario || middleware.validarAcessoFuncionario, 
    mensagemControllers.lerMensagens
); // FUNCIONANDO

routes.put(
    "/usuario/imagem_perfil/:usuarioId",
    middleware.validarAcessoFuncionario,
    usuarioControllers.imagemPerfil
); // TESTAR



// ROTAS QUE FUNCIONARIOS PODEM UTILIZAR
routes.get(
    "/ocorrencia/funcionario/:funcionarioId", 
    middleware.validarAcessoFuncionario, 
    ocorrenciaControllers.lerOcorrenciaPorFuncionario
); // TESTAR

routes.get(
    "/ocorrencia/setor/:funcionarioId/:setor",
    middleware.validarAcessoFuncionario,
    ocorrenciaControllers.lerOcorrenciaPorSetorIntermediador
); // TESTAR

routes.put(
    "/ocorrencia/atribuir/:funcionarioId/:ocorrenciaId",
    middleware.validarAcessoFuncionario,
    ocorrenciaControllers.atribuirOcorrenciaFuncionarioIntermediador
); // TESTAR

routes.put(
    "/ocorrencia/alterar_status/:funcionarioId/:ocorrenciaId",
    middleware.validarAcessoFuncionario,
    ocorrenciaControllers.alterarStatusOcorrenciaIntermediador
); // TESTAR


routes.get(
    "/ocorrencia/registrada/setor/:funcionarioId",
    middleware.validarAcessoFuncionario,
    ocorrenciaControllers.lerOcorrenciaRegistradaSetorAnalista
); // FUNCIONANDO

routes.put(
    "/ocorrencia/registrada/alterar_infos/:funcionarioId/:ocorrenciaId",
    middleware.validarAcessoFuncionario,
    ocorrenciaControllers.alterarInfoOcorrenciaAnalista
); // FUNCIONANDO

routes.put(
    "/ocorrencia/enviar_ocorrencia/:funcionarioId/:ocorrenciaId",
    middleware.validarAcessoFuncionario,
    ocorrenciaControllers.enviarOcorrenciaIntermediador
); // FUNCIONANDO

routes.delete(
    "/ocorrencia/rejeitar/:funcionarioId/:ocorrenciaId",
    middleware.validarAcessoFuncionario,
    ocorrenciaControllers.rejeitarOcorrenciaAnalista
); // FUNCIONANDO


// ROTAS QUE ADMIN PODE UTILIZAR
routes.get(
    "/funcionario", 
    middleware.validarAcessoAdmin, 
    funcionarioControllers.lerFuncionarios
); // FUNCIONANDO

routes.post(
    "/funcionario/registro", 
    middleware.validarAcessoAdmin, 
    funcionarioControllers.registrarFuncionario
); // FUNCIONANDO

routes.put(
    "/funcionario/alterar_senha/:email", 
    middleware.validarAcessoAdmin, 
    funcionarioControllers.alterarSenha
); // FUNCIONANDO

routes.put(
    "/funcionario/imagem_perfil/:funcionarioId",
    middleware.validarAcessoAdmin,
    funcionarioControllers.imagemPerfil
); // TESTAR



// ROTAS QUE GUEST (QUALQUER PESSOA QUE NÃO ESTÁ LOGADA NO SISTEMA) PODE UTILIZAR
routes.post(
    "/usuario/registro", 
    usuarioControllers.registrarUsuario
); // FUNCIONANDO

routes.post(
    "/login/usuario", 
    authControllers.loginUsuario
); // FUNCIONANDO

routes.put(
    "/usuario/alterar_senha/:email", 
    usuarioControllers.alterarSenha
); // FUNCIONANDO

routes.post(
    "/login/funcionario", 
    authControllers.loginFuncionario
); // FUNCIONANDO

routes.get(
    "/ocorrencia", 
    ocorrenciaControllers.lerOcorrencias
); // FUNCIONANDO

routes.get(
    "/usuario/ocorrencia/:ocorrenciaId", 
    ocorrenciaControllers.lerUsuarioPorOcorrencia
); // FUNCIONANDO

routes.get(
    "/ocorrencia/encerradas", 
    ocorrenciaControllers.lerOcorrenciasEncerradas
); // TESTAR

routes.get(
    "/comentario/:ocorrenciaId", 
    comentarioControllers.lerComentarios
); // TESTAR

// ROTAS DE TESTES
routes.get("/usuario", usuarioControllers.lerUsuarios);

module.exports = routes;