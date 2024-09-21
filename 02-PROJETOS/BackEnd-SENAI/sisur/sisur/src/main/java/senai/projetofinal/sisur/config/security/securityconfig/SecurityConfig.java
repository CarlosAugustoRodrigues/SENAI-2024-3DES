package senai.projetofinal.sisur.config.security.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import senai.projetofinal.sisur.config.security.securityfilter.SecurityFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // ROTAS FUNCIONARIOS E ADMIN
                        .requestMatchers(HttpMethod.GET, "/sisur/funcionario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/sisur/funcionario/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sisur/funcionario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/sisur/funcionario/deletar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/sisur/funcionario/alterarsenha/{email}").hasRole("ADMIN")

                        // ROTAS USUARIOS
                        .requestMatchers(HttpMethod.GET, "/sisur/usuario").hasAnyRole("ADMIN", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/sisur/usuario/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sisur/usuario").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/sisur/usuario/alterarsenha/{email}").permitAll()

                        // ROTAS OCORRENCIAS
                        .requestMatchers(HttpMethod.GET, "/sisur/ocorrencia").permitAll()
                        .requestMatchers(HttpMethod.GET, "/sisur/ocorrencia/{id_usuario}").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.POST, "/sisur/ocorrencia").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.DELETE, "/sisur/ocorrencia/{id_usuario}/{id}").hasRole("USUARIO")
                        .requestMatchers(HttpMethod.GET, "/sisur/ocorrencia/{setor}").hasRole("FUNCIONARIO")
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}