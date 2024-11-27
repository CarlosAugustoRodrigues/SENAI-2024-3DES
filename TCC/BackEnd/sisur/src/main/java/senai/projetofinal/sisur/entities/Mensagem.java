package senai.projetofinal.sisur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import senai.projetofinal.sisur.enums.Role;

import java.time.Instant;

@Entity
@Table(name = "tb_mensagens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String mensagem;
    private Instant data;

    @ManyToOne(fetch = FetchType.EAGER)
    private Ocorrencia ocorrencia;
    private Role perfil;
    private Long responsavel;
}
