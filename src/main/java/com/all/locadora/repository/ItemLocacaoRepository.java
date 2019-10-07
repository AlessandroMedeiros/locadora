package com.all.locadora.repository;

import com.all.locadora.model.ItemLocacao;
import com.all.locadora.model.LocacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemLocacaoRepository extends JpaRepository<ItemLocacao, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT itemLocacao FROM ItemLocacao itemLocacao where itemLocacao.id.locacao = :locacaoModel")
    List<ItemLocacao> findByLocacao(LocacaoModel locacaoModel);
}
