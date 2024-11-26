package senai.projetofinal.sisur.enums;

public enum Status {
    PENDENTE("PENDENTE"), // QUANDO A OCORRENCIA FOI REGISTRADA E ESTÁ APENAS EM NOSSO BANCO DE DADOS
    EM_ANALISE("EM_ANALISE"), // QUANDO A OCORRENCIA ESTÁ SENDO ANALISADA POR UM FUNCIONARIO PARA VERIFICAR TEXTOS, IMAGENS, VIABILIDADE
    APROVADA("APROVADA"), // QUANDO A OCORRENCIA FOI APROVADA POR SER VIAVEL, E ESTÁ VISIVEL PARA O FUNCIONARIO DO SETOR E PARA OS CIDADÃOS
    REJEITADA("REJEITADA"), // QUANDO A OCORRENCIA FOI REJEITADO POR SER INVIAVEL E/OU CONTER INFORMAÇÕES INADEQUADAS, COMO TEXTOS E IMAGENS
    ENVIADA_AO_DEPARTAMENTO("ENVIADA_AO_DEPARTAMENTO"), // QUANDO A OCORRENCIA FOI ENVIADA AO DEPARTAMENTO RESPONSAVEL
    EM_DESENVOLVIMENTO("EM_DESENVOLVIMENTO"), // QUANDO A OCORRENCIA ESTIVER EM DESENVOLVIMENTO
    ENCERRADA("ENCERRADA"); // QUANDO A OCORRENCIA FOI ENCERRADA

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
