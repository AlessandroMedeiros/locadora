package com.all.locadora.service;

import com.all.locadora.controller.dto.NovoUsuarioDTO;
import com.all.locadora.model.UsuarioModel;
import com.all.locadora.repository.UsuarioRepository;
import com.all.locadora.security.CriptografarSenhaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioModel cadastrarUsuario(NovoUsuarioDTO novoUsuarioDTO) {
        String senhaCriptografada = criptografarSenhaUsuario(novoUsuarioDTO);
        UsuarioModel usuarioModel = new UsuarioModel(novoUsuarioDTO.getId(), novoUsuarioDTO.getEmail(), novoUsuarioDTO.getNome(), senhaCriptografada);
        usuarioModel = usuarioRepository.save(usuarioModel);
        return usuarioModel;
    }

    private String criptografarSenhaUsuario(NovoUsuarioDTO novoUsuarioDTO) {
        return new CriptografarSenhaUsuario().criptrografarSenha(novoUsuarioDTO.getSenha());
    }
}
