package senai.projetofinal.sisur.dtos.ocorrencia;

import java.util.UUID;

public record OcorrenciaRequest(
        String descricao,
        String rua,
        String bairro,
        String cep,
        String setor,
        UUID usuario
) {
}
