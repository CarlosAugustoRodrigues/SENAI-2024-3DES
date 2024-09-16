package senai.projetofinal.sisur.config.security.customuserdetailsservice;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import senai.projetofinal.sisur.entities.Funcionario;
import senai.projetofinal.sisur.repositories.FuncionarioRepository;

@Component
public class CustomUserDetailsServiceFuncionario implements UserDetailsService {

    private FuncionarioRepository funcionarioRepository;

    public CustomUserDetailsServiceFuncionario(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));

        return new org.springframework.security.core.userdetails.User(
                funcionario.getUsername(), funcionario.getPassword(), funcionario.getAuthorities()
        );
    }
}
