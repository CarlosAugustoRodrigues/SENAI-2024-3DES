package senai.projetofinal.sisur.config.security.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import senai.projetofinal.sisur.config.security.customuserdetailsservice.CustomUserDetailsServiceUsuario;
import senai.projetofinal.sisur.config.security.securityfilter.SecurityFilterUsuario;

public class SecurityConfigUsuario {

    private CustomUserDetailsServiceUsuario userDetailsServiceUsuario;
    private SecurityFilterUsuario securityFilterUsuario;

    public SecurityConfigUsuario(CustomUserDetailsServiceUsuario userDetailsServiceUsuario,
                                 SecurityFilterUsuario securityFilterUsuario) {
        this.userDetailsServiceUsuario = userDetailsServiceUsuario;
        this.securityFilterUsuario = securityFilterUsuario;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/sisur/usuario/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sisur/usuario").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilterUsuario, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws  Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
