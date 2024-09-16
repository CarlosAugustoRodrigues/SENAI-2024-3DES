package senai.projetofinal.sisur.config.security.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import senai.projetofinal.sisur.config.security.customuserdetailsservice.CustomUserDetailsServiceUsuario;
import senai.projetofinal.sisur.config.security.securityfilter.SecurityFilterFuncionario;
import senai.projetofinal.sisur.config.security.securityfilter.SecurityFilterUsuario;

public class SecurityConfigFuncionario {

    private CustomUserDetailsServiceUsuario userDetailsServiceUsuario;
    private SecurityFilterFuncionario securityFilterFuncionario;

    public SecurityConfigFuncionario(CustomUserDetailsServiceUsuario userDetailsServiceUsuario,
                                     SecurityFilterFuncionario securityFilterFuncionario) {
        this.userDetailsServiceUsuario = userDetailsServiceUsuario;
        this.securityFilterFuncionario = securityFilterFuncionario;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilterFuncionario, UsernamePasswordAuthenticationFilter.class);

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
