package com.projetofinal.sisur.services.usuariocomum;

import com.projetofinal.sisur.dtos.usuariocomum.UsuarioComumNomeEmailReqRecDTO;
import com.projetofinal.sisur.dtos.usuariocomum.UsuarioComumReqRecDTO;
import com.projetofinal.sisur.dtos.usuariocomum.UsuarioComumSenhaReqRecDTO;
import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;
import com.projetofinal.sisur.repositories.RepositoryUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioComumService {
    RepositoryUsuario repositoryUsuario;

    public UsuarioComumService(RepositoryUsuario repositoryUsuario) {
        this.repositoryUsuario = repositoryUsuario;
    }

    // REGISTRAR CONTA
    public ResponseEntity<UsuarioComum> createUsuario(UsuarioComumReqRecDTO data) {
        var novoUsuario = new UsuarioComum(data);
        repositoryUsuario.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // ALTERAR DADOS(NOME E EMAIL) - QUANDO ESTIVER LOGADO NO SISTEMA
    public ResponseEntity<Object> updateUsuario(UUID id, UsuarioComumNomeEmailReqRecDTO data) {
         Optional<UsuarioComum> usuario0 = repositoryUsuario.findById(id);
         if (usuario0.isEmpty()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
         }

         var usuario = usuario0.get();
         usuario.setNome(data.nome());
         usuario.setEmail(data.email());
         repositoryUsuario.save(usuario);

         return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    // ALTERAR SENHA
    public ResponseEntity<Object> updateSenha(String email, UsuarioComumSenhaReqRecDTO data) {
        Optional<UsuarioComum> usuario0 = repositoryUsuario.findByEmail(email);

        if (usuario0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }

        var usuario = usuario0.get();
        usuario.setSenha(data.senha());
        repositoryUsuario.save(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }
}
