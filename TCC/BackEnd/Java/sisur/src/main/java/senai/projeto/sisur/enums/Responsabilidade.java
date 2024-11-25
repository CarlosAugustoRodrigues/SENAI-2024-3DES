package senai.projeto.sisur.enums;

public enum Responsabilidade {
    ANALISAR("ANALISAR"), // ANALISAR TEXTOS, IMAGENS, VIABILIDADE DAS OCORRENCIAS
    COMUNICACAO("COMUNICACAO"), // MANTER COMUNICACAO COM USUARIO
    ENVIAR_AO_DEPATARMENTO("ENVIAR_AO_DEPATARMENTO"), // ENVIAR AO DEPARTAMENTO RESPONSAVEL
    DESENVOLVER("DESENVOLVER"), // DIZER QUE A OCORRENCIA ESTA EM DESENVOLVIMENTO
    ENCERRAR("ENCERRAR"); // ENCERRAR OCORRENCIA QUANDO JÁ FOI RESOLVIDA

    private String responsabilidade;

    Responsabilidade(String responsabilidade) {
        this.responsabilidade = responsabilidade;
    }

    public String getResponsabilidade() {
        return responsabilidade;
    }
}
