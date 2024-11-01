package senai.projeto.sisur.entities.usuarios.funcionario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projeto.sisur.enums.Responsabilidade;
import senai.projeto.sisur.enums.Role;
import senai.projeto.sisur.enums.Setor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_usuario_funcionario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagemPerfil;
    private Setor setor;
    private Set<Role> roles = new HashSet<>();
    private Set<Responsabilidade> responsabilidades = new HashSet<>();

}
