package com.all.locadora.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografarSenhaUsuario {

    public String criptrografarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
}