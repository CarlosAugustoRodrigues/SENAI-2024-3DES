package senai.projetofinal.sisur.dtos.usuario;

import senai.projetofinal.sisur.entities.ContaUsuario;
import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.enums.Role;

public record UsuarioResponse(
        Long idContaUsuario,
        String email,
        String password,
        Role role,
        Usuario usuario
) {
    public UsuarioResponse(ContaUsuario contaUsuario) {
        this(
                contaUsuario.getId(),
                contaUsuario.getEmail(),
                contaUsuario.getPassword(),
                contaUsuario.getRole(),
                contaUsuario.getUsuario()
        );
    }
}
