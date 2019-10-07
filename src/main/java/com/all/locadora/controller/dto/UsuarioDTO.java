package com.all.locadora.controller.dto;

import com.all.locadora.model.UsuarioModel;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String nome;

    public UsuarioDTO(UsuarioModel usuarioModel) {
        this.id = usuarioModel.getId();
        this.email = usuarioModel.getEmail();
        this.nome = usuarioModel.getNome();
    }

    public UsuarioDTO(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
