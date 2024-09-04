package com.projetofinal.sisur.repositories;

import com.projetofinal.sisur.entities.usuario_comum.UsuarioComum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryUsuario extends JpaRepository<UsuarioComum, UUID> {
    Optional<UsuarioComum> findByEmail(String email);
}