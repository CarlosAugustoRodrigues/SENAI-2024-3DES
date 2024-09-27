package senai.aula07.controllers.professor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.aula07.dtos.professor.LoginRequest;
import senai.aula07.dtos.professor.ProfessorRequest;
import senai.aula07.dtos.professor.ProfessorResponse;
import senai.aula07.entities.Professor;
import senai.aula07.repositories.RepositoryProfessor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProfessorController {

    private final RepositoryProfessor repositoryProfessor;

    public ProfessorController(RepositoryProfessor repositoryProfessor) {
        this.repositoryProfessor = repositoryProfessor;
    }

    @GetMapping("/professor")
    public ResponseEntity<List<ProfessorResponse>> readAll() {
        List<ProfessorResponse> listaProfessor = repositoryProfessor
                .findAll()
                .stream()
                .map(ProfessorResponse::new)
                .toList();

        return ResponseEntity.ok(listaProfessor);
    }

    @PostMapping("/professor")
    public ResponseEntity<Professor> create(@RequestBody @Validated
                                            ProfessorRequest data) {
        var professor = new Professor(data);
        repositoryProfessor.save(professor);

        return ResponseEntity.status(HttpStatus.CREATED).body(professor);
    }

    @PutMapping("/professor/{email}")
    public ResponseEntity<Object> updateSenha(@PathVariable(value = "email") String email,
                                         @RequestBody @Validated String senha) {

        Optional<Professor> professor0 = repositoryProfessor.findByEmail(email);

        if (professor0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado!");
        }

        var professor = professor0.get();
        professor.setSenha(senha);
        repositoryProfessor.save(professor);

        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }

    @PostMapping("/professor/login")
    public ResponseEntity<Professor> login(@RequestBody @Validated LoginRequest data) {

    }
}
