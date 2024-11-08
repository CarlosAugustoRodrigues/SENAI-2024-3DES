package senai.projeto.sisur.dtos.loginUsuarios.comum;

import senai.projeto.sisur.entities.usuarios.comum.UsuarioComum;

public record LoginUsuarioComumResDTO(UsuarioComum usuario, String token) {
}
