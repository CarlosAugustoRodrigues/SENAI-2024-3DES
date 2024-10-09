package senai.atividade.fabricaautomoveis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.atividade.fabricaautomoveis.entities.Concessionarias;

@Repository
public interface ConcessionariasRepository extends JpaRepository<Concessionarias, Long> {
}
