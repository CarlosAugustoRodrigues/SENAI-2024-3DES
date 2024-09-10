package com.projetofinal.sisur.dtos.ocorrencia;

import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;

public record OcorrenciaReqRecDTO(
        String descricao,
        String rua,
        String bairro,
        String cep,
        Byte imagem,
        String tipoImagem,
        UsuarioComum usuario
) {
}
