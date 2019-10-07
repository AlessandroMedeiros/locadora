package com.all.locadora.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LocadoraService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private ItemLocacaoRepository itemLocacaoRepository;

    public LocacaoModel locarFilme(LocacaoDTO locacaoDTO) {

        UsuarioModel usuario = usuarioRepository.findById(locacaoDTO.getIdUsuario()).get();
        FilmeModel filme = filmeRepository.findById(locacaoDTO.getIdFilme()).get();
        LocacaoModel locacao = new LocacaoModel();

        if (verificarDisponibilidadeFilme(filme)) {
            atualizarDisponibilidades(filme, false);
            locacao.setId(null);
            locacao.setUsuarioModel(usuario);
            locacao.setData(new Date());
            locacao = locacaoRepository.save(locacao);
            ItemLocacao itemLocacao = new ItemLocacao();
            itemLocacao.setFilme(filme);
            itemLocacao.setLocacao(locacao);
            locacao.getItens().add(itemLocacao);
            itemLocacaoRepository.saveAll(locacao.getItens());
            return locacao;
        }
        throw new RNException("Filme indisponivel. Todas as cópias foram locadas");
    }


    public LocacaoModel devolverFilme(DevolucaoDTO devolucaoDTO) {
        LocacaoModel locacao = locacaoRepository.findById(devolucaoDTO.getIdLocacao()).get();
        if (locacao.getUsuarioModel().getId().equals(devolucaoDTO.getIdUsuario())) {
            for (ItemLocacao itemLocacao : locacao.getItens()) {
                FilmeModel filme = filmeRepository.findById(itemLocacao.getId().getFilme().getId()).get();
                atualizarDisponibilidades(filme, true);
                locacao.getItens().remove(itemLocacao);
                itemLocacaoRepository.delete(itemLocacao);
            }
            locacaoRepository.delete(locacao);
            return locacao;
        }
        throw new RNException("Locação não pertence a esse usuário. Devolução não realizada.");
    }


    private void atualizarDisponibilidades(FilmeModel filme, boolean devolucao) {
        if (devolucao) {
            filme.setQuantidade(filme.getQuantidade() + 1);
        } else {
            filme.setQuantidade(filme.getQuantidade() - 1);
        }
        filmeRepository.save(filme);
    }

    private boolean verificarDisponibilidadeFilme(FilmeModel filme) {
        return filme.getQuantidade() > 0;
    }
}
