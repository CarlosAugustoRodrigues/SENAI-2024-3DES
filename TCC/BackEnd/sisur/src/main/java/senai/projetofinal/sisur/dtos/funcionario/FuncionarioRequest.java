package senai.projetofinal.sisur.dtos.funcionario;

import java.util.Set;

public record FuncionarioRequest(
        String nome,
        String setor,
        Set<String> responsabilidades,
        String email,
        String password,
        Set<String> roles
) {
}
