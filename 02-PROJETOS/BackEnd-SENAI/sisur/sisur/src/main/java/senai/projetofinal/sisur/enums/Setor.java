package senai.projetofinal.sisur.enums;

public enum Setor {
    EDUCACAO("educacao"),
    VIAS("vias"),
    INFRAESTRUTURA("infraestrutura"),
    LAZER("lazer"),
    URBANISMO("urbanismo"),
    ESPORTE("esporte"),
    SAUDE("saude"),
    OUTROS("outros");

    private String setor;

    Setor(String setor) {
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }
}

