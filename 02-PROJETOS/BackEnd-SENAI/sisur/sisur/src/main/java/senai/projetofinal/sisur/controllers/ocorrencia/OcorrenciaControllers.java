package senai.projetofinal.sisur.controllers.ocorrencia;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.projetofinal.sisur.dtos.ocorrencia.OcorrenciaRequest;
import senai.projetofinal.sisur.dtos.ocorrencia.OcorrenciaResponse;
import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.service.ocorrencia.OcorrenciaService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sisur")
public class OcorrenciaControllers {

    OcorrenciaService ocorrenciaService;

    public OcorrenciaControllers(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @GetMapping("/ocorrencia")
    public ResponseEntity<List<OcorrenciaResponse>> readAll() {
        return ocorrenciaService.readAll();
    }

    @PostMapping("/ocorrencia")
    public ResponseEntity<Ocorrencia> create(@RequestBody @Validated OcorrenciaRequest data) {
        return ocorrenciaService.create(data);
    }

    @DeleteMapping("/ocorrencia/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {
        return ocorrenciaService.delete(id);
    }

    @GetMapping("/ocorrencia/{setor}")
    public ResponseEntity<List<OcorrenciaResponse>> readSetor(@PathVariable(value = "setor") String setor) {
        return ocorrenciaService.readSetor(setor);
    }
}
