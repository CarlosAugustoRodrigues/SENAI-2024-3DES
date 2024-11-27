package senai.projetofinal.sisur.dtos.ocorrencia;

import senai.projetofinal.sisur.enums.Setor;
import senai.projetofinal.sisur.enums.Status;

import javax.management.monitor.StringMonitor;
import java.time.Instant;
import java.util.Set;

public record OcorrenciaRequest(
        String descricao,
        String rua,
        String bairro,
        String cep,
        Instant data,
        String status,
        String setor,
        Long usuario
) {
}
