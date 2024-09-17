package senai.projetofinal.sisur.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import senai.projetofinal.sisur.dtos.ocorrencia.OcorrenciaRequest;
import senai.projetofinal.sisur.enums.Setor;

import java.time.Instant;

@Entity
@Table(name = "tb_ocorrencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String rua;
    private String bairro;
    private String cep;

    @CreationTimestamp
    private Instant timestamp;
    private Setor setor;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;


    private void converterSetor(String setor) {
        String setorString = setor;
        Setor setorConvertido = Setor.valueOf(setorString.toUpperCase());

        setSetor(setorConvertido);
    }

    public Ocorrencia(OcorrenciaRequest data) {
        setDescricao(data.descricao());
        setRua(data.rua());
        setBairro(data.bairro());
        setCep(data.cep());
        converterSetor(data.setor());
    }
}
