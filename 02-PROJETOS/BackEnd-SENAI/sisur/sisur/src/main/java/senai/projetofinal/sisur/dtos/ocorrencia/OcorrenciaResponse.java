package senai.projetofinal.sisur.dtos.ocorrencia;

import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.enums.Setor;

import java.time.Instant;

public record OcorrenciaResponse(
        Long id,
        String descricao,
        String rua,
        String bairro,
        String cep,
        Instant timestamp,
        Setor setor,
        Usuario usuario
) {

    public OcorrenciaResponse(Ocorrencia data) {
        this(
                data.getId(),
                data.getDescricao(),
                data.getRua(),
                data.getBairro(),
                data.getCep(),
                data.getTimestamp(),
                data.getSetor(),
                data.getUsuario()
        );
    }
}
