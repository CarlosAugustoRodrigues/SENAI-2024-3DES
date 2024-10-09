package senai.atividade.fabricaautomoveis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.atividade.fabricaautomoveis.entities.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
