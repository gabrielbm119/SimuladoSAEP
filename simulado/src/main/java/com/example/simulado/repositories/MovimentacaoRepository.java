package com.example.simulado.repositories;

import com.example.simulado.model.MovimentacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoModel, Integer> {
    Optional<MovimentacaoModel> findById(int id);

    Optional<MovimentacaoModel> findAllById(int id);
}