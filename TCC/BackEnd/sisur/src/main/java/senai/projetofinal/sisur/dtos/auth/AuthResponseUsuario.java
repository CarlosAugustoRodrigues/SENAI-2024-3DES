package senai.projetofinal.sisur.dtos.auth;

import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.enums.Role;

public record AuthResponseUsuario(Usuario usuario,
                                  String email,
                                  Role role,
                                  String token) {
}
