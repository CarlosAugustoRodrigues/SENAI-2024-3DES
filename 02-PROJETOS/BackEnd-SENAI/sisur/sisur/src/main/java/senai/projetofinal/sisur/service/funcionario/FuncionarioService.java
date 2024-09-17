package senai.projetofinal.sisur.service.funcionario;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senai.projetofinal.sisur.config.security.Token;
import senai.projetofinal.sisur.dtos.funcionario.FuncionarioRequest;
import senai.projetofinal.sisur.dtos.funcionario.FuncionarioRequestSenha;
import senai.projetofinal.sisur.dtos.funcionario.LoginFuncionarioRequest;
import senai.projetofinal.sisur.dtos.funcionario.LoginFuncionarioResponse;
import senai.projetofinal.sisur.entities.Funcionario;
import senai.projetofinal.sisur.repositories.FuncionarioRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    FuncionarioRepository funcionarioRepository;
    PasswordEncoder passwordEncoder;
    Token tokenService;

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              PasswordEncoder passwordEncoder,
                              Token tokenService) {
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    // Service login funcionario
    public ResponseEntity loginFuncionario(LoginFuncionarioRequest data) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(data.email());

        if (funcionario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Funcionário não encontrado!");
        }

        if (data.email().equals(funcionario.get().getEmail()) && passwordEncoder.matches(data.senha(), funcionario.get().getPassword())) {
            String token = tokenService.generateTokenFuncionario(funcionario.get());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new LoginFuncionarioResponse(funcionario.get(), token));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("E-mail e/ou Senha inválidos!");
    }

    // Service criar conta funcionário - Apenas ADMIN pode utilizar esse service
    public ResponseEntity<Object> create(FuncionarioRequest data) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(data.email());

        if (funcionario.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("E-mail já cadastrado!");
        }

        if (data.senha() == null || data.senha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Senha não pode ser nula ou vazia!");
        }

        var novoFuncionario = new Funcionario(data);
        novoFuncionario.setSenha(passwordEncoder.encode(data.senha()));
        funcionarioRepository.save(novoFuncionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }

    // Service para alterar senha funcionário - Apenas ADMIN pode utilizar esse service
    public ResponseEntity<Object> updateSenha(String email, FuncionarioRequestSenha data) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);

        if (funcionario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Funcionário não encontrado!");
        }

        if (data.senha() == null || data.senha().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Senha não pode ser nula ou vazia!");
        }

        funcionario.get().setSenha(passwordEncoder.encode(data.senha()));
        funcionarioRepository.save(funcionario.get());
        return ResponseEntity.status(HttpStatus.OK)
                .body(funcionario.get());
    }

    // Service para excluir conta do funcionário - Apenas ADMIN pode utilizar esse service
    public ResponseEntity<Object> deleteFuncionario(UUID id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

        if (funcionario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Funcionário não encontrado!");
        }

        funcionarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Funcionário excluido!");
    }

}
