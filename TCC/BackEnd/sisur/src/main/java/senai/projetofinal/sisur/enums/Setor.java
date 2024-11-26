package senai.projetofinal.sisur.enums;

public enum Setor {
    EDUCACAO("EDUCACAO"),
    VIAS("VIAS"),
    INFRAESTRUTURA("INFRAESTRUTURA"),
    LAZER("LAZER"),
    URBANISMO("URBANISMO"),
    ESPORTE("ESPORTE"),
    SAUDE("SAUDE");

    private String setor;

    Setor(String setor) {
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }
}
