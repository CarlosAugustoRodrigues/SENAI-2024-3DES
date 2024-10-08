package senai.atividade.fabricaautomoveis.controlesweb.area;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.atividade.fabricaautomoveis.dtos.area.AreaResponse;
import senai.atividade.fabricaautomoveis.entidades.Area;
import senai.atividade.fabricaautomoveis.repositorios.AreaRepositorio;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AreaControles {

    private final AreaRepositorio areaRepositorio;

    public AreaControles(AreaRepositorio areaRepositorio) {
        this.areaRepositorio = areaRepositorio;
    }

    @GetMapping("/area")
    public ResponseEntity<List<AreaResponse>> readAll() {
        List<AreaResponse> listaArea =
    }


}
