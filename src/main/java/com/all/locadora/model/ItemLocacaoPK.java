package com.all.locadora.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ItemLocacaoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "locacao_id")
    private LocacaoModel locacao;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private FilmeModel filme;

    public LocacaoModel getLocacao() {
        return locacao;
    }

    public void setLocacao(LocacaoModel locacao) {
        this.locacao = locacao;
    }

    public FilmeModel getFilme() {
        return filme;
    }

    public void setFilme(FilmeModel filme) {
        this.filme = filme;
    }
}
