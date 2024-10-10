package senai.atividade.sisturmaseatividades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senai.atividade.sisturmaseatividades.entities.Turma;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    @Query("SELECT t FROM Turma t WHERE t.professor.id = :id")
    List<Turma> findByProfessor(@Param("id") Long id);
}
