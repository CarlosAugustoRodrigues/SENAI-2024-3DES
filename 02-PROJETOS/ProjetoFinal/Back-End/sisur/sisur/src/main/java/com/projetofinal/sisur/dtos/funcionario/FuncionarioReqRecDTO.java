package com.projetofinal.sisur.dtos.funcionario;

import com.projetofinal.sisur.enums.Role;
import com.projetofinal.sisur.enums.Setores;

public record FuncionarioReqRecDTO(
        String nome,
        Setores setor,
        Role role,
        String email,
        String senha
) {
}
