package com.projetofinal.sisur.services.ocorrencia;

import com.projetofinal.sisur.repositories.RepositoryOcorrencia;
import org.springframework.stereotype.Service;

@Service
public class OcorrenciaService {

    RepositoryOcorrencia repositoryOcorrencia;

    public OcorrenciaService(RepositoryOcorrencia repositoryOcorrencia) {
        this.repositoryOcorrencia = repositoryOcorrencia;
    }


}
