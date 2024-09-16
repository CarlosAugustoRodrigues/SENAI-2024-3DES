package senai.projetofinal.sisur.dtos.usuario;

import senai.projetofinal.sisur.entities.Usuario;

public record LoginUsuarioResponse(Usuario usuario, String token) {
}
