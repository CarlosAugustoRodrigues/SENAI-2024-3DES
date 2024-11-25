package senai.projeto.sisur.services.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senai.projeto.sisur.config.Token;
import senai.projeto.sisur.dtos.loginUsuarios.comum.LoginUsuarioComumReqDTO;
import senai.projeto.sisur.dtos.loginUsuarios.comum.LoginUsuarioComumResDTO;
import senai.projeto.sisur.dtos.loginUsuarios.funcionario.LoginUsuarioFuncionarioReqDTO;
import senai.projeto.sisur.dtos.loginUsuarios.funcionario.LoginUsuarioFuncionarioResDTO;
import senai.projeto.sisur.repositories.LoginUsuarioComumRepository;
import senai.projeto.sisur.repositories.LoginUsuarioFuncionarioRepository;

@Service
public class Auth {

    private final Token token;
    private final LoginUsuarioComumRepository loginUsuarioComumRepository;
    private final LoginUsuarioFuncionarioRepository loginUsuarioFuncionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Auth(Token token,
                LoginUsuarioComumRepository loginUsuarioComumRepository,
                LoginUsuarioFuncionarioRepository loginUsuarioFuncionarioRepository,
                PasswordEncoder passwordEncoder) {
        this.token = token;
        this.loginUsuarioComumRepository = loginUsuarioComumRepository;
        this.loginUsuarioFuncionarioRepository = loginUsuarioFuncionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> loginUsuarioComum (LoginUsuarioComumReqDTO data) {
        var login = loginUsuarioComumRepository.findByEmail(data.email())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

        if (!passwordEncoder.matches(data.password(), login.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("E-mail e/ou Senha inválidos!");
        }

        String tokenUsuario = token.generateTokenComum(login);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginUsuarioComumResDTO(login.getUsuario(), login.getUsername(), tokenUsuario));
    }

    public ResponseEntity<?> loginUsuarioFuncionario (LoginUsuarioFuncionarioReqDTO data) {
        var login = loginUsuarioFuncionarioRepository.findByEmail(data.email())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado!"));

        if (!passwordEncoder.matches(data.pin(), login.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("E-mail e/ou Senha inválidos!");
        }

        String tokenFuncionario = token.generateTokenFuncionario(login);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new LoginUsuarioFuncionarioResDTO(login.getFuncionario(), login.getUsername(), tokenFuncionario));
    }
}
