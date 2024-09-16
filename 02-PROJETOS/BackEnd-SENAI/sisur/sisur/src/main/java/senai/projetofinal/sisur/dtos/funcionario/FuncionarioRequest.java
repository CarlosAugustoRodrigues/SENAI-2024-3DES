package senai.projetofinal.sisur.dtos.funcionario;

import senai.projetofinal.sisur.enums.Setor;

public record FuncionarioRequest(
        String nome,
        String email,
        String senha,
        String setor
) {
}
