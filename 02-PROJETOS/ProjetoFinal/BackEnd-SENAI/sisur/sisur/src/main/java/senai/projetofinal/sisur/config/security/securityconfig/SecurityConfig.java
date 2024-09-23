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
                        .requestMatchers(HttpMethod.GET, "/sisur/funcionario").hasRole("ADMIN") // TEM QUE FAZER
                        .requestMatchers(HttpMethod.POST, "/sisur/funcionario/login").permitAll() // FEITO
                        .requestMatchers(HttpMethod.POST, "/sisur/funcionario").hasRole("ADMIN") // FEITO
                        .requestMatchers(HttpMethod.DELETE, "/sisur/funcionario/deletar/{id}").hasRole("ADMIN") // TEM QUE FAZER
                        .requestMatchers(HttpMethod.PUT, "/sisur/funcionario/alterarsenha/{email}").hasRole("ADMIN") // TEM QUE FAZER

                        // ROTAS USUARIOS
                        .requestMatchers(HttpMethod.GET, "/sisur/usuario").hasAnyRole("ADMIN", "FUNCIONARIO")
                        .requestMatchers(HttpMethod.POST, "/sisur/usuario/login").permitAll() // FEITO
                        .requestMatchers(HttpMethod.POST, "/sisur/usuario").permitAll() // FEITO
                        .requestMatchers(HttpMethod.PUT, "/sisur/usuario/alterarsenha/{email}").permitAll() // TEM QUE FAZER

                        // ROTAS OCORRENCIAS
                        .requestMatchers(HttpMethod.GET, "/sisur/ocorrencia").permitAll() // FEITO
                        .requestMatchers(HttpMethod.GET, "/sisur/ocorrencia/usuario/{id}").hasRole("USUARIO") // FEITO
                        .requestMatchers(HttpMethod.POST, "/sisur/ocorrencia").hasRole("USUARIO") // FEITO
                        .requestMatchers(HttpMethod.DELETE, "/sisur/ocorrencia/{id_usuario}/{id}").hasRole("USUARIO") // TEM QUE FAZER
                        .requestMatchers(HttpMethod.GET, "/sisur/ocorrencia/{setor}").hasRole("FUNCIONARIO") // FEITO
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
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}