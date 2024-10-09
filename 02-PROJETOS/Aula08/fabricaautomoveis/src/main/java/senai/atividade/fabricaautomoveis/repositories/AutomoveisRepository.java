package senai.atividade.fabricaautomoveis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.atividade.fabricaautomoveis.entities.Automoveis;

@Repository
public interface AutomoveisRepository extends JpaRepository<Automoveis, Long> {
}
