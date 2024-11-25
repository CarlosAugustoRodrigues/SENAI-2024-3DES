package senai.projeto.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projeto.sisur.entities.comentario.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
