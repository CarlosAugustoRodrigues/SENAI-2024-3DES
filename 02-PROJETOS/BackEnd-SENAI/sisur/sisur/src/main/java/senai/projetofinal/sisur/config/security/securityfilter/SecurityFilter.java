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
import senai.projetofinal.sisur.repositories.FuncionarioRepository;
import senai.projetofinal.sisur.repositories.UsuarioRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private Token token;
    private FuncionarioRepository funcionarioRepository;
    private UsuarioRepository usuarioRepository;

    public SecurityFilter(Token token,
                          FuncionarioRepository funcionarioRepository,
                          UsuarioRepository usuarioRepository) {
        this.token = token;
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var tokenString = this.recuperarToken(request);

        if (tokenString != null) {
            var email = token.validateToken(tokenString);
            var tipoToken = token.getTokenType(tokenString);
            System.out.println(tipoToken);
            System.out.println(tokenString);
            
            if (tipoToken.equals("funcionario")) {
                var funcionario = funcionarioRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
                var authorities = funcionario.getAuthorities();
                var authentication = new UsernamePasswordAuthenticationToken(funcionario.getUsername(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else if (tipoToken.equals("usuario")) {
                var usuario = usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
                var authorities = usuario.getAuthorities();
                var authentication = new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
