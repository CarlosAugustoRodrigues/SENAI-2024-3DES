package com.projetofinal.sisur.dtos.ocorrencia;

import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;

import java.util.UUID;

public record OcorrenciaReqRecDTO(
        String descricao,
        String rua,
        String bairro,
        String cep,
        String imagem,
        String tipoImagem,
        UUID usuario
) {
}
