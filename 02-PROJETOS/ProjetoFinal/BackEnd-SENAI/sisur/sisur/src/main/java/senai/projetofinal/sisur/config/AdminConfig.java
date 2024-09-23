package senai.projetofinal.sisur.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import senai.projetofinal.sisur.entities.Funcionario;
import senai.projetofinal.sisur.enums.Role;
import senai.projetofinal.sisur.repositories.FuncionarioRepository;

@Configuration
public class AdminConfig implements CommandLineRunner {

    private FuncionarioRepository funcionarioRepository;
    private PasswordEncoder passwordEncoder;

    public AdminConfig(FuncionarioRepository funcionarioRepository,
                       PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var usuarioAdmin = funcionarioRepository.findByNome("SISURADMIN");

        usuarioAdmin.ifPresentOrElse(
                funcionario -> {
                    System.out.println("ADMIN EXISTENTE!");
                },
                () -> {
                    var admin = new Funcionario();
                    admin.setNome("SISURADMIN");
                    admin.setEmail("sisuradmin@sisur.com");
                    admin.setSenha(passwordEncoder.encode("SISURADMIN"));
                    admin.setRole(Role.ADMIN);
                    funcionarioRepository.save(admin);
                }
        );
    }
}
