package com.all.locadora.service;

import com.all.locadora.controller.dto.NovoUsuarioDTO;
import com.all.locadora.model.UsuarioModel;
import com.all.locadora.repository.UsuarioRepository;
import com.all.locadora.security.CriptografarSenhaUsuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("locadora4all")
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void cadastrar_usuario_caminho_feliz(){
        NovoUsuarioDTO novoUsuarioDTOTest = new NovoUsuarioDTO("teste@email.com", "teste nome", "teste123");
        String senhaCriptografada = "senhacriptografada";

        UsuarioModel usuarioModelTest = new UsuarioModel(novoUsuarioDTOTest.getId(), novoUsuarioDTOTest.getEmail(), novoUsuarioDTOTest.getNome(), senhaCriptografada);
        BDDMockito.given(this.bCryptPasswordEncoder.encode(novoUsuarioDTOTest.getSenha())).willReturn(senhaCriptografada);
        BDDMockito.given(this.usuarioRepository.save(any(UsuarioModel.class))).willReturn(usuarioModelTest);

        UsuarioModel usuarioModel = usuarioService.cadastrarUsuario(novoUsuarioDTOTest);

        verify(usuarioRepository, times(1)).save(any(UsuarioModel.class));
        assertNotNull(usuarioModel);
        assertEquals(senhaCriptografada, usuarioModel.getSenha());
    }
}
