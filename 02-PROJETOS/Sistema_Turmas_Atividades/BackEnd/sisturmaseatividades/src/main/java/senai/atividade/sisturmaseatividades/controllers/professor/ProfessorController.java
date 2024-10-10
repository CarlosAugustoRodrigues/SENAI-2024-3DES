package senai.atividade.sisturmaseatividades.controllers.professor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.atividade.sisturmaseatividades.entities.Professor;
import senai.atividade.sisturmaseatividades.repositories.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> readAll() {
        List<Professor> listaProfessor = professorRepository.findAll();

        return ResponseEntity.ok(listaProfessor);
    }

    @PostMapping
    public ResponseEntity<Professor> create(
            @RequestBody @Validated String nome,
            @RequestBody @Validated String email,
            @RequestBody @Validated String senha
    ) {
        var novoProfessor = new Professor(nome, email, senha);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(professorRepository.save(novoProfessor));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Validated String email,
            @RequestBody @Validated String senha
    ) {
        Optional<Professor> professor0 = professorRepository.findByEmail(email);

        if (professor0.isPresent()) {
            if (!professor0.get().getSenha().equals(senha)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Senha incorreta!");
            }

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(professor0.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("E-mail não cadastrado!");
    }
}
