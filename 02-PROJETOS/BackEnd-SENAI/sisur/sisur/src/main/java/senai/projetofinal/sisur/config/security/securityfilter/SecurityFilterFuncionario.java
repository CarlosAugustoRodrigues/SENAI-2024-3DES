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

import java.io.IOException;

@Component
public class SecurityFilterFuncionario extends OncePerRequestFilter {

    private Token tokenService;
    private FuncionarioRepository funcionarioRepository;

    public SecurityFilterFuncionario(Token tokenService, FuncionarioRepository funcionarioRepository) {
        this.tokenService = tokenService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var token = this.recuperarToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            var funcionario = funcionarioRepository.findByEmail(login)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

            var authorities = funcionario.getAuthorities();
            var authentication = new UsernamePasswordAuthenticationToken(funcionario, null, authorities);
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
