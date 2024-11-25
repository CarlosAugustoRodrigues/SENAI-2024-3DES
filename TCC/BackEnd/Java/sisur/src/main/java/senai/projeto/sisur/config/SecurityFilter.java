package senai.projeto.sisur.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import senai.projeto.sisur.repositories.LoginUsuarioComumRepository;
import senai.projeto.sisur.repositories.LoginUsuarioFuncionarioRepository;
import senai.projeto.sisur.repositories.UsuarioComumRepository;
import senai.projeto.sisur.repositories.UsuarioFuncionarioRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final Token token;
    private final LoginUsuarioComumRepository loginUsuarioComumRepository;
    private final LoginUsuarioFuncionarioRepository loginUsuarioFuncionarioRepository;

    public SecurityFilter(Token token,
                          LoginUsuarioComumRepository loginUsuarioComumRepository,
                          LoginUsuarioFuncionarioRepository loginUsuarioFuncionarioRepository) {
        this.token = token;
        this.loginUsuarioComumRepository = loginUsuarioComumRepository;
        this.loginUsuarioFuncionarioRepository = loginUsuarioFuncionarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String tokenString = this.recoverToken(request);

        if (tokenString != null) {
            String email = token.validateToken(tokenString);
            String typeToken = token.getTokenType(tokenString);

            if (typeToken.equals("usuariocomum")) {
                var usuario = loginUsuarioComumRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));

                var authorities = usuario.getAuthorities();
                var authentication = new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                var funcionario = loginUsuarioFuncionarioRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Funcionario não encontrado!"));

                var authorities = funcionario.getAuthorities();
                var authentication = new UsernamePasswordAuthenticationToken(funcionario.getUsername(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
