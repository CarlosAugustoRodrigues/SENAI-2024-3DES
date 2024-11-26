package senai.projetofinal.sisur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projetofinal.sisur.enums.Responsabilidade;
import senai.projetofinal.sisur.enums.Setor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_funcionarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagemPerfil;
    private Setor setor;
    private Set<Responsabilidade> responsabilidades = new HashSet<>();
}
