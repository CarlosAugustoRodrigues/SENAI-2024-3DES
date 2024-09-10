package com.projetofinal.sisur.entities.ocorrencia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetofinal.sisur.dtos.ocorrencia.OcorrenciaReqRecDTO;
import com.projetofinal.sisur.entities.usuariocomum.UsuarioComum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "TB_OCORRENCIA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "rua", nullable = false)
    private String rua;

    @Column(name = "bairro", nullable = false)
    private String bairro;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "imagem")
    private Byte imagem;

    @Column(name = "tipo_imagem")
    private String tipoImagem;

    @Column(name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private UsuarioComum usuario;

    public  Ocorrencia(OcorrenciaReqRecDTO data) {
        setDescricao(data.descricao());
        setRua(data.rua());
        setBairro(data.bairro());
        setCep(data.cep());
        setImagem(data.imagem());
        setTipoImagem(data.tipoImagem());
        setHora(LocalTime.now());
        setData(LocalDate.now());
        setStatus("Pendente");
        setCategoria("Qualquer");
        setUsuario(data.usuario());
    }
}
