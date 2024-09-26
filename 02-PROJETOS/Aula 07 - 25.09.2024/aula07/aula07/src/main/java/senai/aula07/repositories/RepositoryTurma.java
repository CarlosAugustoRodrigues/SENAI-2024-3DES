package senai.aula07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.aula07.entities.Turma;

public interface RepositoryTurma extends JpaRepository<Turma, Long> {
}
