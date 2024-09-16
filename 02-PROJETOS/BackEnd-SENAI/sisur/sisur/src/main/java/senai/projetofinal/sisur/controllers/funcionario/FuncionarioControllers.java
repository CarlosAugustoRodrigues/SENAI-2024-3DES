package senai.projetofinal.sisur.controllers.funcionario;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senai.projetofinal.sisur.dtos.funcionario.FuncionarioRequest;
import senai.projetofinal.sisur.dtos.funcionario.LoginFuncionarioRequest;
import senai.projetofinal.sisur.service.funcionario.FuncionarioService;
import senai.projetofinal.sisur.service.login.LoginSerivice;

@RestController
@RequestMapping("/sisur")
public class FuncionarioControllers {

    FuncionarioService funcionarioService;
    LoginSerivice loginSerivice;

    public FuncionarioControllers(FuncionarioService funcionarioService,
                                  LoginSerivice loginSerivice) {
        this.funcionarioService = funcionarioService;
        this.loginSerivice = loginSerivice;
    }

    @PostMapping("/funcionario/login")
    public ResponseEntity login(@RequestBody @Validated LoginFuncionarioRequest data) {
        return loginSerivice.loginFuncionario(data);
    }

    @PostMapping("/funcionario")
    public ResponseEntity<Object> create(@RequestBody @Validated FuncionarioRequest data) {
        return funcionarioService.create(data);
    }
}
