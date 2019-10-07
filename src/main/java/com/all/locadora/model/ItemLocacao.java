package com.all.locadora.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class ItemLocacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemLocacaoPK id = new ItemLocacaoPK();

    public ItemLocacao() {
    }

    public ItemLocacao(LocacaoModel locacaoModel, FilmeModel filmeModel) {
        id.setLocacao(locacaoModel);
        id.setFilme(filmeModel);
    }

    public void setLocacao(LocacaoModel locacao) {
        this.id.setLocacao(locacao);
    }

    public void setFilme(FilmeModel filme) {
        this.id.setFilme(filme);
    }

    public ItemLocacaoPK getId() {
        return id;
    }

    public void setId(ItemLocacaoPK id) {
        this.id = id;
    }
}
