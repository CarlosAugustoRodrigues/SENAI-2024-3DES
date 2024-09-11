package com.projetofinal.sisur.controllers.ocorrencia;

import com.projetofinal.sisur.dtos.ocorrencia.OcorrenciaReqRecDTO;
import com.projetofinal.sisur.dtos.ocorrencia.OcorrenciaResRecDTO;
import com.projetofinal.sisur.entities.ocorrencia.Ocorrencia;
import com.projetofinal.sisur.services.ocorrencia.OcorrenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OcorrenciaControllers {

    OcorrenciaService ocorrenciaService;

    public OcorrenciaControllers(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @GetMapping("/ocorrencia")
    public ResponseEntity<List<OcorrenciaResRecDTO>> readAll() {
        return ocorrenciaService.readAll();
    }

    @GetMapping("/ocorrencia/{categoria}")
    public ResponseEntity<List<OcorrenciaResRecDTO>> readByCategoria(@PathVariable(value = "categoria") String categoria) {
        return ocorrenciaService.readByCategoria(categoria);
    }

    @PostMapping("/ocorrencia")
    public ResponseEntity<Ocorrencia> create(@RequestBody @Validated OcorrenciaReqRecDTO data) {
        return ocorrenciaService.create(data);
    }

    @DeleteMapping("/ocorrencia/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")UUID id) {
        return ocorrenciaService.delete(id);
    }
}
