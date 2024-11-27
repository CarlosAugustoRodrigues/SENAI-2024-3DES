package senai.projetofinal.sisur.dtos.ocorrencia;

import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.enums.Setor;
import senai.projetofinal.sisur.enums.Status;

import javax.management.InstanceNotFoundException;
import java.time.Instant;
import java.util.Set;

public record OcorrenciaResponse(
        Long id,
        Set<String> imagens,
        String rua,
        String bairro,
        String cep,
        Instant data,
        Status status,
        Setor setor,
        Usuario usuario
) {
    public OcorrenciaResponse(Ocorrencia ocorrencia) {
        this (
                ocorrencia.getId(),
                ocorrencia.getImagens(),
                ocorrencia.getRua(),
                ocorrencia.getBairro(),
                ocorrencia.getCep(),
                ocorrencia.getData(),
                ocorrencia.getStatus(),
                ocorrencia.getSetor(),
                ocorrencia.getUsuario()
        );
    }
}
