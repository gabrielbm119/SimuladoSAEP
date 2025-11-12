package com.example.simulado.service;

import com.example.simulado.model.MovimentacaoModel;
import com.example.simulado.model.ProdutoModel;
import com.example.simulado.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoModel registrarMovimentacao(String tipo, ProdutoModel produto) {
        MovimentacaoModel movimentacao = new MovimentacaoModel();
        movimentacao.setTipoMovimentacao(tipo);
        movimentacao.setProdutoModel(produto);
        movimentacao.setDataHoraMovimentacao(LocalDateTime.now());

        return movimentacaoRepository.save(movimentacao);
    }

    public List<MovimentacaoModel> getAllMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    public Object getMovimentacaoById(int idMovimentacao) {
        Optional<MovimentacaoModel> movimentacaoModel = movimentacaoRepository.findById(idMovimentacao);
        if (movimentacaoModel.isPresent()) {
            return movimentacaoModel.get();
        } else {
            return null;
        }
    }
}