package senai.atividade.sisturmaseatividades.controllers.atividade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.atividade.sisturmaseatividades.entities.Atividade;
import senai.atividade.sisturmaseatividades.repositories.AtividadeRepository;
import senai.atividade.sisturmaseatividades.repositories.TurmaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

    private final AtividadeRepository atividadeRepository;
    private final TurmaRepository turmaRepository;

    public AtividadeController(AtividadeRepository atividadeRepository,
                               TurmaRepository turmaRepository) {
        this.atividadeRepository = atividadeRepository;
        this.turmaRepository = turmaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Atividade>> readAll() {
        List<Atividade> listaAtividade = atividadeRepository
                .findAll();

        return ResponseEntity.ok(listaAtividade);
    }

    @PostMapping
    public ResponseEntity<Atividade> create(
            @RequestBody @Validated String descricao,
            @RequestBody @Validated Long professor,
            @RequestBody @Validated Long turma
    ) {
        var turma0 = turmaRepository.findById(turma);
        Integer numAtividadeTurma = turma0.get().getAtividades().size();
        Long numNovaAtividade = numAtividadeTurma.longValue() + 1;

        var novaAtividade = new Atividade(descricao, professor, numNovaAtividade, turma0.get());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(atividadeRepository.save(novaAtividade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id) {
        Optional<Atividade> atividade0 = atividadeRepository.findById(id);
        if (atividade0.isPresent()) {
            atividadeRepository.delete(atividade0.get());
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Atividade excluida com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Atividade não encontrada!");
    }

    @GetMapping("/turma/{id}")
    public ResponseEntity<?> readByTurma(@PathVariable(value = "id")Long id) {
        List<Atividade> listaAtividade = atividadeRepository.findByTurma(id);

        return ResponseEntity.ok(listaAtividade);
    }
}
