package senai.aula07.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.aula07.entities.Atividade;

public interface RepositoryAtividade extends JpaRepository<Atividade, Long> {
}
