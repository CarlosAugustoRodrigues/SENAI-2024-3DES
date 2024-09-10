package com.projetofinal.sisur.dtos.ocorrencia;

import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;
import com.projetofinal.sisur.enums.Categoria;

public record OcorrenciaReqRecDTO(
        String descricao,
        String rua,
        String bairro,
        String cep,
        Byte imagem,
        String tipoImagem,
        Categoria categoria,
        UsuarioComum usuario
) {
}
