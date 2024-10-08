package senai.atividade.fabricaautomoveis.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import senai.atividade.fabricaautomoveis.entidades.Area;

public interface AreaRepositorio extends JpaRepository<Area, Long> {
}
