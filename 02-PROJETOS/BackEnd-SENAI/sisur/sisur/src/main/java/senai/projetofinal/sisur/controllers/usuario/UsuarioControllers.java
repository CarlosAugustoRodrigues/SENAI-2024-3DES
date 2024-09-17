package senai.projetofinal.sisur.controllers.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.projetofinal.sisur.dtos.usuario.LoginUsuarioRequest;
import senai.projetofinal.sisur.dtos.usuario.UsuarioRequest;
import senai.projetofinal.sisur.dtos.usuario.UsuarioRequestSenha;
import senai.projetofinal.sisur.service.usuario.UsuarioService;

@RestController
@RequestMapping("/sisur")
public class UsuarioControllers {

    private UsuarioService usuarioService;

    public UsuarioControllers(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuario/login")
    public ResponseEntity login(@RequestBody @Validated LoginUsuarioRequest data) {
        return usuarioService.loginUsuario(data);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Object> create(@RequestBody @Validated UsuarioRequest data) {
        return usuarioService.create(data);
    }

    @PutMapping("/usuario/alterarsenha/{email}")
    public ResponseEntity<Object> updateSenha(@PathVariable(value = "email") String email,
                                              @RequestBody @Validated UsuarioRequestSenha data) {
        return usuarioService.updateSenha(email, data);
    }

}
