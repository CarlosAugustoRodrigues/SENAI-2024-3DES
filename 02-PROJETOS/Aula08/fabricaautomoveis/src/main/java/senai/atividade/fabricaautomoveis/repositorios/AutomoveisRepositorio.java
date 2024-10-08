package senai.atividade.fabricaautomoveis.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.atividade.fabricaautomoveis.entidades.Automoveis;

public interface AutomoveisRepositorio extends JpaRepository<Automoveis, Long> {
}
