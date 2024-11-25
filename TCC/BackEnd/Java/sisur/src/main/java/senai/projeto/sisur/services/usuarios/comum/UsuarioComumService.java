package senai.projeto.sisur.services.usuarios.comum;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senai.projeto.sisur.repositories.LoginUsuarioComumRepository;
import senai.projeto.sisur.repositories.UsuarioComumRepository;

@Service
public class UsuarioComumService {

    private final LoginUsuarioComumRepository loginUsuarioComumRepository;
    private final UsuarioComumRepository usuarioComumRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioComumService(LoginUsuarioComumRepository loginUsuarioComumRepository,
                               UsuarioComumRepository usuarioComumRepository,
                               PasswordEncoder passwordEncoder) {
        this.loginUsuarioComumRepository = loginUsuarioComumRepository;
        this.usuarioComumRepository = usuarioComumRepository;
        this.passwordEncoder = passwordEncoder;
    }


}
