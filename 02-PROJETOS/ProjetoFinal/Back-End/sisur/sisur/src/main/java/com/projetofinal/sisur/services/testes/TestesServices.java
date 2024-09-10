package com.projetofinal.sisur.services.testes;

import com.projetofinal.sisur.dtos.usuario_comum.UsuarioComumResRecDTO;
import com.projetofinal.sisur.repositories.RepositoryUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestesServices {

    RepositoryUsuario repositoryUsuario;

    public TestesServices(RepositoryUsuario repositoryUsuario) {
        this.repositoryUsuario = repositoryUsuario;
    }

    // VISUALIZAR USUARIO
    public ResponseEntity<List<UsuarioComumResRecDTO>> readUsuario() {
        List<UsuarioComumResRecDTO> listaUsuario = repositoryUsuario
                .findAll()
                .stream()
                .map(UsuarioComumResRecDTO::new)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(listaUsuario);
    }

}
