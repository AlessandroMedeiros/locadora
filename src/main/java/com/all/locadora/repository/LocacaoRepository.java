package com.all.locadora.repository;

import com.all.locadora.model.LocacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocacaoRepository extends JpaRepository<LocacaoModel, Integer> {
}
