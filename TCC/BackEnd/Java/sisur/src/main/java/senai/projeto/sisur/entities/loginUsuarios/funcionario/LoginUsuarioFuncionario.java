package senai.projeto.sisur.entities.loginUsuarios.funcionario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projeto.sisur.entities.usuarios.funcionario.UsuarioFuncionario;

@Entity
@Table(name = "tb_login_usuario_funcionario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of =  "id")
public class LoginUsuarioFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario")
    private UsuarioFuncionario funcionario;
}
