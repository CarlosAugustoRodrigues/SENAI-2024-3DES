package com.projetofinal.sisur.dtos.ocorrencia;

import com.projetofinal.sisur.entities.ocorrencia.Ocorrencia;
import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record OcorrenciaResRecDTO(
        UUID id,
        String descricao,
        String rua,
        String bairro,
        String cep,
        String imagem,
        String tipoImagem,
        LocalTime hora,
        LocalDate data,
        String status,
        String categoria,
        UsuarioComum usuario
) {

    public OcorrenciaResRecDTO(Ocorrencia data) {
        this(
                data.getId(),
                data.getDescricao(),
                data.getRua(),
                data.getBairro(),
                data.getCep(),
                data.getImagem(),
                data.getTipoImagem(),
                data.getHora(),
                data.getData(),
                data.getStatus(),
                data.getCategoria(),
                data.getUsuario()
        );
    }
}
