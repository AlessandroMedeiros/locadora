package com.all.locadora.service;

import com.all.locadora.model.FilmeModel;
import com.all.locadora.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<FilmeModel> listarFilmes() {
        List<FilmeModel> listaFilmes = filmeRepository.findAll();
        listarFilmesDisponiveis(listaFilmes);
        return listaFilmes;
    }

    private void listarFilmesDisponiveis(List<FilmeModel> listaFilmes) {
        for (int i = 0; i < listaFilmes.size(); i++) {
            if (listaFilmes.get(i).getQuantidade() == 0) {
                listaFilmes.remove(i);
            }
        }
    }
}
