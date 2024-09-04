package com.projetofinal.sisur.repositories;

import com.projetofinal.sisur.entities.funcionario.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryFuncionario extends JpaRepository<Funcionario, UUID> {
    Optional<Funcionario> findByEmail(String email);
}
