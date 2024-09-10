package com.projetofinal.sisur.dtos.usuario_comum;

import com.projetofinal.sisur.entities.ocorrencia.Ocorrencia;
import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;
import com.projetofinal.sisur.enums.Role;
import com.projetofinal.sisur.services.usuariocomum.UsuarioComumService;

import java.util.Set;
import java.util.UUID;

public record UsuarioComumResRecDTO(
        UUID id,
        String nome,
        String role,
        String email,
        String senha,
        Set<Ocorrencia> listaOcorrencia
) {

    public UsuarioComumResRecDTO(UsuarioComum data) {
        this(
                data.getId(),
                data.getNome(),
                data.getRole(),
                data.getEmail(),
                data.getSenha(),
                data.getListaOcorrencia()
        );
    }
}
