package com.projetofinal.sisur.dtos.usuario_comum;

import com.projetofinal.sisur.enums.Role;

public record UsuarioComumReqRecDTO(
        String nome,
        String email,
        String senha
) {
}
