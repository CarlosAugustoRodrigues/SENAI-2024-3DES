package senai.projetofinal.sisur.service.funcionario;

import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senai.projetofinal.sisur.dtos.funcionario.FuncionarioRequest;
import senai.projetofinal.sisur.entities.Funcionario;
import senai.projetofinal.sisur.repositories.FuncionarioRepository;

import java.util.Optional;

@Service
public class FuncionarioService {

    FuncionarioRepository funcionarioRepository;
    PasswordEncoder passwordEncoder;

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              PasswordEncoder passwordEncoder) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Object> create(FuncionarioRequest data) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(data.email());

        if (funcionario.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado!");
        }

        if (data.senha() == null || data.senha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha não pode ser nula ou vazia!");
        }

        var novoFuncionario = new Funcionario(data);
        novoFuncionario.setSenha(passwordEncoder.encode(data.senha()));
        funcionarioRepository.save(novoFuncionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }
}
