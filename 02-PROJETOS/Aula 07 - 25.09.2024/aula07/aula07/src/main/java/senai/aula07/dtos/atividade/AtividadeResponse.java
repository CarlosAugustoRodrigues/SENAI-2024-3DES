package senai.aula07.dtos.atividade;

import senai.aula07.entities.Atividade;
import senai.aula07.entities.Turma;

import java.time.Instant;

public record AtividadeResponse(
        Long id,
        String descricao,
        Instant dataInicio,
        Instant dataFim,
        Turma turma
) {

    public AtividadeResponse(Atividade data) {
        this(
                data.getId(),
                data.getDescricao(),
                data.getDataInicio(),
                data.getDataFim(),
                data.getTurma()
        );
    }
}
