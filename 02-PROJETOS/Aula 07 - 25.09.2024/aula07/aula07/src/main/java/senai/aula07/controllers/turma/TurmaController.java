package senai.aula07.controllers.turma;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.aula07.dtos.turma.TurmaRequest;
import senai.aula07.dtos.turma.TurmaResponse;
import senai.aula07.entities.Turma;
import senai.aula07.repositories.RepositoryProfessor;
import senai.aula07.repositories.RepositoryTurma;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TurmaController {

    private final RepositoryProfessor repositoryProfessor;
    private final RepositoryTurma repositoryTurma;

    public TurmaController(RepositoryProfessor repositoryProfessor,
                           RepositoryTurma repositoryTurma) {
        this.repositoryProfessor = repositoryProfessor;
        this.repositoryTurma = repositoryTurma;
    }

    @GetMapping("/turma")
    public ResponseEntity<List<TurmaResponse>> readAll() {
        List<TurmaResponse> listaTurma = repositoryTurma
                .findAll()
                .stream()
                .map(TurmaResponse::new)
                .toList();

        return ResponseEntity.ok(listaTurma);
    }

    @PostMapping("/turma")
    public ResponseEntity<Turma> create(@RequestBody @Validated
                                        TurmaRequest data) {
        var professor = repositoryProfessor.findById(data.professor());
        Integer numeroTurmas = professor.get().getTurmas().size();
        Long converterNumeroTurmas = numeroTurmas.longValue();

        var turma = new Turma(data);
        turma.setNumero(converterNumeroTurmas + 1);
        turma.setProfessor(professor.get());

    }
}
