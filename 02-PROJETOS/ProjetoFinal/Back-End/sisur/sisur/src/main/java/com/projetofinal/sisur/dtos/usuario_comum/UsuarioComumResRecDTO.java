package com.projetofinal.sisur.dtos.usuario_comum;

import com.projetofinal.sisur.entities.ocorrencia.Ocorrencia;
import com.projetofinal.sisur.enums.Role;

import java.util.Set;
import java.util.UUID;

public record UsuarioComumResRecDTO(
        UUID id,
        String nome,
        Role role,
        String email,
        String senha,
        Set<Ocorrencia> listaOcorrencia
) {
}
