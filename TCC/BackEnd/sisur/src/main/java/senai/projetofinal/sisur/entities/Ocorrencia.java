package senai.projetofinal.sisur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projetofinal.sisur.enums.Setor;
import senai.projetofinal.sisur.enums.Status;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_ocorrencias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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
    private Status status;
    private Setor setor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
}
