package senai.projeto.sisur.entities.loginUsuarios.comum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projeto.sisur.entities.usuarios.comum.UsuarioComum;

@Entity
@Table(name = "tb_login_usuario_comum")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of =  "id")
public class LoginUsuarioComum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    private UsuarioComum usuario;
}
