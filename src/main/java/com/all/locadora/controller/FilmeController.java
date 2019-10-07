package com.all.locadora.controller;

import com.all.locadora.model.FilmeModel;
import com.all.locadora.repository.FilmeRepository;
import com.all.locadora.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<FilmeModel> listarFilmes() {
        return filmeService.listarFilmes();
    }

    @GetMapping("/{titulo}/pesquisar")
    public Optional<FilmeModel> listarFilmeTitulo(@PathVariable String titulo) {
        return filmeRepository.findByTitulo(titulo);
    }
}
