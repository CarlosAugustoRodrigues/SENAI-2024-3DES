package senai.projetofinal.sisur.dtos.funcionario;

import senai.projetofinal.sisur.entities.Funcionario;

public record LoginFuncionarioResponse(Funcionario funcionario, String token) {
}
