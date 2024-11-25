package senai.projeto.sisur.entities.usuarios.comum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuario_comum")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of =  "id")
public class UsuarioComum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagemPerfil;
    private String telefone;
}
