package senai.projetofinal.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projetofinal.sisur.entities.ContaFuncionario;

@Repository
public interface ContaFuncionarioRepository extends JpaRepository<ContaFuncionario, Long> {
}
