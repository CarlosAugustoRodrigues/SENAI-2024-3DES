package senai.atividade.fabricaautomoveis.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

@Entity
@Table(name = "tb_automoveis")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Automoveis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String modelo;
    private Float preco;
    private String situacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Area area;
}
