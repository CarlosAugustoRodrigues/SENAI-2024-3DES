package senai.projetofinal.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projetofinal.sisur.entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
