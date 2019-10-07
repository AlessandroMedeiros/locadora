package com.all.locadora.controller.dto;

import com.all.locadora.model.FilmeModel;

public class FilmeDTO {

    private Integer id;

    private String titulo;

    private String diretor;

    public FilmeDTO(FilmeModel filmeModel) {
        this.id = filmeModel.getId();
        this.titulo = filmeModel.getTitulo();
        this.diretor = filmeModel.getDiretor();
    }

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
}
