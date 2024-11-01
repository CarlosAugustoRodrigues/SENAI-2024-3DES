package senai.projeto.sisur.enums;

public enum Role {
    GERENTE("ROLE_GERENTE"),
    ADMIN("ROLE_ADMIN"),
    FUNCIONARIO("ROLE_FUNCIONARIO"),
    USUARIO("ROLE_USUARIO");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
