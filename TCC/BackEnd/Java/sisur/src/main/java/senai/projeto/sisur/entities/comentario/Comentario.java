package senai.projeto.sisur.entities.comentario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projeto.sisur.entities.ocorrencia.Ocorrencia;

import java.time.Instant;

@Entity
@Table(name = "tb_comentario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String comentario;
    private Instant dataHora;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ocorrencia ocorrencia;

    private Long responsavel;
}
