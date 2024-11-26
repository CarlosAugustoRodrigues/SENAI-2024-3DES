package senai.projetofinal.sisur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projetofinal.sisur.enums.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_contas_funcionarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ContaFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    private Funcionario funcionario;
}
