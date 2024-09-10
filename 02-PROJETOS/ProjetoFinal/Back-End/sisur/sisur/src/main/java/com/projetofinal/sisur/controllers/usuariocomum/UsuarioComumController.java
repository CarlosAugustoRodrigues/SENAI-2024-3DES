package com.projetofinal.sisur.controllers.usuariocomum;

import com.projetofinal.sisur.dtos.usuario_comum.UsuarioComumReqRecDTO;
import com.projetofinal.sisur.dtos.usuario_comum.UsuarioComumResRecDTO;
import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;
import com.projetofinal.sisur.services.usuariocomum.UsuarioComumService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UsuarioComumController {

    UsuarioComumService usuarioComumService;

    public UsuarioComumController(UsuarioComumService usuarioComumService) {
        this.usuarioComumService = usuarioComumService;
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioComum> createUsuario(@RequestBody @Validated UsuarioComumReqRecDTO data) {
        return usuarioComumService.createUsuario(data);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Validated UsuarioComumReqRecDTO data) {
        return usuarioComumService.updateUsuario(id, data);
    }

    @PutMapping("/usuario/senha/{email}")
    public ResponseEntity<Object> updateSenha(@PathVariable(value = "email") String email,
                                              @RequestBody @Validated UsuarioComumReqRecDTO data) {
        return usuarioComumService.updateSenha(email, data);
    }
}
