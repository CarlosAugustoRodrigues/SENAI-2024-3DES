package senai.projetofinal.sisur.service.ocorrencia;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import senai.projetofinal.sisur.dtos.ocorrencia.OcorrenciaRequest;
import senai.projetofinal.sisur.dtos.ocorrencia.OcorrenciaResponse;
import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.repositories.OcorrenciaRepository;
import senai.projetofinal.sisur.repositories.UsuarioRepository;

import java.util.List;

@Service
public class OcorrenciaService {

    OcorrenciaRepository ocorrenciaRepository;
    UsuarioRepository usuarioRepository;

    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository,
                             UsuarioRepository usuarioRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Service para pegar todas as ocorrencias registradas
    public ResponseEntity<List<OcorrenciaResponse>> readAll() {
        List<OcorrenciaResponse> listaOcorrencia = ocorrenciaRepository.findAll()
                .stream()
                .map(OcorrenciaResponse::new)
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(listaOcorrencia);
    }

    // Service para USUARIO registrar ocorrencia
    public ResponseEntity<Ocorrencia> create(OcorrenciaRequest data) {
        var usuario = usuarioRepository.findById(data.usuario());
        var novaOcorrencia = new Ocorrencia(data);
        novaOcorrencia.setUsuario(usuario.get());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ocorrenciaRepository.save(novaOcorrencia));
    }

    // Service para USUARIO excluir ocorrencia feita
    public ResponseEntity<Object> delete(Long id) {
        var ocorrencia = ocorrenciaRepository.findById(id);

        if (ocorrencia.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ocorrência não encontrada!");
        }

        ocorrenciaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Ocorrência excluida!");
    }

    // Service para FUNCIONARIO pegar todas ocorrências referente ao setor
    public ResponseEntity<List<OcorrenciaResponse>> readSetor(String setor) {
        String setorUpper = setor.toUpperCase();
        List<OcorrenciaResponse> listaOcorrenciaSetor = ocorrenciaRepository.findBySetor(setorUpper)
                .stream()
                .map(OcorrenciaResponse::new)
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(listaOcorrenciaSetor);
    }
}
