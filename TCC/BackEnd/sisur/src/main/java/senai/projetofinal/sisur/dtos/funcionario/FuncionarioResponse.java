package senai.projetofinal.sisur.dtos.funcionario;

import senai.projetofinal.sisur.entities.ContaFuncionario;
import senai.projetofinal.sisur.entities.Funcionario;
import senai.projetofinal.sisur.enums.Role;

import java.util.Set;

public record FuncionarioResponse(
        Long idContaFuncionario,
        String email,
        String password,
        Set<Role> roles,
        Funcionario funcionario
) {

    public FuncionarioResponse(ContaFuncionario contaFuncionario) {
        this(
                contaFuncionario.getId(),
                contaFuncionario.getEmail(),
                contaFuncionario.getPassword(),
                contaFuncionario.getRoles(),
                contaFuncionario.getFuncionario()
        );
    }
}
