package com.all.locadora.controller.dto;


import com.all.locadora.service.validation.RealizarLocacao;

@RealizarLocacao
public class LocacaoDTO {

    private Integer idUsuario;

    private Integer idFilme;

    public LocacaoDTO() {
    }

    public LocacaoDTO(Integer idUsuario, Integer idFilme) {
        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }
}
