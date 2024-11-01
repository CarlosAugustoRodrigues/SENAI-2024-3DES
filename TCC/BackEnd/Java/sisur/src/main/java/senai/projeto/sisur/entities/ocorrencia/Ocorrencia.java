package senai.projeto.sisur.entities.ocorrencia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projeto.sisur.enums.Setor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_ocorrencias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of =  "id")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String descricao;
    private Set<String> imagens = new HashSet<>();
    private String rua;
    private String bairro;
    private String cep;
    private Instant dataHora;
    private Setor setor;
    private String situacao;
    private Set<String> comentarios = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario")
    @JsonIgnore
    private String usuario;
}
