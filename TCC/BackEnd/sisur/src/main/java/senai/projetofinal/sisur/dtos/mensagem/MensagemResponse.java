package senai.projetofinal.sisur.dtos.mensagem;

import senai.projetofinal.sisur.entities.Mensagem;
import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.enums.Role;

import java.time.Instant;

public record MensagemResponse(
        Long id,
        String mensagem,
        Instant data,
        Ocorrencia ocorrencia,
        Role perfil,
        Long responsavel
) {
    public MensagemResponse (Mensagem mensagem) {
        this(
                mensagem.getId(),
                mensagem.getMensagem(),
                mensagem.getData(),
                mensagem.getOcorrencia(),
                mensagem.getPerfil(),
                mensagem.getResponsavel()
        );
    }
}
