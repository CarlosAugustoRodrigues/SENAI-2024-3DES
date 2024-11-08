package senai.projeto.sisur.dtos.loginUsuarios.funcionario;

import senai.projeto.sisur.entities.usuarios.funcionario.UsuarioFuncionario;

public record LoginUsuarioFuncionarioResDTO(UsuarioFuncionario funcionario, String token) {
}
