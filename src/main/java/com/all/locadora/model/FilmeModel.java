package com.all.locadora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "filme")
public class FilmeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public FilmeModel() {
    }

    public FilmeModel(String titulo, String diretor) {
        this.titulo = titulo;
        this.diretor = diretor;
    }

    public FilmeModel(String titulo, String diretor, int quantidade) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.quantidade = quantidade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "titulo")
    private String titulo;

    @NotNull
    @Column(name = "diretor")
    private String diretor;

    @NotNull
    @Column(name = "quantidade")
    private int quantidade;

    @JsonIgnore
    @OneToMany(mappedBy = "id.filme")
    private Set<ItemLocacao> itens = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Set<ItemLocacao> getItens() {
        return itens;
    }

    public void setItens(Set<ItemLocacao> itens) {
        this.itens = itens;
    }
}
