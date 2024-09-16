package com.projetofinal.sisur.entities.usuariocomum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetofinal.sisur.entities.ocorrencia.Ocorrencia;
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
    private String role;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String senha;

    @Column(name = "ocorrencia", nullable = false)
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Ocorrencia> ListaOcorrencia = new HashSet<>();
}