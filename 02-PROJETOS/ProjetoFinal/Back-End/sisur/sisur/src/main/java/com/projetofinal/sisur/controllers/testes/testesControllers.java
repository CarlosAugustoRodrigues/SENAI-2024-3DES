package com.projetofinal.sisur.controllers.testes;

import com.projetofinal.sisur.dtos.usuario_comum.UsuarioComumResRecDTO;
import com.projetofinal.sisur.services.testes.TestesServices;
import com.projetofinal.sisur.services.usuariocomum.UsuarioComumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class testesControllers {

    UsuarioComumService usuarioComumService;
    TestesServices testesServices;

    public testesControllers(UsuarioComumService usuarioComumService, TestesServices testesServices) {
        this.usuarioComumService = usuarioComumService;
        this.testesServices = testesServices;
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<UsuarioComumResRecDTO>> readAll() {
        return testesServices.readUsuario();
    }

}
