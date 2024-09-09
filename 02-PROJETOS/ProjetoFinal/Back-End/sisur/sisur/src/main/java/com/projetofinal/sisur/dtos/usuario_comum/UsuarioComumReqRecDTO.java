package com.projetofinal.sisur.dtos.usuario_comum;

import com.projetofinal.sisur.enums.Role;

public record UsuarioComumReqRecDTO(
        String nome,
        Role role,
        String email,
        String senha
) {
}
