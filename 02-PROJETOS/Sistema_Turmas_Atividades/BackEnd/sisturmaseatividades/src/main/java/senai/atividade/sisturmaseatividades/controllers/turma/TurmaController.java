package senai.atividade.sisturmaseatividades.controllers.turma;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.atividade.sisturmaseatividades.entities.Professor;
import senai.atividade.sisturmaseatividades.entities.Turma;
import senai.atividade.sisturmaseatividades.repositories.ProfessorRepository;
import senai.atividade.sisturmaseatividades.repositories.TurmaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;

    public TurmaController(ProfessorRepository professorRepository,
                           TurmaRepository turmaRepository) {
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Turma>> readAll() {
        List<Turma> listaTurma = turmaRepository.findAll();

        return ResponseEntity.ok(listaTurma);
    }

    @PostMapping
    public ResponseEntity<Turma> create(
            @RequestBody @Validated String nome,
            @RequestBody @Validated Long professor
    ) {
        var professor0 = professorRepository.findById(professor);

        Integer numeroTurmasProf = professor0.get().getTurmas().size();
        Long numeroNovaTurma = numeroTurmasProf.longValue() + 1;

        var novaTurma = new Turma(numeroNovaTurma, nome, professor0.get());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(turmaRepository.save(novaTurma));
    }

    @DeleteMapping("/{id_prof}/{id_turma}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "id_prof") Long professor,
            @PathVariable(value = "id_turma") Long turma
    ) {
        Optional<Professor> professor0 = professorRepository.findById(professor);
        Optional<Turma> turma0 = turmaRepository.findById(turma);

        if (turma0.isPresent() && professor0.isPresent()) {
            if (turma0.get().getProfessor() == professor0.get()) {
                if (turma0.get().getAtividades().size() == 0) {
                    return ResponseEntity.status(HttpStatus.OK)
                            .body("Turma exclída com sucesso!");
                }

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Turma não pode ser excluída, pois possue uma atividade!");
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Turma não pode ser excluída!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Turma e/ou professor não encontrado!");
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<List<Turma>> readByProfessor(
            @PathVariable(value = "id") Long id
    ) {
        List<Turma> listaTurma = turmaRepository.findByProfessor(id);

        return ResponseEntity.ok(listaTurma);
    }
}
