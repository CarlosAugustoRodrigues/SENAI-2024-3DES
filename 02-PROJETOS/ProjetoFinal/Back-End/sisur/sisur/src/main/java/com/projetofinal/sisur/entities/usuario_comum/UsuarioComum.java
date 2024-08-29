package com.projetofinal.sisur.entities.usuario_comum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetofinal.sisur.entities.ocorrencia.Ocorrencia;
import com.projetofinal.sisur.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_USUARIO_COMUM")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioComum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(nullable = false)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ocorrencias", referencedColumnName = "id")
    @JsonIgnore
    private Set<Ocorrencia> ListaOcorrencia = new HashSet<>();
}