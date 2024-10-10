package senai.atividade.sisturmaseatividades.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "tb_atividades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long numAtividade;
    private String descricao;
    private Long professorId;

    @ManyToOne
    @JsonIgnore
    private Turma turma;

    public Atividade(String descricao, Long professorId, Long numAtividade, Turma turma) {
        this.descricao = descricao;
        this.professorId = professorId;
        this.numAtividade = numAtividade;
        this.turma = turma;
    }
}
