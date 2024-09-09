package com.projetofinal.sisur.dtos.funcionario;

import com.projetofinal.sisur.entities.funcionario.Funcionario;
import com.projetofinal.sisur.enums.Role;
import com.projetofinal.sisur.enums.Setores;

import java.util.UUID;

public record FuncionarioResRecDTO(
        UUID id,
        String nome,
        Setores setor,
        Role role,
        String email,
        String senha
) {
    public FuncionarioResRecDTO(Funcionario data) {
        this(
                data.getId(),
                data.getNome(),
                data.getSetor(),
                data.getRole(),
                data.getEmail(),
                data.getSenha()
        );
    }
}
