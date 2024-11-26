package senai.projetofinal.sisur.dtos.auth;

import senai.projetofinal.sisur.entities.Funcionario;
import senai.projetofinal.sisur.enums.Role;

import java.util.Set;

public record AuthResponseFuncionario(Funcionario funcionario,
                                      String email,
                                      Set<Role> roles, 
                                      String token) {
}
