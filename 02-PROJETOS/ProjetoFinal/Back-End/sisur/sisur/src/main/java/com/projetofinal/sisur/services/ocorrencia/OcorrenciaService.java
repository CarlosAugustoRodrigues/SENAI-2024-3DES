package com.projetofinal.sisur.services.ocorrencia;

import com.projetofinal.sisur.dtos.ocorrencia.OcorrenciaReqRecDTO;
import com.projetofinal.sisur.dtos.ocorrencia.OcorrenciaResRecDTO;
import com.projetofinal.sisur.entities.ocorrencia.Ocorrencia;
import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;
import com.projetofinal.sisur.repositories.RepositoryOcorrencia;
import com.projetofinal.sisur.repositories.RepositoryUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OcorrenciaService {

    RepositoryOcorrencia repositoryOcorrencia;
    RepositoryUsuario repositoryUsuario;

    public OcorrenciaService(RepositoryOcorrencia repositoryOcorrencia, RepositoryUsuario repositoryUsuario) {
        this.repositoryOcorrencia = repositoryOcorrencia;
        this.repositoryUsuario = repositoryUsuario;
    }

    // LISTAR TODAS AS OCORRENCIAS
    public ResponseEntity<List<OcorrenciaResRecDTO>> readAll() {
        List<OcorrenciaResRecDTO> listaOcorrencia = repositoryOcorrencia
                .findAll()
                .stream()
                .map(OcorrenciaResRecDTO::new)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(listaOcorrencia);
    }

    // LISTAR TODAS AS OCORRENCIAS PELA CATEGORIA
    public ResponseEntity<List<OcorrenciaResRecDTO>> readByCategoria(String categoria) {
        List<OcorrenciaResRecDTO> listaOcorrencia = repositoryOcorrencia
                .findByCategoria(categoria)
                .stream()
                .map(OcorrenciaResRecDTO::new)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(listaOcorrencia);
    }

    // REGISTRAR OCORRENCIA
    public ResponseEntity<Ocorrencia> create(OcorrenciaReqRecDTO data) {
        var ocorrencia = new Ocorrencia(data);
        ocorrencia.setUsuario(repositoryUsuario.findById(data.usuario()).get());

        repositoryOcorrencia.save(ocorrencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrencia);
    }

    // DELETAR OCORRENCIA
    public ResponseEntity<Object> delete(UUID id) {
        Optional<Ocorrencia> ocorrencia0 = repositoryOcorrencia.findById(id);

        if (ocorrencia0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocorrência não encontrada!");
        }

        repositoryOcorrencia.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Ocorrência excluida com sucesso!");
    }

}
