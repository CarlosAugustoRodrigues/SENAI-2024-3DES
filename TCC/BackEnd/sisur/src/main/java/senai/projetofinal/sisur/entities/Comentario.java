package senai.projetofinal.sisur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "tb_comentarios")
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

    private String perfil;
    private Long responsavel;
}
