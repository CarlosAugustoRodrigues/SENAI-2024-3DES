package senai.atividade.fabricaautomoveis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.atividade.fabricaautomoveis.entities.Alocacao;

@Repository
public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {
}
