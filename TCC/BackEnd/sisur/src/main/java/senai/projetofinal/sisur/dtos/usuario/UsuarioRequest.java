package senai.projetofinal.sisur.dtos.usuario;

public record UsuarioRequest(
        String nome,
        String telefone,
        String email,
        String password
) {
}
