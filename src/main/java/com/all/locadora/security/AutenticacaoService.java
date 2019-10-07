package com.all.locadora.security;

import com.all.locadora.model.UsuarioModel;
import com.all.locadora.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findByEmail(email);
        if (usuarioModel.isPresent()) {
            return usuarioModel.get();
        }
        throw new UsernameNotFoundException("Email inválido!");
    }
}
