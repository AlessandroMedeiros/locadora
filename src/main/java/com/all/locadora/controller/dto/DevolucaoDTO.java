package com.all.locadora.controller.dto;

import com.all.locadora.service.validation.RealizarDevolucao;

@RealizarDevolucao
public class DevolucaoDTO {

    private Integer idUsuario;

    private Integer idLocacao;

    public DevolucaoDTO() {
    }

    public DevolucaoDTO(Integer idUsuario, Integer idLocacao) {
        this.idUsuario = idUsuario;
        this.idLocacao = idLocacao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }
}
