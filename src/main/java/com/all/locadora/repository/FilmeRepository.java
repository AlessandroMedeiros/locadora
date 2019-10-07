package com.all.locadora.repository;

import com.all.locadora.model.FilmeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmeRepository extends JpaRepository<FilmeModel, Integer> {

    Optional<FilmeModel> findByTitulo(String titulo);

    Optional<FilmeModel> findById(Integer id);
}
