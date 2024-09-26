package senai.aula07.dtos.atividade;

import java.time.Instant;

public record AtividadeRequest(
        String descricao,
        Instant dataFim,
        Long turma
) {
}
