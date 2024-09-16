package senai.projetofinal.sisur.controllers.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.projetofinal.sisur.dtos.usuario.LoginUsuarioRequest;
import senai.projetofinal.sisur.dtos.usuario.UsuarioRequest;
import senai.projetofinal.sisur.service.login.LoginSerivice;
import senai.projetofinal.sisur.service.usuario.UsuarioService;

@RestController
@RequestMapping("/sisur")
public class UsuarioControllers {

    private LoginSerivice loginSerivice;
    private UsuarioService usuarioService;

    public UsuarioControllers(LoginSerivice loginSerivice,
                              UsuarioService usuarioService) {
        this.loginSerivice = loginSerivice;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario/login")
    public ResponseEntity login(@RequestBody @Validated LoginUsuarioRequest data) {
        return loginSerivice.loginUsuario(data);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Object> create(@RequestBody @Validated UsuarioRequest data) {
        return usuarioService.create(data);
    }
}
