package senai.projetofinal.sisur.config.security.customuserdetailsservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;
import senai.projetofinal.sisur.entities.Usuario;
import senai.projetofinal.sisur.repositories.UsuarioRepository;

@Component
public class CustomUserDetailsServiceUsuario implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public CustomUserDetailsServiceUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(), usuario.getPassword(), usuario.getAuthorities()
        );
    }
}
