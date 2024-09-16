package senai.projetofinal.sisur.config.security.securityfilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import senai.projetofinal.sisur.config.security.Token;
import senai.projetofinal.sisur.repositories.UsuarioRepository;

import java.io.IOException;

@Component
public class SecurityFilterUsuario extends OncePerRequestFilter {

    private Token tokenService;
    private UsuarioRepository usuarioRepository;

    public SecurityFilterUsuario(Token tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            var usuario = usuarioRepository.findByEmail(login)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

            var authorities = usuario.getAuthorities();
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
