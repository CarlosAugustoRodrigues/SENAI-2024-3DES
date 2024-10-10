package senai.atividade.sisturmaseatividades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senai.atividade.sisturmaseatividades.entities.Atividade;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    @Query("SELECT a FROM Atividade a WHERE a.turma.id = :id")
    List<Atividade> findByTurma(@Param("id") Long id);
}
