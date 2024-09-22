package senai.projetofinal.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.enums.Setor;

import java.util.List;
import java.util.UUID;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    List<Ocorrencia> findBySetor(Setor setor);

    @Query("SELECT o FROM Ocorrencia o WHERE o.usuario.id = :id")
    List<Ocorrencia> findByUsuario(@Param("id") UUID id);
}
