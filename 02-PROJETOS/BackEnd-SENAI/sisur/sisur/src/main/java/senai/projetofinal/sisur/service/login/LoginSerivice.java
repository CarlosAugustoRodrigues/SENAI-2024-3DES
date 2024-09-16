package senai.projetofinal.sisur.service.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senai.projetofinal.sisur.config.security.Token;
import senai.projetofinal.sisur.dtos.funcionario.LoginFuncionarioRequest;
import senai.projetofinal.sisur.dtos.funcionario.LoginFuncionarioResponse;
import senai.projetofinal.sisur.dtos.usuario.LoginUsuarioRequest;
import senai.projetofinal.sisur.dtos.usuario.LoginUsuarioResponse;
import senai.projetofinal.sisur.entities.Funcionario;
import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.repositories.FuncionarioRepository;
import senai.projetofinal.sisur.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class LoginSerivice {

    private FuncionarioRepository funcionarioRepository;
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private Token tokenSerice;

    public LoginSerivice(FuncionarioRepository funcionarioRepository,
                         UsuarioRepository usuarioRepository,
                         PasswordEncoder passwordEncoder,
                         Token tokenSerice) {
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenSerice = tokenSerice;
    }

    public ResponseEntity loginUsuario(LoginUsuarioRequest data) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(data.email());

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }

        if (data.email().equals(usuario.get().getEmail()) && passwordEncoder.matches(data.senha(), usuario.get().getPassword())) {
            String token = tokenSerice.generateTokenUsuario(usuario.get());

            return ResponseEntity.status(HttpStatus.OK).body(new LoginUsuarioResponse(usuario.get(), token));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail e/ou Senha inválidos!");
    }

    public ResponseEntity loginFuncionario(LoginFuncionarioRequest data) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(data.email());

        if (funcionario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado!");
        }

        if (data.email() == funcionario.get().getUsername() && passwordEncoder.matches(data.senha(), funcionario.get().getPassword())) {
            String token = tokenSerice.generateTokenFuncionario(funcionario.get());

            return ResponseEntity.status(HttpStatus.OK).body(new LoginFuncionarioResponse(funcionario.get(), token));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail e/ou Senha inválidos!");
    }
}
