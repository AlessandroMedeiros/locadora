package com.all.locadora.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.all.locadora.controller.dto.DevolucaoDTO;
import com.all.locadora.controller.dto.LocacaoDTO;
import com.all.locadora.model.FilmeModel;
import com.all.locadora.model.ItemLocacao;
import com.all.locadora.model.LocacaoModel;
import com.all.locadora.model.UsuarioModel;
import com.all.locadora.repository.FilmeRepository;
import com.all.locadora.repository.ItemLocacaoRepository;
import com.all.locadora.repository.LocacaoRepository;
import com.all.locadora.repository.UsuarioRepository;
import com.all.locadora.service.exceptions.RNException;

@SpringBootTest
@ActiveProfiles("locadora4all")
@RunWith(SpringRunner.class)
public class LocadoraServiceTest {
    @Autowired
    private LocadoraService service;
    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private LocacaoRepository locacaoRepository;
    @MockBean
    private ItemLocacaoRepository itemLocacaoRepository;
    @MockBean
    private FilmeRepository filmeRepository;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void locar_filme_caminho_feliz() {
        UsuarioModel usuarioMock = new UsuarioModel();
        usuarioMock.setId(1);
        FilmeModel filmeMock = carregarInfosFilme();
        LocacaoModel locacaoMock = carregarInfosLocacao(filmeMock);
        locacaoMock.setUsuarioModel(usuarioMock);
        LocacaoDTO dto = new LocacaoDTO(1, 1);

        BDDMockito.given(this.usuarioRepository.findById(dto.getIdUsuario()))
                .willReturn(Optional.of(usuarioMock));

        BDDMockito.given(this.filmeRepository.findById(dto.getIdFilme()))
                .willReturn(Optional.of(filmeMock));
        BDDMockito.given(this.filmeRepository.save(Mockito.any(FilmeModel.class)))
                .willReturn(filmeMock);
        BDDMockito.given(this.locacaoRepository.save(Mockito.any(LocacaoModel.class)))
                .willReturn(locacaoMock);
        LocacaoModel locacao = service.locarFilme(dto);
        Set<ItemLocacao> itensLocacao = locacao.getItens();
        assertNotNull(locacao);
        assertEquals(itensLocacao.size(), locacaoMock.getItens().size());
        verify(filmeRepository, times(1)).findById(dto.getIdUsuario());
        verify(usuarioRepository, times(1)).findById(dto.getIdFilme());
        verify(filmeRepository, times(1)).save(filmeMock);
        verify(itemLocacaoRepository, times(1)).saveAll(locacaoMock.getItens());
    }

    @Test
    public void locar_filme_indisponivel_lancar_RNException() {
        UsuarioModel usuarioMock = new UsuarioModel();
        usuarioMock.setId(1);
        FilmeModel filmeMock = new FilmeModel("Teste filme", null, 0);
        LocacaoModel locacaoMock = carregarInfosLocacao(filmeMock);
        locacaoMock.setUsuarioModel(usuarioMock);
        LocacaoDTO dto = new LocacaoDTO(1, 1);
        BDDMockito.given(this.usuarioRepository.findById(dto.getIdUsuario())).willReturn(Optional.of(usuarioMock));
        BDDMockito.given(this.filmeRepository.findById(dto.getIdFilme())).willReturn(Optional.of(filmeMock));
        exceptionRule.expect(RNException.class);
        exceptionRule.expectMessage("Filme indisponivel. Todas as cópias foram locadas");
        service.locarFilme(dto);
        verify(filmeRepository, times(1)).findById(dto.getIdUsuario());
        verify(usuarioRepository, times(1)).findById(dto.getIdFilme());
        verify(filmeRepository, times(0)).save(filmeMock);
        verify(itemLocacaoRepository, times(0)).saveAll(locacaoMock.getItens());
    }

    @Test
    public void devolverFilme_caminho_feliz() {
        UsuarioModel usuarioMock = new UsuarioModel();
        usuarioMock.setId(1);
        FilmeModel filmeMock = new FilmeModel("Teste filme", null, 0);
        LocacaoModel locacaoMock = carregarInfosLocacao(filmeMock);
        locacaoMock.setUsuarioModel(usuarioMock);
        DevolucaoDTO dto = new DevolucaoDTO(1, 1);

        ItemLocacao itemMock = new ItemLocacao();
        itemMock.setFilme(filmeMock);
        itemMock.setLocacao(locacaoMock);
        BDDMockito.given(this.locacaoRepository.findById(dto.getIdLocacao())).willReturn(Optional.of(locacaoMock));
        BDDMockito.given(this.filmeRepository.findById(itemMock.getId().getFilme().getId())).willReturn(Optional.of(filmeMock));
        BDDMockito.given(this.filmeRepository.save(Mockito.any(FilmeModel.class))).willReturn(filmeMock);
        doNothing().when(itemLocacaoRepository).delete(itemMock);
        doNothing().when(locacaoRepository).delete(any(LocacaoModel.class));
        LocacaoModel locacao = service.devolverFilme(dto);
        assertNotNull(locacao);
        assertEquals(0, locacaoMock.getItens().size());
        assertEquals(1, filmeMock.getQuantidade());
        verify(filmeRepository, times(1)).save(filmeMock);
        verify(itemLocacaoRepository, times(1)).delete(any(ItemLocacao.class));
        verify(locacaoRepository, times(1)).delete(locacaoMock);
    }

    @Test
    public void devolver_filme_indisponivel_lancar_RNException() {
        UsuarioModel usuarioMock = new UsuarioModel();
        usuarioMock.setId(1);
        FilmeModel filmeMock = new FilmeModel("Teste filme", null, 0);
        LocacaoModel locacaoMock = carregarInfosLocacao(filmeMock);
        locacaoMock.setUsuarioModel(usuarioMock);
        DevolucaoDTO dto = new DevolucaoDTO(2, 1);
        BDDMockito.given(this.locacaoRepository.findById(dto.getIdLocacao())).willReturn(Optional.of(locacaoMock));
        exceptionRule.expect(RNException.class);
        exceptionRule.expectMessage("Locação não pertence a esse usuário. Devolução não realizada.");
        service.devolverFilme(dto);

        verify(locacaoRepository, times(1)).findById(dto.getIdLocacao());
    }

    private LocacaoModel carregarInfosLocacao(FilmeModel filmeMock) {
        LocacaoModel locacao = new LocacaoModel();
        locacao.setId(1);
        locacao.setItens(carregarItensLocacao(filmeMock, locacao));
        return locacao;
    }

    private Set<ItemLocacao> carregarItensLocacao(FilmeModel filmeMock, LocacaoModel locacao) {
        ItemLocacao item = new ItemLocacao();
        item.setFilme(filmeMock);
        item.setLocacao(locacao);
        locacao.getItens().add(item);
        return locacao.getItens();
    }

    private FilmeModel carregarInfosFilme() {
        FilmeModel filme = new FilmeModel();
        filme.setId(1);
        filme.setTitulo("Teste Filme");
        filme.setQuantidade(1);
        return filme;
    }
}
