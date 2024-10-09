package senai.atividade.fabricaautomoveis.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
}
