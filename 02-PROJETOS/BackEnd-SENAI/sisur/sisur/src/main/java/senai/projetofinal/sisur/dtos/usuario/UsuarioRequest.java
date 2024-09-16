package senai.projetofinal.sisur.dtos.usuario;

public record UsuarioRequest(
        String nome,
        String email,
        String senha
) {
}
