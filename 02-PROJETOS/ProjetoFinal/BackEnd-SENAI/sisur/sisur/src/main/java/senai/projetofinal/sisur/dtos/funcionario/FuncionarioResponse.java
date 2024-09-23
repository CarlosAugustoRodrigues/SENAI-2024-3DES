package senai.projetofinal.sisur.dtos.funcionario;

import senai.projetofinal.sisur.entities.Funcionario;
import senai.projetofinal.sisur.enums.Role;
import senai.projetofinal.sisur.enums.Setor;

import java.util.UUID;

public record FuncionarioResponse(
        UUID id,
        String nome,
        String email,
        String senha,
        Role role,
        Setor setor
) {

    public FuncionarioResponse(Funcionario data) {
        this(
                data.getId(),
                data.getNome(),
                data.getEmail(),
                data.getSenha(),
                data.getRole(),
                data.getSetor()
        );
    }
}
