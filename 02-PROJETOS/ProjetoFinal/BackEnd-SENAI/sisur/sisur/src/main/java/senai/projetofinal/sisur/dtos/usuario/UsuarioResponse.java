package senai.projetofinal.sisur.dtos.usuario;

import senai.projetofinal.sisur.entities.Ocorrencia;
import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.enums.Role;

import java.util.Set;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        Role role,
        String email,
        String senha,
        Set<Ocorrencia> ocorrencias
) {

    public UsuarioResponse (Usuario data){
        this(
                data.getId(),
                data.getNome(),
                data.getRole(),
                data.getEmail(),
                data.getSenha(),
                data.getOcorrencias()
        );
    }
}
