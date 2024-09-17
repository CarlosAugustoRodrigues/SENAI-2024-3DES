package senai.projetofinal.sisur.controllers.funcionario;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import senai.projetofinal.sisur.dtos.funcionario.FuncionarioRequest;
import senai.projetofinal.sisur.dtos.funcionario.FuncionarioRequestSenha;
import senai.projetofinal.sisur.dtos.funcionario.LoginFuncionarioRequest;
import senai.projetofinal.sisur.service.funcionario.FuncionarioService;

import java.util.UUID;

@RestController
@RequestMapping("/sisur")
public class FuncionarioControllers {

    FuncionarioService funcionarioService;

    public FuncionarioControllers(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/funcionario/login")
    public ResponseEntity<?> login(@RequestBody @Validated LoginFuncionarioRequest data) {
        return funcionarioService.loginFuncionario(data);
    }

    @PostMapping("/funcionario")
    public ResponseEntity<Object> create(@RequestBody @Validated FuncionarioRequest data) {
        return funcionarioService.create(data);
    }

    @PutMapping("/funcionario/alterarsenha/{email}")
    public ResponseEntity<Object> updateSenha(@PathVariable(value = "email") String email,
                                              @RequestBody @Validated FuncionarioRequestSenha data) {
        return funcionarioService.updateSenha(email, data);
    }

    @DeleteMapping("/funcionario/deletar/{id}")
    public ResponseEntity<Object> deleteFuncionario(@PathVariable(value = "id")UUID id) {
        return funcionarioService.deleteFuncionario(id);
    }
}
