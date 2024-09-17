package senai.projetofinal.sisur.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.enums.Setor;

import java.util.List;
import java.util.Optional;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    List<Ocorrencia> findBySetor(Setor setor);
}
