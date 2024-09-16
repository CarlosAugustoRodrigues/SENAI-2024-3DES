package senai.projetofinal.sisur.dtos.ocorrencia;

import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.enums.Setor;

import java.util.UUID;

public record OcorrenciaRequest(
        String descricao,
        String rua,
        String bairro,
        String cep,
        String imagem,
        String setor,
        UUID usuario
) {
}
