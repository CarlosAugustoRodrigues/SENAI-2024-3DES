package com.projetofinal.sisur.dtos.ocorrencia;

import com.projetofinal.sisur.entities.usuario_comum.UsuarioComum;
import com.projetofinal.sisur.enums.Categoria;
import com.projetofinal.sisur.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;

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
