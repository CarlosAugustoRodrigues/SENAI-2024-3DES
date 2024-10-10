package senai.atividade.sisturmaseatividades.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_turmas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long numTurma;
    private String nomeTurma;

    @ManyToOne
    @JsonIgnore
    private Professor professor;

    @OneToMany(mappedBy = "turma", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Atividade> atividades = new HashSet<>();

    public Turma(Long numTurma, String nomeTurma, Professor professor) {
        this.numTurma = numTurma;
        this.nomeTurma = nomeTurma;
        this.professor = professor;
    }
}
