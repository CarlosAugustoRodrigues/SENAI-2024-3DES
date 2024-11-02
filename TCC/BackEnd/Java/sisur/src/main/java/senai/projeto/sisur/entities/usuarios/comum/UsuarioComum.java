package senai.projeto.sisur.entities.usuarios.comum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projeto.sisur.entities.ocorrencia.Ocorrencia;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_usuario_comum")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of =  "id")
public class UsuarioComum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagemPerfil;
    private String telefone;
    private String role;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    @JoinColumn(name = "lista_ocorrencias")
    @JsonIgnore
    private Set<Ocorrencia> ocorrencias = new HashSet<>();
}