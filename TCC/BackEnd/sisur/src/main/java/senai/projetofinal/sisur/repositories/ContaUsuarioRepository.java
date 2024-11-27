package senai.projetofinal.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projetofinal.sisur.entities.ContaUsuario;

@Repository
public interface ContaUsuarioRepository extends JpaRepository<ContaUsuario, Long> {
}
