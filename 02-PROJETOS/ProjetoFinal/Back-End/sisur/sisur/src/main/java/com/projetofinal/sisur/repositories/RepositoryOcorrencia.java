package com.projetofinal.sisur.repositories;

import com.projetofinal.sisur.entities.ocorrencia.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryOcorrencia extends JpaRepository<Ocorrencia, UUID> {
}
