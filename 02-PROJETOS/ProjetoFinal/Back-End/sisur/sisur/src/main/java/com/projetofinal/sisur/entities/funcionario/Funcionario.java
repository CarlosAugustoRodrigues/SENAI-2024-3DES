package com.projetofinal.sisur.entities.funcionario;

import com.projetofinal.sisur.dtos.funcionario.FuncionarioReqRecDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "setor", nullable = false)
    private String setor;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String senha;

    public Funcionario(FuncionarioReqRecDTO data) {
        setNome(data.nome());
        setSetor("Qualquer");
        setRole("Role_Funcionario");
        setEmail(data.email());
        setSenha(data.senha());
    }
}
