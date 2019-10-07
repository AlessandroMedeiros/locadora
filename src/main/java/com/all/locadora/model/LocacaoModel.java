package com.all.locadora.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "locacao")
public class LocacaoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuarioModel;

    @OneToMany(mappedBy = "id.locacao")
    private Set<ItemLocacao> itens = new HashSet<>();

    public LocacaoModel() {
    }

    public LocacaoModel(Integer id, Date data, UsuarioModel usuarioModel) {
        this.id = id;
        this.data = data;
        this.usuarioModel = usuarioModel;
    }

    public LocacaoModel(Date data, UsuarioModel usuarioModel) {
        this.data = data;
        this.usuarioModel = usuarioModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public Set<ItemLocacao> getItens() {
        return itens;
    }

    public void setItens(Set<ItemLocacao> itens) {
        this.itens = itens;
    }
}
