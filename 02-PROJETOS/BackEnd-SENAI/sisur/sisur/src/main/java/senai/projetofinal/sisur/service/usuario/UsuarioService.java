package senai.projetofinal.sisur.service.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senai.projetofinal.sisur.config.security.Token;
import senai.projetofinal.sisur.dtos.usuario.LoginUsuarioRequest;
import senai.projetofinal.sisur.dtos.usuario.LoginUsuarioResponse;
import senai.projetofinal.sisur.dtos.usuario.UsuarioRequest;
import senai.projetofinal.sisur.dtos.usuario.UsuarioRequestSenha;
import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.repositories.UsuarioRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;
    PasswordEncoder passwordEncoder;
    Token tokenService;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          Token tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    // Service login usuário
    public ResponseEntity loginUsuario(LoginUsuarioRequest data) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(data.email());

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado!");
        }

        if (data.email().equals(usuario.get().getEmail()) && passwordEncoder.matches(data.senha(), usuario.get().getPassword())) {
            String token = tokenService.generateTokenUsuario(usuario.get());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new LoginUsuarioResponse(usuario.get(), token));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("E-mail e/ou Senha inválidos!");
    }

    // Service criar conta usuário
    public ResponseEntity<Object> create(UsuarioRequest data) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(data.email());

        if (usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("E-mail já cadastrado!");
        }

        if (data.senha() == null || data.senha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Senha não pode ser nula ou vazia!");
        }

        var novoUsuario = new Usuario(data);
        novoUsuario.setSenha(passwordEncoder.encode(data.senha()));
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Service alterar senha usuário - FUNCIONARIO e USUARIO pode utilizar esse service
    public ResponseEntity<Object> updateSenha(String email, UsuarioRequestSenha data) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado!");
        }

        if (data.senha() == null || data.senha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Senha não pode ser nula ou vazia!");
        }

        usuario.get().setSenha(passwordEncoder.encode(data.senha()));
        usuarioRepository.save(usuario.get());
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuario.get());
    }
}
