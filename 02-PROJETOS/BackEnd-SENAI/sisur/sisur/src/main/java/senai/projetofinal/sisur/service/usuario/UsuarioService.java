package senai.projetofinal.sisur.service.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senai.projetofinal.sisur.dtos.usuario.UsuarioRequest;
import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;
    PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Object> create(UsuarioRequest data) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(data.email());
        System.out.println(data);

        if (usuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado!");
        }

        if (data.senha() == null || data.senha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode ser nula ou vazia!");
        }

        var novoUsuario = new Usuario(data);
        novoUsuario.setSenha(passwordEncoder.encode(data.senha()));
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}
